package paint.view;

import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import paint.model.ShapeGroup;

// [ADAPTER PATTERN] Converts our model Shapes into JavaFX Nodes
// [نمط Adapter] يحول كائنات النموذج الخاصة بنا إلى عناصر JavaFX قابلة للرسم
public class FxShapeAdapter {

    public static Node toNode(paint.model.Shape s) {
        
        // 1. Handle ShapeGroup (Composite Pattern integration)
        // التعامل مع المجموعات (تكامل مع نمط Composite)
        if (s instanceof ShapeGroup g) {
            Group fxGroup = new Group();
            for (paint.model.Shape child : g.getChildren()) {
                // Recursively adapt children
                // تحويل الأبناء بشكل تكراري
                fxGroup.getChildren().add(toNode(child));
            }
            return fxGroup;
        }

        // 2. Handle Basic Shapes
        // التعامل مع الأشكال الأساسية
        Color stroke = (s.getColor() == null) ? Color.BLACK : s.getColor();

        if (s instanceof paint.model.Rectangle r) {
            // Adapt paint.model.Rectangle -> javafx.scene.shape.Rectangle
            // تحويل المستطيل من النموذج إلى مستطيل JavaFX
            javafx.scene.shape.Rectangle n =
                new javafx.scene.shape.Rectangle(r.getX(), r.getY(), r.getW(), r.getH());
            n.setFill(Color.TRANSPARENT);
            n.setStroke(stroke);
            return n;
        }

        // ... (Logic for Square, Circle, Ellipse, Line is similar) ...
        // ... (بقية الأشكال بنفس الطريقة) ...

        if (s instanceof paint.model.Triangle t) {
            // Adapt Triangle using Polygon
            // تحويل المثلث باستخدام Polygon
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

        throw new IllegalArgumentException("Unsupported shape: " + s.getName());
    }
}