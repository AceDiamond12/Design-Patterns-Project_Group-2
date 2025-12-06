package paint.model;

import javafx.scene.paint.Color;

public class Line implements Shape {
    private double x1,y1,x2,y2; private Color color;
    public Line(double x1,double y1,double x2,double y2, Color c){
        this.x1=x1; this.y1=y1; this.x2=x2; this.y2=y2; this.color=c;
    }
    public double getX1(){return x1;} public double getY1(){return y1;}
    public double getX2(){return x2;} public double getY2(){return y2;}

    @Override public String getName(){ return "Line"; }
    @Override public String label(){ return "Line ("+(int)x1+","+(int)y1+")"; }
    @Override public Color getColor(){ return color; }
    @Override public void setColor(Color c){ this.color=c; }

    @Override public Shape copy(){ return new Line(x1,y1,x2,y2,color); }
    @Override public void moveBy(double dx,double dy){ x1+=dx; y1+=dy; x2+=dx; y2+=dy; }
    @Override public void scaleBy(double f){ x2 = x1 + (x2-x1)*f; y2 = y1 + (y2-y1)*f; }
}
