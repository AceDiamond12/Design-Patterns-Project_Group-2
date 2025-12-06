package paint.strategy;

import paint.model.Shape;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class TxtSaveStrategy implements FileStrategy {
    @Override
    public void save(List<Shape> shapes, File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Shape s : shapes) {
                // Format: Name:Color
                writer.println(s.label() + ":" + s.getColor().toString());
            }
            System.out.println("Saved to " + file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}