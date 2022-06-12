package JYQ.CourseManager;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;
import HYH.System_main;
import HYH.System_time.System_time;
import JYQ.Directories;
import JYQ.SortUtils.HeapSort;
import JYQ.SortUtils.StringLengthComparator;
import JYQ.UserLogin.Student;
import JYQ.UserLogin.UserInformation;
import JYQ.Utils;
import java.io.File;
import net.lingala.zip4j.*;
import JHY.*;
import net.lingala.zip4j.exception.ZipException;

public class CourseManager {
    public static void addNewCourse(Course course) {
        Scanner scanner = new Scanner(System.in);
       if (CourseManager.hasCourse(course)) {
            System.out.println("您所添加的课程已经存在于课程系统中，如果继续添加将会覆盖原有的课程，继续吗?(y继续，n终止)");
            String y = scanner.next();
            if (y.compareToIgnoreCase("n") == 0) {
                return;
            }
        }
        File CourseRepo = Directories.CourseRepo;
        if (!CourseRepo.exists()) {
            CourseRepo.mkdir();
        }
        File courseFile = Utils.join(CourseRepo, course.getName());
        Utils.writeObject(courseFile, course);
    }
    public static void deleteCourse(Course course) {
        File CourseRepo = Directories.CourseRepo;
        File courseFile = Utils.join(CourseRepo, course.getName());
        if (!CourseManager.hasCourse(course)) {
            System.out.println("您所要删除的课程不存在！");
            return;
        }
        else {
            Utils.restrictedDelete(courseFile);
        }
    }
    public static boolean hasCourse(Course course) {
        if (course == null) {
            return false;
        }
        else {
            File courseFile = Utils.join(Directories.CourseRepo, course.getName());
            return courseFile.exists();
        }
    }
    public static void Interface() {
        Scanner scanner = new Scanner(System.in);
        File CurrentUserFile = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        UserInformation CurrentUser = Utils.readObject(CurrentUserFile, UserInformation.class);
        if (CurrentUser.isStudent()) {
            Student student = (Student) CurrentUser;
            int option;
            System.out.println("欢迎进入课程管理模块。您现在处于学生端");
            System.out.println("您在本模块中可以做的事有:");
            System.out.println("1.查询课程信息");
            System.out.println("2.上传作业");
            System.out.println("3.查询已交作业或待交作业以及作业的详细信息");
            System.out.println("4.查询考试相关信息");
            System.out.println("5.上传课程资料");
            System.out.println("6.查看课程资料");
            System.out.println("请输入您想要的功能的序号:");
            try {
                option = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("您输入的不是整数，请重新输入:");
                scanner.nextLine();
                option = scanner.nextInt();
            }
            if (option == 1) {
                CourseTable courseTable = new CourseTable();
                File classDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
                File ClassTableFile = Utils.join(classDir, "RegularTable");
                File studentDir = Utils.join(classDir,student.getUserName());
                File studentTableFile = Utils.join(studentDir, "StudentRegularTable");
                RegularTable StudentTable = Utils.readObject(studentTableFile, RegularTable.class);
                RegularTable ClassTable = Utils.readObject(ClassTableFile, RegularTable.class);
                courseTable.getCompleteTable(ClassTable, StudentTable);
                int MethodOption;
                System.out.println("您选择的是查询课程信息功能，在本功能中您可以选择两种查询方式:");
                System.out.println("1.按照课程名称查询");
                System.out.println("2.通过课表查询");
                System.out.println("请输入您想要的查询方式的序号:");
                try {
                    MethodOption = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("您输入的不是整数序号，请重新输入整数序号:");
                    scanner.nextLine();
                    MethodOption = scanner.nextInt();
                }
                if (MethodOption == 1) {
                    System.out.println("请输入要查询的课程的完整名称:");
                    String CourseName = scanner.next();
                    CourseManager.SearchCourse(CourseName, courseTable);
                }
                else {
                    courseTable.PrintTable();
                    System.out.println("课程的时间段信息可以从课表中查到。您要查哪一门课的具体信息?");
                    System.out.println("请输入课程的完整名称：");
                    String cn = scanner.next();
                    CourseManager.SearchCourse(cn, courseTable);
                }
            }
            else if (option == 2) {
                CourseManager.uploadHomework();
            }
            else if (option == 3) {
                CourseManager.SearchHomework();
            }
            else if (option == 4) {
                CourseManager.SearchExam();
            }
            else if (option == 5) {
                CourseManager.UploadMaterialAsStudent();
            }
            else if (option == 6) {
                CourseManager.CheckoutMaterialAsStudent();
            }
        }
        //管理员端
        else {
            System.out.println("欢迎进入课程管理模块。您现在处于管理员端");
            System.out.println("您可以做的事情有：");
            System.out.println("1.为某个班级添加课程");
            System.out.println("2.为某个班级删除课程");
            System.out.println("3.为某个课程添加考试相关信息");
            System.out.println("4.为某个课程添加课程群信息");
            System.out.println("5.为某个课程添加新的作业");
            System.out.println("6.为某个课程添加资料");
            System.out.println("请输入您想使用的功能的序号:");
            int option;
            try {
                option = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("不要输入奇怪的东西啊喂，输个数字好不好，拜托，你真是太逊了。");
                scanner.nextLine();
                option = scanner.nextInt();
            }
            if (option == 1) {
                System.out.println("请输入您想添加课程的班级的序号(阿拉伯数字，不要奇怪的东西)");
                int num;
                try {
                    num = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("都说了不要奇怪的东西，你真的太逊了，重新输入吧。");
                    scanner.nextLine();
                    num = scanner.nextInt();
                }
                CourseManager.addCourseForClass(num);
            }
            else if (option == 2){
                System.out.println("请输入您想删除课程的班级的序号(阿拉伯数字，不要奇怪的东西)");
                int num;
                try {
                    num = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("都说了不要奇怪的东西，你真的太逊了，重新输入吧。");
                    scanner.nextLine();
                    num = scanner.nextInt();
                }
                CourseManager.deleteClassForClass(num);
            }
            else if (option == 3) {
                CourseManager.addExamForCourse();
            }
            else if (option == 4) {
                CourseManager.addGroupInformation();
            }
            else if (option == 5) {
                CourseManager.addHomeworkForCourse();
            }
            else if (option == 6) {
                CourseManager.UploadMaterialAsManager();
            }
        }
    }
    public static void CheckoutMaterialAsStudent() {
        Scanner scanner = new Scanner(System.in);
        File CurrentUser = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        Student student = Utils.readObject(CurrentUser, Student.class);
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
        File ClassMaterialRepo = Utils.join(ClassDir, "ClassMaterialRepo");
        System.out.println("请注意：你只能查看由管理员以及你的同班同学上传的课程资料");
        System.out.println("请输入你想要查看资料的课程名称:");
        String CourseName = scanner.next();
        if (!CourseManager.hasCourse(new Course(CourseName))) {
            System.out.println("您指定的课程在系统中不存在");
        }
        else {
            File ManagerMaterial = Utils.join(Directories.MaterialRepo, CourseName);
            File ClassMaterial = Utils.join(ClassMaterialRepo, CourseName);
            if (!ManagerMaterial.exists() && ClassMaterial.exists()) {
                System.out.println("管理员并没有为" + CourseName + "添加课程资料");
                List<String> ClassFile = Utils.plainFilenamesIn(ClassMaterial);
                System.out.println("由你所属班级的同学"+ CourseName + "上传的课程资料有:" + ClassFile);
            }
            else if (!ClassMaterial.exists() && ManagerMaterial.exists()) {
                System.out.println("你所属的班级目前没有同学为" + CourseName + "上传课程资料");
                List<String> ManagerFile = Utils.plainFilenamesIn(ManagerMaterial);
                System.out.println("由管理员"+ CourseName + "添加的课程资料有:" + ManagerFile);
            }
            else if (!ManagerMaterial.exists() && !ClassMaterial.exists()) {
                System.out.println("目前系统中没有" + CourseName + "的课程资料");
            }
            else if (ManagerMaterial.exists() && ClassMaterial.exists()) {
                List<String> ManagerFile = Utils.plainFilenamesIn(ManagerMaterial);
                List<String> ClassFile = Utils.plainFilenamesIn(ClassMaterial);
                System.out.println("由管理员"+ CourseName + "添加的课程资料有:" + ManagerFile);
                System.out.println("由你所属班级的同学"+ CourseName + "上传的课程资料有:" + ClassFile);
            }
        }
    }
    public static void UploadMaterialAsStudent() {
        Scanner scanner = new Scanner(System.in);
        File CurrentUser = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        Student student = Utils.readObject(CurrentUser, Student.class);
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
        File c = Utils.join(ClassDir, "ClassMaterialRepo");
        System.out.println("请输入你想要添加资料的课程名称:");
        String CourseName = scanner.next();
        if (!CourseManager.hasCourse(new Course(CourseName))) {
            System.out.println("您指定的课程在系统中不存在");
        }
        else {
            if (!c.exists()) {
                c.mkdir();
            }
            File CourseMaterialRepo = Utils.join(c, CourseName);
            if (!CourseMaterialRepo.exists()) {
                CourseMaterialRepo.mkdir();
            }
            System.out.println("请输入资料文件的绝对路径:");
            System.out.println("给定一个绝对路径的例子，请参照例子进行输入:D:\\DataStructureProject\\UserFiles\\CourseRepo\\计算机网络.txt");
            scanner.nextLine();
            String Path = scanner.nextLine();
            File CommitFile = new File(Path);
            if (!CommitFile.isFile()) {
                System.out.println("您输入的是一个目录，不是一个文件，请重新输入:");
                Path = scanner.nextLine();
                CommitFile = new File(Path);
            }
            try {
                Files.copy(CommitFile.toPath(), CourseMaterialRepo.toPath().resolve(CommitFile.getName()));
            }
            catch (IOException e) {
                System.out.println("上传课程资料失败，请检查是否存在如下情况后重试:");
                System.out.println("1.此资料文件此前已经上传过一次");
                System.out.println("2.此资料文件不存在");
                System.exit(1);
            }
            try {
                new ZipFile(CourseMaterialRepo.toPath().resolve(CommitFile.getName()) + ".zip").addFile(CommitFile);
                System.out.println("上传的课程资料已被压缩保存至" + CourseMaterialRepo.toPath().resolve(CommitFile.getName()) + ".zip");
            } catch (ZipException e) {
                System.out.println("压缩文件失败");
                e.printStackTrace();
            }
            System.out.println("添加资料成功");
        }
    }
    public static void UploadMaterialAsManager() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你想要添加资料的课程名称:");
        String CourseName = scanner.next();
        if (!CourseManager.hasCourse(new Course(CourseName))) {
            System.out.println("您指定的课程在系统中不存在");
        }
        else {
            if (!Directories.MaterialRepo.exists()) {
                Directories.MaterialRepo.mkdir();
            }
            // ./计算机网络
            File CourseMaterialDir = Utils.join(Directories.MaterialRepo, CourseName);
            if (!CourseMaterialDir.exists()) {
                CourseMaterialDir.mkdir();
            }
            System.out.println("请输入资料文件的绝对路径:");
            System.out.println("给定一个绝对路径的例子，请参照例子进行输入:D:\\DataStructureProject\\UserFiles\\CourseRepo\\计算机网络.txt");
            scanner.nextLine();
            String Path = scanner.nextLine();
            File CommitFile = new File(Path);
            if (!CommitFile.isFile()) {
                System.out.println("您输入的是一个目录，不是一个文件，请重新输入:");
                Path = scanner.nextLine();
                CommitFile = new File(Path);
            }
            try {
                Files.copy(CommitFile.toPath(), CourseMaterialDir.toPath().resolve(CommitFile.getName()));
            }
            catch (IOException e) {
                System.out.println("上传资料失败，请检查是否存在如下情况后重试:");
                System.out.println("1.此资料文件此前已经上传过一次");
                System.out.println("2.此资料文件不存在");
                System.exit(1);
            }
            try {
                new ZipFile(CourseMaterialDir.toPath().resolve(CommitFile.getName()) + ".zip").addFile(CommitFile);
                System.out.println("上传的资料已被压缩保存至" + CourseMaterialDir.toPath().resolve(CommitFile.getName()) + ".zip");
            } catch (ZipException e) {
                System.out.println("压缩文件失败");
                e.printStackTrace();
            }
            System.out.println("添加资料成功");
        }
    }
    @SuppressWarnings("unchecked")
    public static void SearchHomework() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你想要查询作业提交情况的课程名称:");
        String CourseName = scanner.next();
        if (!CourseManager.hasCourse(new Course(CourseName))) {
            System.out.println("您指定的课程在系统中不存在");
        }
        else {
            File HomeworkDir = Utils.join(Directories.HomeworkRepo, CourseName);
            if (!HomeworkDir.exists()) {
                System.out.println("您所查询的课程目前还没有布置作业");
            }
            else {
                //作业总仓中指定课程的所有作业
                List<String> Homeworks = Utils.plainFilenamesIn(HomeworkDir);
                System.out.println("本门课程中布置的所有作业有：");
                System.out.println(Homeworks);
                File CurrentUser = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
                Student student = Utils.readObject(CurrentUser, Student.class);
                //Class8
                File ClassDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
                //Class8//jyq
                File UserDir = Utils.join(ClassDir, student.getUserName());
                // jyq//CommitRepo
                File CommitRepo = Utils.join(UserDir, "CommitRepo");
                File UserHomeworkDir = Utils.join(CommitRepo, CourseName);
                File file = Utils.join(UserHomeworkDir, "HandedHomeworkList");
                if (!CommitRepo.exists() || !UserHomeworkDir.exists() || !file.exists()) {
                    System.out.println("您目前还没有提交过作业");
                }
                else {
                    System.out.println("你想按照哪种顺序查看？1.默认顺序2.名称顺序(输入序号)");
                    ArrayList<String> UploadedWork = Utils.readObject(file, ArrayList.class);
                    int op = scanner.nextInt();
                    if (op == 1) {System.out.println("请注意：相同作业名重复出现的次数代表这个作业的提交次数");
                        System.out.println("目前你已经提交的作业有:");
                        System.out.println(UploadedWork);
                    }
                    else {
                        System.out.println("请注意：相同作业名重复出现的次数代表这个作业的提交次数");
                        HeapSort<String> sort = new HeapSort<>(new StringLengthComparator());
                        List<String> list = sort.HeapSort(UploadedWork);
                        System.out.println("目前你已经提交的作业有:");
                        System.out.println(list);
                    }
                }
                System.out.println("是否需要查看某次作业的详细信息?(y/n)");
                String y = scanner.next();
                if (y.compareToIgnoreCase("y") == 0) {
                    System.out.println("请输入你想查询的作业名称(exit退出):");
                    while (scanner.hasNext()) {
                        String name = scanner.next();
                        if (name.equals("exit")) {
                            break;
                        }
                        // 计算机网络//第一次作业
                        File WorkFile = Utils.join(HomeworkDir, name);
                        Homework homework = Utils.readObject(WorkFile, Homework.class);
                        System.out.println(homework);
                        System.out.println("请输入你想查询的作业名称(exit退出):");
                    }
                }
            }
        }
    }
    @SuppressWarnings("unchecked")
    public static void uploadHomework() {
        Scanner scanner = new Scanner(System.in);
        File CurrentUser = Utils.join(Directories.UserRepo, System_main.CurrentUserName);
        Student student = Utils.readObject(CurrentUser, Student.class);
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + student.getClassNumber());
        File UserDir = Utils.join(ClassDir, student.getUserName());
        File HomeWorkRepo = Utils.join(UserDir, "CommitRepo");
        if (!HomeWorkRepo.exists()) {
            HomeWorkRepo.mkdir();
        }
        System.out.println("请输入你想提交作业的课程的完整名称(exit退出):");
        String CourseName = scanner.next();
        if (CourseName.equals("exit")) {
            return;
        }
        if (!CourseManager.hasCourse(new Course(CourseName))) {
            System.out.println("您输入的课程在系统中不存在");
        }
        else {
            File HomeworkDir = Utils.join(Directories.HomeworkRepo, CourseName);
            List<String> HomeworkList = Utils.plainFilenamesIn(HomeworkDir);
            // CommitRepo//计算机网络
            File CourseDir = Utils.join(HomeWorkRepo,CourseName);
            if (!CourseDir.exists()) {
                CourseDir.mkdir();
            }
            File HandedHomeworkListFile = Utils.join(CourseDir, "HandedHomeworkList");
            ArrayList<String> HandedHomeworkList;
            if (!HandedHomeworkListFile.exists()) {
                Utils.writeObject(HandedHomeworkListFile, new ArrayList<String>());
            }
            HandedHomeworkList = Utils.readObject(HandedHomeworkListFile, ArrayList.class);
            System.out.println(CourseName + "目前所布置的所有作业有:");
            System.out.println(HomeworkList);
            System.out.println("您要提交的是哪一个作业?(请输入作业名)");
            System.out.println("请注意，如果提交相同名字的作业文件，则默认保存最新提交的。");
            String WorkName = scanner.next();
            assert HomeworkList != null;
            if (!HomeworkList.contains(WorkName)) {
                System.out.println("输入的作业不存在，请重新输入：");
                WorkName = scanner.next();
            }
            System.out.println("请输入作业文件的绝对路径:");
            System.out.println("给定一个绝对路径的例子，请参照例子进行输入:D:\\DataStructureProject\\UserFiles\\CourseRepo\\计算机网络.txt");
            scanner.nextLine();
            String Path = scanner.nextLine();
            File CommitFile = new File(Path);
            if (!CommitFile.isFile()) {
                System.out.println("您输入的是一个目录，不是一个文件，请重新输入:");
                Path = scanner.nextLine();
                CommitFile = new File(Path);
            }
            try {
                Files.copy(CommitFile.toPath(), CourseDir.toPath().resolve(CommitFile.getName()));
            }
            catch (IOException e) {
                System.out.println("上传作业失败，请检查是否存在如下情况后重试:");
                System.out.println("1.此作业文件此前已经上传过一次");
                System.out.println("2.此作业文件不存在");
                System.exit(1);
            }
            try {
                new ZipFile(CourseDir.toPath().resolve(CommitFile.getName()) + ".zip").addFile(CommitFile);
                System.out.println("上传的作业已被压缩保存至" + CourseDir.toPath().resolve(CommitFile.getName()) + ".zip");
            } catch (ZipException e) {
                System.out.println("压缩文件失败");
                e.printStackTrace();
            }
            HandedHomeworkList.add(WorkName);
            Utils.writeObject(HandedHomeworkListFile, HandedHomeworkList);
            System.out.println("提交作业成功");
        }
    }
    public static void addHomeworkForCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你想要添加作业的课程名称:");
        String CourseName = scanner.next();
        if (!CourseManager.hasCourse(new Course(CourseName))) {
            System.out.println("您指定的课程在系统中不存在");
        }
        else {
            if (!Directories.HomeworkRepo.exists()) {
                Directories.HomeworkRepo.mkdir();
            }
            File HomeworkDir = Utils.join(Directories.HomeworkRepo, CourseName);
            if (!HomeworkDir.exists()) {
                HomeworkDir.mkdir();
            }
            System.out.println("请输入您要添加的作业的名称(exit退出):");
            while (scanner.hasNext()) {
                String WorkName = scanner.next();
                File WorkFile = Utils.join(HomeworkDir,WorkName);
                if (WorkFile.exists()) {
                    System.out.println("同名作业已经存在，将直接覆盖，是否重新命名?(y,n)");
                    String y = scanner.next();
                    if (y.compareToIgnoreCase("y") == 0) {
                        System.out.println("请输入新的名字:");
                        WorkName = scanner.next();
                    }
                }
                if (WorkName.equals("exit")) {
                    break;
                }
                scanner.nextLine();
                System.out.println("请输入本次作业的内容(exit退出):");
                String WorkContent = scanner.nextLine();
                if (WorkContent.equals("exit")) {
                    break;
                }
                System.out.println("请输入作业的开始日期和时间:");
                LocalDateTime StartTime = CourseManager.getDateTime();
                System.out.println("请输入作业的截止日期和时间:");
                LocalDateTime endTime = CourseManager.getDateTime();
                Homework homework = new Homework(WorkName, CourseName, StartTime, endTime, WorkContent);
                Utils.writeObject(WorkFile, homework);
                System.out.println("作业" + WorkName + "添加成功");
                System.out.println("请输入下一个您要添加的作业的名称(exit退出):");
            }
            System.out.println("添加作业成功");
        }
    }
    private static LocalDateTime getDateTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请用阿拉伯数字依次输入年(yyyy)  月(MM)   日(dd) 小时(HH) 分钟(mm)");
        String year, month, day, hour, min;
        year = scanner.next();
        month = scanner.next();
        day = scanner.next();
        hour = scanner.next();
        min = scanner.next();
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(min));
    }
    public static void SearchExam() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要查询考试的课程的完整名称:");
        String name = scanner.next();
        if (!CourseManager.hasCourse(new Course(name))) {
            System.out.println("您要查询的课程在系统中不存在");
        }
        else if (!Directories.ExamRepo.exists()) {
            System.out.println("目前所有科目都不存在考试");
        }
        else {
            File ExamDir = Utils.join(Directories.ExamRepo, name);
            if (!ExamDir.exists()) {
                System.out.println("您查询的科目不存在考试");
            }
            else {
                System.out.println("你想按照什么顺序进行排序？1.名称长度2.添加的时间(请输入选项号,其他选项默认为2顺序)");
                int op = scanner.nextInt();
                List<String> examNames = Utils.plainFilenamesIn(ExamDir);
                System.out.println("查询到的考试信息如下:");
                if (op == 1) {
                    HeapSort<String> sort = new HeapSort<>(new StringLengthComparator());
                    List<String> list = sort.HeapSort(examNames);
                    assert examNames != null;
                    for (String examName: list) {
                        File examFile = Utils.join(ExamDir, examName);
                        Exam exam = Utils.readObject(examFile, Exam.class);
                        System.out.println(exam);
                    }
                }
                else {
                    assert examNames != null;
                    for (String examName : examNames) {
                        File examFile = Utils.join(ExamDir, examName);
                        Exam exam = Utils.readObject(examFile, Exam.class);
                        System.out.println(exam);
                    }
                }
                System.out.println("当前系统时间是:" + (new System_time("s").returnTime()));
                System.out.println("请注意辨别哪些考试是已经过期的");
            }
        }
    }
    public static void SearchCourse(String CourseName, CourseTable courseTable) {
        File CourseFile = Utils.join(Directories.CourseRepo, CourseName);
        Course course = Utils.readObject(CourseFile, Course.class);
        System.out.println("查询到的课程信息如下:");
        System.out.println("课程名称: " + course.getName());
        System.out.println("上课地点: "+ course.getBuildingName() + course.getClassRoomName());
        LinkedList<TableInformation> coursesList = new LinkedList<>();
        for ( LinkedList<TableInformation> l:courseTable.getCourseTable()) {
            for ( TableInformation t: l) {
                if ((new Course(CourseName)).equals(t.getCourse())) {
                    coursesList.add(t);
                }
            }
        }
        if (coursesList.isEmpty()) {
            System.out.println("未从您的课表中查询到上课时间");
        }
        else {
            System.out.println("在您的课表中查询到的上课时间:");
            System.out.println(coursesList);
        }
        if (course.getGroupInformation() == null) {
            System.out.println("未查询到相关的课程群信息");
        }
        else {
            System.out.println("有关的课程群信息: " + course.getGroupInformation());
        }
    }
    public static void addCourseForClass(int ClassNum) {
        Scanner scanner = new Scanner(System.in);
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + ClassNum);
        File ClassRegularTable = Utils.join(ClassDir, "RegularTable");
        RegularTable regularTable = Utils.readObject(ClassRegularTable, RegularTable.class);
        System.out.println("请输入您想为" + ClassNum +"班添加的课程(输入exit结束): ");
        System.out.println("请按照课程名称 周几(一个阿拉伯数字即可) 第几节(一个阿拉伯数字即可)的格式来输入。");
        while (scanner.hasNext()) {
            String name,Day,Seq;
            int day;
            int seq;
            try {
                name = scanner.next();
                if (name.equals("exit")) {
                    break;
                }
                Day = scanner.next();
                if (Day.equals("exit")) {
                    break;
                }
                Seq = scanner.next();
                if (Seq.equals("exit")) {
                    break;
                }
                day = Integer.parseInt(Day);
                seq = Integer.parseInt(Seq);

            }
            catch (InputMismatchException e) {
                System.out.println("您输入的格式有误，请重新输入:");
                name = scanner.next();
                if (name.equals("exit")) {
                    break;
                }
                Day = scanner.next();
                if (Day.equals("exit")) {
                    break;
                }
                Seq = scanner.next();
                if (Seq.equals("exit")) {
                    break;
                }
                day = Integer.parseInt(Day);
                seq = Integer.parseInt(Seq);
            }
            File courseFile = Utils.join(Directories.CourseRepo, name);
            if (!courseFile.exists()) {
                System.out.println("您输入的课程在课程库中不存在，请重新输入:");
            }
            else {
                Course course = Utils.readObject(courseFile, Course.class);
                regularTable.addLesson(day, seq, course);
            }
        }
        Utils.writeObject(ClassRegularTable,regularTable);
        System.out.println("添加课程成功");
    }
    public static void deleteClassForClass(int ClassNum) {
        Scanner scanner = new Scanner(System.in);
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + ClassNum);
        File ClassRegularTable = Utils.join(ClassDir, "RegularTable");
        RegularTable regularTable = Utils.readObject(ClassRegularTable, RegularTable.class);
        System.out.println("请依次输入两个阿拉伯数字，代表周几和第几节课(输入exit结束)");
        while (scanner.hasNext()) {
            String day = scanner.next();
            if (day.equals("exit")) {
                break;
            }
            String seq = scanner.next();
            if (seq.equals("exit")) {
                break;
            }
            regularTable.deleteLesson(Integer.parseInt(day), Integer.parseInt(seq));
        }
        Utils.writeObject(ClassRegularTable, regularTable);
        System.out.println("删除课程成功");
    }
    public static void addExamForCourse() {
        File ExamRepo = Directories.ExamRepo;
        if (!ExamRepo.exists()) {
            ExamRepo.mkdir();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您想添加考试的课程的完整名称");
        String CourseName = scanner.next();
        File courseFile = Utils.join(Directories.CourseRepo,CourseName);
        Course course = Utils.readObject(courseFile, Course.class);
        if (!CourseManager.hasCourse(course)) {
            System.out.println("您输入的课程在系统中不存在，已退出。");
            return;
        }
        File ExamDir = Utils.join(ExamRepo, CourseName);
        if (!ExamDir.exists()) {
            ExamDir.mkdir();
        }
        System.out.println("接下来请输入考试的信息，如果有多场考试，输入完毕后输入exit退出");
        System.out.println("请输入考试的名称(如期中考试，exit退出):");
        while (scanner.hasNext()) {
            String ExamName = scanner.next();
            if (ExamName.equals("exit")) {
                break;
            }
            File examFile = Utils.join(ExamDir, ExamName);
            if (examFile.exists()) {
                System.out.println("请注意：相同名称的考试已存在，将会直接覆盖同名考试");
            }
            System.out.println("请输入考试的地点(exit退出):");
            String ExamAddress = scanner.next();
            if (ExamAddress.equals("exit")) {
                break;
            }
            System.out.println("请用阿拉伯数字依次输入年(yyyy)  月(MM)   日(dd) 小时(HH) 分钟(mm) (exit退出)：");
            String year, month, day, hour, min;
            LocalDateTime localDateTime;
                year = scanner.next();
                month = scanner.next();
                day = scanner.next();
                hour = scanner.next();
                min = scanner.next();
                localDateTime = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(min));
            System.out.println("请用阿拉伯数字输入考试的时长(单位:分钟)(exit退出):");
            String time = scanner.next();
            if (time.equals("exit")) {
                break;
            }
            int t = Integer.parseInt(time);
            Exam exam = new Exam(ExamAddress, localDateTime,ExamName,t);
            Utils.writeObject(examFile, exam);
            System.out.println("请输入考试的名称(如期中考试,exit退出):");
        }
        System.out.println("添加考试成功");
    }
    public static void addGroupInformation() {
        System.out.println("请输入您添加课程群信息的课程的完整名称。");
        Scanner scanner = new Scanner(System.in);
        String CourseName = scanner.next();
        File courseFile = Utils.join(Directories.CourseRepo,CourseName);
        Course course = Utils.readObject(courseFile, Course.class);
        if (!CourseManager.hasCourse(course)) {
            System.out.println("您输入的课程不存在，已退出。");
            return;
        }
        System.out.println("请输入课程群信息");
        scanner.nextLine();
        String info = scanner.nextLine();
        course.setGroupInformation(info);
        Utils.writeObject(courseFile, course);
        System.out.println("添加课程信息成功。");
    }
    public static void HomeWorkDuplicateChecking() {

    }
    public static void main(String[] args) {
       Course[] courses= {new Course("计算机组成原理","S208","综合实验教学楼"),
        new Course("计算机网络", "N516","综合实验教学楼"),
        new Course("毛泽东思想和中国特色社会主义理论体系概论","E431", "综合实验教学楼"),
        new Course("体育基础（男篮）","运动场", "综合实验教学楼"),
        new Course("形式语言与自动机", "S510", "综合实验教学楼"),
        new Course("面向对象程序设计Java","N201", "综合实验教学楼"),
        new Course("数据结构课程设计","N207", "综合实验教学楼"),
        new Course("面向对象程序设计C++","S301", "综合实验教学楼"),
        new Course("离散数学","S201","综合实验教学楼"),
        new Course("数学建模","N201","综合实验教学楼"),
        new Course("数据库原理","N309","教一楼")
       };
    /* for (int i = 0 ; i< courses.length ; i ++) {
            CourseManager.addNewCourse(courses[i]);
        }*/
       // deleteCourse(courses[1]);
      CourseManager.Interface();
       /* File jsjwl = Utils.join(Directories.CourseRepo, "计算机网络");
        Course course = Utils.readObject(jsjwl, Course.class);
        System.out.println(course.getExams());
        System.exit(0);*/
    }
}
