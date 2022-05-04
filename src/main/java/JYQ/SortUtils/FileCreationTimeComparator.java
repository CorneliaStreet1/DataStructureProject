package JYQ.SortUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

public class FileCreationTimeComparator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        int r = 0;
        try {
            BasicFileAttributes b1 = Files.readAttributes(o1.toPath(), BasicFileAttributes.class);
            BasicFileAttributes b2 = Files.readAttributes(o2.toPath(), BasicFileAttributes.class);
            r = b1.creationTime().compareTo(b2.creationTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }
 }
