package paint.model;

import javafx.scene.paint.Color;

public class Triangle implements Shape {
    private double cx,cy,base,height; private Color color;
    public Triangle(double cx,double cy,double base,double height, Color c){
        this.cx=cx; this.cy=cy; this.base=base; this.height=height; this.color=c;
    }
    public double getCx(){return cx;} public double getCy(){return cy;}
    public double getBase(){return base;} public double getHeight(){return height;}

    @Override public String getName(){ return "Triangle"; }
    @Override public String label(){ return "Triangle ("+(int)cx+","+(int)cy+")"; }
    @Override public Color getColor(){ return color; }
    @Override public void setColor(Color c){ this.color=c; }

    @Override public Shape copy(){ return new Triangle(cx,cy,base,height,color); }
    @Override public void moveBy(double dx,double dy){ cx+=dx; cy+=dy; }
    @Override public void scaleBy(double f){ base*=f; height*=f; }
}
