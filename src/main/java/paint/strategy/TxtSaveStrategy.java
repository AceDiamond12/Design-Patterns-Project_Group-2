package paint.strategy;

import paint.model.*;
import javafx.scene.paint.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtSaveStrategy implements FileStrategy {

    @Override
    public void save(List<Shape> shapes, File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Shape s : shapes) {
                writeShape(writer, s);
            }
            System.out.println("Saved to " + file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper to handle saving different types, including Groups
    private void writeShape(PrintWriter writer, Shape s) {
        if (s instanceof ShapeGroup group) {
            // For simplicity, we save the children of the group
            for (Shape child : group.getChildren()) {
                writeShape(writer, child);
            }
            return;
        }

        String color = s.getColor().toString();
        
        if (s instanceof Rectangle r) {
            writer.printf("Rectangle,%f,%f,%f,%f,%s\n", r.getX(), r.getY(), r.getW(), r.getH(), color);
        } else if (s instanceof Square sq) {
            writer.printf("Square,%f,%f,%f,%s\n", sq.getX(), sq.getY(), sq.getSide(), color);
        } else if (s instanceof Circle c) {
            writer.printf("Circle,%f,%f,%f,%s\n", c.getCx(), c.getCy(), c.getR(), color);
        } else if (s instanceof Ellipse e) {
            writer.printf("Ellipse,%f,%f,%f,%f,%s\n", e.getCx(), e.getCy(), e.getRx(), e.getRy(), color);
        } else if (s instanceof Line l) {
            writer.printf("Line,%f,%f,%f,%f,%s\n", l.getX1(), l.getY1(), l.getX2(), l.getY2(), color);
        } else if (s instanceof Triangle t) {
            writer.printf("Triangle,%f,%f,%f,%f,%s\n", t.getCx(), t.getCy(), t.getBase(), t.getHeight(), color);
        }
    }

    @Override
    public List<Shape> load(File file) {
        List<Shape> loadedShapes = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String type = parts[0];
                Color color = Color.valueOf(parts[parts.length - 1]);

                switch (type) {
                    case "Rectangle":
                        loadedShapes.add(new Rectangle(
                            Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), color));
                        break;
                    case "Square":
                        loadedShapes.add(new Square(
                            Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), color));
                        break;
                    case "Circle":
                        loadedShapes.add(new Circle(
                            Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), color));
                        break;
                    case "Ellipse":
                        loadedShapes.add(new Ellipse(
                            Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), color));
                        break;
                    case "Line":
                        loadedShapes.add(new Line(
                            Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), color));
                        break;
                    case "Triangle":
                        loadedShapes.add(new Triangle(
                            Double.parseDouble(parts[1]), Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), color));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedShapes;
    }
}