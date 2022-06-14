package JHY;

import HYH.Information.Information;
import java.io.Serializable;

public class Course extends Information implements Serializable {
    String name;
    String ClassRoomName;
    String GroupInformation;
    String BuildingName;

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    public Course() {
    }

    public Course(String name, String ClassRoomName, String buildingName) {
        this.name = name;
        this.ClassRoomName = ClassRoomName;
        this.BuildingName = buildingName;
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRoomName() {
        return ClassRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.ClassRoomName = classRoomName;
    }

    public String getGroupInformation() {
        return GroupInformation;
    }

    public void setGroupInformation(String groupInformation) {
        GroupInformation = groupInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Course) {
            Course course = (Course) o;
            return this.getName().equals(course.getName());
        }
        return false;
    }
}
