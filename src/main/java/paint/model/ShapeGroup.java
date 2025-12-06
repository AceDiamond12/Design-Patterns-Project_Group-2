package paint.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup implements Shape {
    // This list holds the "children" shapes
    private final List<Shape> children = new ArrayList<>();

    // Methods to add/remove children
    public void add(Shape s) { children.add(s); }
    public void remove(Shape s) { children.remove(s); }
    public List<Shape> getChildren() { return children; }

    @Override
    public String getName() { return "Group"; }

    @Override
    public String label() { return "Group (" + children.size() + ")"; }

    @Override
    public Color getColor() {
        // Return the color of the first child, or transparent if empty
        return children.isEmpty() ? Color.TRANSPARENT : children.get(0).getColor();
    }

    @Override
    public void setColor(Color c) {
        // When we set color on the group, we set it for ALL children
        for (Shape s : children) s.setColor(c);
    }

    @Override
    public Shape copy() {
        // Deep copy: Create a new group and copy all children into it
        ShapeGroup clone = new ShapeGroup();
        for (Shape s : children) {
            clone.add(s.copy());
        }
        return clone;
    }

    @Override
    public void moveBy(double dx, double dy) {
        // Move all children
        for (Shape s : children) s.moveBy(dx, dy);
    }

    @Override
    public void scaleBy(double f) {
        // Scale all children
        for (Shape s : children) s.scaleBy(f);
    }
}