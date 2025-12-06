package paint.model;

import javafx.scene.paint.Color;

public interface Shape {
    String getName();
    String label();
    Color  getColor();
    void   setColor(Color c);
    Shape  copy();
    void   moveBy(double dx, double dy);
    void   scaleBy(double factor);
}
