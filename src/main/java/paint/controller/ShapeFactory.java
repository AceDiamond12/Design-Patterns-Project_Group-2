package paint.controller;

import javafx.scene.paint.Color;
import paint.model.*;

public class ShapeFactory {
    public static Shape createShape(String type, double x, double y, Color c) {
        switch (type.toLowerCase()) {
            case "rectangle": return new Rectangle(x-80, y-40, 160, 80, c);
            case "square":    return new Square(x-40, y-40, 80, c);
            case "circle":    return new Circle(x, y, 40, c);
            case "ellipse":   return new Ellipse(x, y, 55, 85, c);
            case "line":      return new Line(x-80, y-40, x+80, y+40, c);
            case "triangle":  return new Triangle(x, y, 120, 90, c);
            default: throw new IllegalArgumentException("Unknown shape: " + type);
        }
    }
}
