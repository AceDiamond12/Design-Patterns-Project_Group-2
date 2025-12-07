package paint.model;

import javafx.scene.paint.Color;

// The interface for all shapes in our application
// الواجهة الرئيسية لجميع الأشكال في التطبيق
public interface Shape {
    String getName();
    String label();
    
    // Getters and Setters for color
    // دوال لجلب وتغيير اللون
    Color  getColor();
    void   setColor(Color c);

    // [PROTOTYPE PATTERN] Creates a clone of the shape
    // [نمط Prototype] لإنشاء نسخة طبق الأصل من الشكل
    Shape  copy();

    // Methods to modify the shape
    // دوال لتعديل الشكل (تحريك أو تغيير الحجم)
    void   moveBy(double dx, double dy);
    void   scaleBy(double factor);
}