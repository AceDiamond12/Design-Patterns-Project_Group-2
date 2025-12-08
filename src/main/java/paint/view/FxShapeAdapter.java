package paint.view;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import paint.model.ShapeGroup;

public class FxShapeAdapter {

    public static Node toNode(paint.model.Shape s) {
        // 1. Handle the new ShapeGroup (Composite Pattern)
        if (s instanceof ShapeGroup g) {
            Group fxGroup = new Group();
            for (paint.model.Shape child : g.getChildren()) {
                fxGroup.getChildren().add(toNode(child));
            }
            return fxGroup;
        }

        // 2. Handle basic shapes
        Color stroke = (s.getColor() == null) ? Color.BLACK : s.getColor();

        if (s instanceof paint.model.Rectangle r) {
            javafx.scene.shape.Rectangle n =
                new javafx.scene.shape.Rectangle(r.getX(), r.getY(), r.getW(), r.getH());
            n.setFill(Color.TRANSPARENT);
            n.setStroke(stroke);
            return n;
        }

        if (s instanceof paint.model.Square sq) {
            javafx.scene.shape.Rectangle n =
                new javafx.scene.shape.Rectangle(sq.getX(), sq.getY(), sq.getSide(), sq.getSide());
            n.setFill(stroke.deriveColor(0, 1, 1, 0.2));
            n.setStroke(stroke);
            return n;
        }

        if (s instanceof paint.model.Circle c) {
            javafx.scene.shape.Circle n =
                new javafx.scene.shape.Circle(c.getCx(), c.getCy(), c.getR());
            n.setFill(stroke.deriveColor(0, 1, 1, 0.2));
            n.setStroke(stroke);
            return n;
        }

        if (s instanceof paint.model.Ellipse e) {
            javafx.scene.shape.Ellipse n =
                new javafx.scene.shape.Ellipse(e.getCx(), e.getCy(), e.getRx(), e.getRy());
            n.setFill(stroke.deriveColor(0, 1, 1, 0.2));
            n.setStroke(stroke);
            return n;
        }

        // IMPORTANT: This is the block that was failing or missing!
        if (s instanceof paint.model.Line l) {
            javafx.scene.shape.Line n =
                new javafx.scene.shape.Line(l.getX1(), l.getY1(), l.getX2(), l.getY2());
            n.setStroke(stroke);
            n.setStrokeWidth(2);
            return n;
        }

        if (s instanceof paint.model.Triangle t) {
            double half = t.getBase() / 2.0;
            Polygon p = new Polygon(
                t.getCx(),           t.getCy() - t.getHeight() / 2.0,
                t.getCx() - half,    t.getCy() + t.getHeight() / 2.0,
                t.getCx() + half,    t.getCy() + t.getHeight() / 2.0
            );
            p.setFill(stroke.deriveColor(0, 1, 1, 0.2));
            p.setStroke(stroke);
            return p;
        }

        // If the shape is not recognized, throw an error
        throw new IllegalArgumentException("Unsupported shape: " + s.getName());
    }
}