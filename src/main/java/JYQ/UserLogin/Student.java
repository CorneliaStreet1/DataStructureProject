package JYQ.UserLogin;

import JHY.Course;
import JHY.RegularTable;
import JYQ.Directories;
import JYQ.Utils;

import java.io.File;

public class Student extends UserInformation{
    private int ClassNumber;
    public Student(String UserName, String PassWord, Boolean isStudent,int ClassNumber) {
        super(UserName, PassWord, isStudent);
        this.ClassNumber = ClassNumber;
    }
    public int getClassNumber() {
        return ClassNumber;
    }
    public void addCourse(Course course,int day, int seq) {
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + this.ClassNumber);
        File UserDir = Utils.join(ClassDir, this.getUserName());
        File tableFile = Utils.join(UserDir, "StudentRegularTable");
        RegularTable regularTable = Utils.readObject(tableFile, RegularTable.class);
        regularTable.addLesson(day, seq, course);
        Utils.writeObject(tableFile, regularTable);
    }
    public void deleteCourse(int day, int seq) {
        File ClassDir = Utils.join(Directories.UserFiles, "Class" + this.ClassNumber);
        File UserDir = Utils.join(ClassDir, this.getUserName());
        File tableFile = Utils.join(UserDir, "StudentRegularTable");
        RegularTable regularTable = Utils.readObject(tableFile, RegularTable.class);
        regularTable.deleteLesson(day,seq);
        Utils.writeObject(tableFile, regularTable);
    }
}
