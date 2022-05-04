package JYQ.SortUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class FIleSizeComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        long l = 0;
        try {
            BasicFileAttributes b1 = Files.readAttributes(o1.toPath(), BasicFileAttributes.class);
            BasicFileAttributes b2 = Files.readAttributes(o2.toPath(), BasicFileAttributes.class);
            l = b1.size() - b2.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (l > 0) {
            return 1;
        }
        else if (l < 0) {
            return -1;
        }
        return 0;
    }
}
