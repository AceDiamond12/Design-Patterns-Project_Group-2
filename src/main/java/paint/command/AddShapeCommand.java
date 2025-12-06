package paint.command;

import paint.model.Shape;
import java.util.List;

public class AddShapeCommand implements Command {
    private final Shape shape;
    private final List<Shape> list;

    public AddShapeCommand(Shape shape, List<Shape> list) {
        this.shape = shape;
        this.list = list;
    }

    @Override
    public void execute() {
        list.add(shape);
    }

    @Override
    public void undo() {
        list.remove(shape);
    }
}