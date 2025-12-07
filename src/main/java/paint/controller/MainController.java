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

    // UI Components injected from FXML
    // عناصر واجهة المستخدم
    @FXML private ComboBox<String> shapeBox;
    @FXML private ColorPicker colorPicker;
    @FXML private Pane canvasPane;
    @FXML private ListView<String> shapesList;
    @FXML private Button groupBtn;

    // The data model: a list of shapes
    // نموذج البيانات: قائمة الأشكال
    private final List<Shape> shapes = new ArrayList<>();
    
    // [COMMAND PATTERN] Manager to handle Undo/Redo
    // [نمط Command] مدير للتعامل مع التراجع والإعادة
    private final CommandManager commandManager = new CommandManager();

    @FXML
    private void initialize() {
        // Setup initial UI state
        // إعداد الحالة الأولية للواجهة
        shapeBox.getItems().addAll("Rectangle","Circle","Line","Ellipse","Triangle","Square");
        shapeBox.getSelectionModel().selectFirst();
        colorPicker.setValue(javafx.scene.paint.Color.BLACK);
        // Allow multiple selection for grouping
        // السماح باختيار متعدد لأجل التجميع
        shapesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        refreshUI();
    }

    @FXML
    private void canvasClicked(MouseEvent e) {
        // Use Factory to create a new shape
        // استخدام Factory لإنشاء شكل جديد
        String type = shapeBox.getValue();
        javafx.scene.paint.Color c = colorPicker.getValue();
        Shape s = ShapeFactory.createShape(type, e.getX(), e.getY(), c);
        
        // [COMMAND PATTERN] Execute 'AddShapeCommand' instead of adding directly
        // [نمط Command] تنفيذ أمر إضافة الشكل بدلاً من الإضافة المباشرة
        commandManager.executeCommand(new AddShapeCommand(s, shapes));
        
        refreshUI();
    }

    @FXML
    private void moveSelected() { 
        Shape s = getSelectedShape();
        if (s == null) return;
        
        // [COMMAND PATTERN] Execute 'MoveCommand'
        // [نمط Command] تنفيذ أمر التحريك
        commandManager.executeCommand(new MoveCommand(s, 15, 10));
        refreshUI();
    }

    @FXML
    private void groupSelected() {
        // [COMPOSITE PATTERN] Grouping logic
        // [نمط Composite] منطق التجميع
        ObservableList<Integer> indices = shapesList.getSelectionModel().getSelectedIndices();
        if (indices.size() < 2) {
            info("Group", "Select at least 2 shapes to group.");
            return;
        }

        List<Shape> toGroup = new ArrayList<>();
        for (int i : indices) {
            toGroup.add(shapes.get(i));
        }

        ShapeGroup group = new ShapeGroup();
        for (Shape s : toGroup) group.add(s);

        shapes.removeAll(toGroup);
        shapes.add(group); // Add the group as a single shape / إضافة المجموعة كشكل واحد
        
        refreshUI();
    }

    @FXML
    private void copySelected() {
        Shape s = getSelectedShape();
        if (s == null) return;
        // [PROTOTYPE PATTERN] Using .copy()
        // [نمط Prototype] استخدام دالة النسخ
        shapes.add(s.copy());
        refreshUI();
    }

    // Undo/Redo delegate to CommandManager
    // التراجع والإعادة يتم تفويضهما لمدير الأوامر
    @FXML private void undo() { commandManager.undo(); refreshUI(); }
    @FXML private void redo() { commandManager.redo(); refreshUI(); }

    @FXML
    private void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Shapes");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(canvasPane.getScene().getWindow());
        
        if (file != null) {
            // [STRATEGY PATTERN] Using TxtSaveStrategy
            // [نمط Strategy] استخدام استراتيجية الحفظ النصي
            new TxtSaveStrategy().save(shapes, file);
        }
    }
    
    @FXML
    private void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Shapes");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(canvasPane.getScene().getWindow());
        
        if (file != null) {
            // [STRATEGY PATTERN] Loading using Strategy
            // [نمط Strategy] التحميل باستخدام الاستراتيجية
            List<Shape> loaded = new TxtSaveStrategy().load(file);
            shapes.clear();
            shapes.addAll(loaded);
            refreshUI();
        }
    }

    // Helper method to draw everything
    // دالة مساعدة لرسم كل شيء على الشاشة
    private void refreshUI() {
        canvasPane.getChildren().clear();
        for (Shape s : shapes) {
            // [ADAPTER PATTERN] Convert Model -> View
            // [نمط Adapter] تحويل من نموذج إلى عرض
            Node n = FxShapeAdapter.toNode(s);
            canvasPane.getChildren().add(n);
        }
        shapesList.getItems().setAll(shapes.stream().map(Shape::label).toList());
    }
    
    // Other helper methods...
    private Shape getSelectedShape() {
        int idx = shapesList.getSelectionModel().getSelectedIndex();
        if (idx < 0 || idx >= shapes.size()) return null;
        return shapes.get(idx);
    }
    
    private void info(String h, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(h); a.setContentText(m); a.showAndWait();
    }
    @FXML private void deleteSelected() { Shape s = getSelectedShape(); if(s!=null) shapes.remove(s); refreshUI(); }
    @FXML private void recolorSelected() { Shape s = getSelectedShape(); if(s!=null) s.setColor(colorPicker.getValue()); refreshUI(); }
    @FXML private void resizeSelected() { Shape s = getSelectedShape(); if(s!=null) s.scaleBy(1.1); refreshUI(); }
    @FXML private void importFile() { info("Import", "Import placeholder."); }
}