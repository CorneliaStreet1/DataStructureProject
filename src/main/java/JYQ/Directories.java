package JYQ;

import java.io.File;

public class Directories {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File UserFiles = Utils.join(CWD, "UserFiles");
    public static final File UserRepo = Utils.join(UserFiles, "UserRepo");
    public static final File CourseRepo = Utils.join(UserFiles, "CourseRepo");
    public static final File ExamRepo = Utils.join(UserFiles, "ExamRepo");
    public static final File HomeworkRepo = Utils.join(UserFiles, "HomeworkRepo");
   // public static final File CompressedRepo = Utils.join(UserFiles,"CompressedRepo");
    public static final File MaterialRepo = Utils.join(UserFiles, "MaterialRepo");
    public static final File MapRepo = Utils.join(UserFiles,"MapRepo");
    public static final File DistanceMap = Utils.join(MapRepo, "DistanceMap");
}
