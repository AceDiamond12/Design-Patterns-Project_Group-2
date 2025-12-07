package paint.strategy;

import paint.model.Shape;
import java.io.File;
import java.util.List;

// [STRATEGY PATTERN] Interface for saving/loading algorithms
// [نمط Strategy] واجهة لخوارزميات الحفظ والتحميل
public interface FileStrategy {
    void save(List<Shape> shapes, File file);
    List<Shape> load(File file);
}