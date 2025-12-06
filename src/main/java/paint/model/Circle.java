package paint.model;

import javafx.scene.paint.Color;

public class Circle implements Shape {
    private double cx, cy, r; private Color color;
    public Circle(double cx,double cy,double r, Color c){ this.cx=cx; this.cy=cy; this.r=r; this.color=c; }
    public double getCx(){return cx;} public double getCy(){return cy;} public double getR(){return r;}
    @Override public String getName(){ return "Circle"; }
    @Override public String label(){ return "Circle ("+(int)cx+","+(int)cy+")"; }
    @Override public Color getColor(){ return color; }
    @Override public void setColor(Color c){ this.color=c; }
    @Override public Shape copy(){ return new Circle(cx,cy,r,color); }
    @Override public void moveBy(double dx,double dy){ cx+=dx; cy+=dy; }
    @Override public void scaleBy(double f){ r*=f; }
}
