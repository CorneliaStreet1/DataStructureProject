package JYQ.PathUtil;

import JHY.Course;
import JYQ.BuptMap.ShaHeMap;
import JYQ.BuptMap.XiTuChengMap;
import JYQ.Directories;
import JYQ.Utils;

import java.io.File;

public class PathUtils {
    public static int getCourseBuildingIndex(String CourseName , boolean isShahe) {
        if (isShahe)
            return (new ShaHeMap()).getBuildingIndex(PathUtils.getCourseBuilding(CourseName));
        else {
            return (new XiTuChengMap()).getBuildingIndex(PathUtils.getCourseBuilding(CourseName));
        }
    }
    public static String getCourseBuilding(String CourseName) {
        File CourseFile = Utils.join(Directories.CourseRepo, CourseName);
        Course course = Utils.readObject(CourseFile,Course.class);
        return course.getBuildingName();
    }
}
