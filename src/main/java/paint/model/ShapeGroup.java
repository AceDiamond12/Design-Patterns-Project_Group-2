package paint.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

// [COMPOSITE PATTERN] Treats a group of shapes as a single shape
// [نمط Composite] يعامل مجموعة الأشكال كشكل واحد
public class ShapeGroup implements Shape {
    
    // A list to hold the children (individual shapes or other groups)
    // قائمة تحتوي على الأشكال الفرعية
    private final List<Shape> children = new ArrayList<>();

    // Add a shape to the group
    // إضافة شكل للمجموعة
    public void add(Shape s) { children.add(s); }
    
    // Remove a shape from the group
    // حذف شكل من المجموعة
    public void remove(Shape s) { children.remove(s); }
    
    public List<Shape> getChildren() { return children; }

    @Override
    public String getName() { return "Group"; }

    @Override
    public String label() { return "Group (" + children.size() + ")"; }

    @Override
    public Color getColor() {
        // Return transparent if empty, or the color of the first child
        // يرجع لون شفاف إذا كانت فارغة، أو لون أول عنصر
        return children.isEmpty() ? Color.TRANSPARENT : children.get(0).getColor();
    }

    @Override
    public void setColor(Color c) {
        // Delegate: Update color for ALL children
        // تفويض: يغير اللون لجميع الأشكال داخل المجموعة
        for (Shape s : children) s.setColor(c);
    }

    @Override
    public Shape copy() {
        // [PROTOTYPE] Deep copy: Copy the group and all its children
        // نسخ عميق: ينسخ المجموعة وكل ما بداخلها
        ShapeGroup clone = new ShapeGroup();
        for (Shape s : children) {
            clone.add(s.copy());
        }
        return clone;
    }

    @Override
    public void moveBy(double dx, double dy) {
        // Delegate: Move ALL children
        // تفويض: يحرك جميع الأشكال داخل المجموعة
        for (Shape s : children) s.moveBy(dx, dy);
    }

    @Override
    public void scaleBy(double f) {
        // Delegate: Scale ALL children
        // تفويض: يغير حجم جميع الأشكال
        for (Shape s : children) s.scaleBy(f);
    }
}