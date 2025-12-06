package paint.model;

import javafx.scene.paint.Color;

public class Square implements Shape {
    private double x,y,side; private Color color;
    public Square(double x,double y,double side, Color c){ this.x=x; this.y=y; this.side=side; this.color=c; }
    public double getX(){return x;} public double getY(){return y;} public double getSide(){return side;}
    @Override public String getName(){ return "Square"; }
    @Override public String label(){ return "Square ("+(int)x+","+(int)y+")"; }
    @Override public Color getColor(){ return color; }
    @Override public void setColor(Color c){ this.color=c; }
    @Override public Shape copy(){ return new Square(x,y,side,color); }
    @Override public void moveBy(double dx,double dy){ x+=dx; y+=dy; }
    @Override public void scaleBy(double f){ side*=f; }
}
