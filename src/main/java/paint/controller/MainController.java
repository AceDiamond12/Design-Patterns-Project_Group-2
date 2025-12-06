package paint.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import paint.model.Shape;
import paint.model.ShapeGroup;
import paint.view.FxShapeAdapter;
import paint.command.CommandManager;
import paint.command.AddShapeCommand;
import paint.command.MoveCommand;
import paint.strategy.TxtSaveStrategy;

public class MainController {

    @FXML private ComboBox<String> shapeBox;
    @FXML private ColorPicker colorPicker;
    @FXML private Pane canvasPane;
    @FXML private ListView<String> shapesList;
    @FXML private Button groupBtn;

    private final List<Shape> shapes = new ArrayList<>();
    private final CommandManager commandManager = new CommandManager();

    @FXML
    private void initialize() {
        shapeBox.getItems().addAll("Rectangle","Circle","Line","Ellipse","Triangle","Square");
        shapeBox.getSelectionModel().selectFirst();
        colorPicker.setValue(javafx.scene.paint.Color.BLACK);
        shapesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        refreshUI();
    }

    @FXML
    private void canvasClicked(MouseEvent e) {
        if (e.getTarget() != canvasPane) return; 

        String type = shapeBox.getValue();
        javafx.scene.paint.Color c = colorPicker.getValue();
        Shape s = ShapeFactory.createShape(type, e.getX(), e.getY(), c);
        
        commandManager.executeCommand(new AddShapeCommand(s, shapes));
        refreshUI();
    }

    @FXML
    private void moveSelected() { 
        Shape s = getSelectedShape();
        if (s == null) return;
        commandManager.executeCommand(new MoveCommand(s, 15, 10));
        refreshUI();
    }

    // --- Restored Missing Methods ---

    @FXML
    private void groupSelected() {
        ObservableList<Integer> indices = shapesList.getSelectionModel().getSelectedIndices();
        if (indices.size() < 2) {
            info("Group", "Select at least 2 shapes to group.");
            return;
        }

        // Create group (Note: this logic is simplified and not undoable via command yet)
        List<Shape> toGroup = new ArrayList<>();
        for (int i : indices) {
            toGroup.add(shapes.get(i));
        }

        ShapeGroup group = new ShapeGroup();
        for (Shape s : toGroup) group.add(s);

        shapes.removeAll(toGroup);
        shapes.add(group);
        
        refreshUI();
    }

    @FXML
    private void recolorSelected() {
        Shape s = getSelectedShape();
        if (s == null) return;
        s.setColor(colorPicker.getValue());
        refreshUI();
    }

    @FXML
    private void deleteSelected() {
        Shape s = getSelectedShape();
        if (s == null) return;
        shapes.remove(s);
        refreshUI();
    }

    @FXML
    private void copySelected() {
        Shape s = getSelectedShape();
        if (s == null) return;
        shapes.add(s.copy());
        refreshUI();
    }

    @FXML
    private void resizeSelected() {
        Shape s = getSelectedShape();
        if (s == null) return;
        s.scaleBy(1.1);
        refreshUI();
    }

    // --------------------------------

    @FXML
    private void undo() {
        commandManager.undo();
        refreshUI();
    }

    @FXML
    private void redo() {
        commandManager.redo();
        refreshUI();
    }

    @FXML
    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Shapes");
        File file = fileChooser.showSaveDialog(canvasPane.getScene().getWindow());
        
        if (file != null) {
            new TxtSaveStrategy().save(shapes, file);
        }
    }
    
    @FXML private void loadFile()   { info("Load", "LoadFromXML placeholder."); }
    @FXML private void importFile() { info("Import", "Import placeholder."); }

    /* Helpers */
    private Shape getSelectedShape() {
        int idx = shapesList.getSelectionModel().getSelectedIndex();
        if (idx < 0 || idx >= shapes.size()) return null;
        return shapes.get(idx);
    }

    private void refreshUI() {
        canvasPane.getChildren().clear();
        for (Shape s : shapes) {
            Node n = FxShapeAdapter.toNode(s);
            canvasPane.getChildren().add(n);
        }
        shapesList.getItems().setAll(shapes.stream().map(Shape::label).toList());
    }

    private void info(String h, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(h); a.setContentText(m); a.showAndWait();
    }
}