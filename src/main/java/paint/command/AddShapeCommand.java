package paint.command;

import paint.model.Shape;
import java.util.List;

// Concrete Command: Adds a shape
// أمر ملموس: إضافة شكل.
public class AddShapeCommand implements Command {
    private final Shape shape;
    private final List<Shape> list;

    public AddShapeCommand(Shape shape, List<Shape> list) {
        this.shape = shape;
        this.list = list;
    }

    @Override
    public void execute() {
        list.add(shape); // Action: Add / الفعل: إضافة
    }

    @Override
    public void undo() {
        list.remove(shape); // Undo: Remove / التراجع: حذف
    }
}