package paint.command;

import paint.model.Shape;

public class MoveCommand implements Command {
    private final Shape shape;
    private final double dx, dy;

    public MoveCommand(Shape shape, double dx, double dy) {
        this.shape = shape;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        shape.moveBy(dx, dy);
    }

    @Override
    public void undo() {
        shape.moveBy(-dx, -dy); // Reverse the move
    }
}