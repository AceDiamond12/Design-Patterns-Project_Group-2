package paint.strategy;

import paint.model.*;
import javafx.scene.paint.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Concrete Strategy: Saves as Text
// استراتيجية ملموسة: الحفظ كنص
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

    // Helper to write properties of each shape
    // دالة مساعدة لكتابة خصائص كل شكل
    private void writeShape(PrintWriter writer, Shape s) {
        if (s instanceof ShapeGroup group) {
            // Flatten group for saving
            // حفظ محتويات المجموعة
            for (Shape child : group.getChildren()) {
                writeShape(writer, child);
            }
            return;
        }
        // Write format: Type,x,y,dims...,color
        // الكتابة بصيغة: النوع، الإحداثيات، الأبعاد، اللون
        String color = s.getColor().toString();
        if (s instanceof Rectangle r) {
            writer.printf("Rectangle,%f,%f,%f,%f,%s\n", r.getX(), r.getY(), r.getW(), r.getH(), color);
        } 
        // ... (Logic for other shapes) ...
        // ... (منطق بقية الأشكال) ...
    }

    @Override
    public List<Shape> load(File file) {
        List<Shape> loadedShapes = new ArrayList<>();
        // Logic to read line by line and recreate objects
        // منطق قراءة الملف سطر بسطر وإعادة إنشاء الكائنات
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                // Parse parts and create shapes...
                // تحليل النص وإنشاء الأشكال...
                // (Refer to previously provided full code for details)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedShapes;
    }
}