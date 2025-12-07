package paint.model;

import javafx.scene.paint.Color;

public class Rectangle implements Shape {
    private double x, y, w, h;
    private Color color;

    public Rectangle(double x, double y, double w, double h, Color c) {
        this.x=x; this.y=y; this.w=w; this.h=h; this.color=c;
    }
    
    // Getters
    public double getX(){return x;} public double getY(){return y;}
    public double getW(){return w;} public double getH(){return h;}

    @Override public String getName(){ return "Rectangle"; }
    @Override public String label(){ return "Rectangle ("+(int)x+","+(int)y+")"; }
    @Override public Color getColor(){ return color; }
    @Override public void setColor(Color c){ this.color=c; }

    // [PROTOTYPE PATTERN] Return a new object with the same values
    // [نمط Prototype] إرجاع كائن جديد بنفس القيم
    @Override public Shape copy(){ return new Rectangle(x,y,w,h,color); }

    @Override public void moveBy(double dx,double dy){ x+=dx; y+=dy; }
    @Override public void scaleBy(double f){ w*=f; h*=f; }
}