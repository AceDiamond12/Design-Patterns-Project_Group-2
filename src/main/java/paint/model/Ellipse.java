package paint.model;

import javafx.scene.paint.Color;

public class Ellipse implements Shape {
    private double cx,cy,rx,ry; private Color color;
    public Ellipse(double cx,double cy,double rx,double ry, Color c){
        this.cx=cx; this.cy=cy; this.rx=rx; this.ry=ry; this.color=c;
    }
    public double getCx(){return cx;} public double getCy(){return cy;}
    public double getRx(){return rx;} public double getRy(){return ry;}

    @Override public String getName(){ return "Ellipse"; }
    @Override public String label(){ return "Ellipse ("+(int)cx+","+(int)cy+")"; }
    @Override public Color getColor(){ return color; }
    @Override public void setColor(Color c){ this.color=c; }

    @Override public Shape copy(){ return new Ellipse(cx,cy,rx,ry,color); }
    @Override public void moveBy(double dx,double dy){ cx+=dx; cy+=dy; }
    @Override public void scaleBy(double f){ rx*=f; ry*=f; }
}
