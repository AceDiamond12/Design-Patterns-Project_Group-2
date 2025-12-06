package paint.strategy;

import paint.model.Shape;
import java.io.File;
import java.util.List;

public interface FileStrategy {
    void save(List<Shape> shapes, File file);
    // You could also add 'load' here
}