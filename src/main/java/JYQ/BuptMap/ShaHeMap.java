package JYQ.BuptMap;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

public class ShaHeMap extends BuptGraph implements Serializable {
    public static final String[] buildings = {"综合实验教学楼N楼", "综合实验教学楼S楼", "教室楼S1", "实验楼S2和S3",
             "雁北园A和B和C区","雁北园D2和E区","雁南园S4和S5","雁南园S6","信息楼S1","综合办公室","教工食堂","学生食堂","南区食堂",
            "学生活动中心", "图书馆", "地下超市", "小麦铺", "运动场", "菜鸟驿站"};
    private Map<Integer, String> IndexToBuilding;
    private Map<String , Integer> BuildingToIndex;
    public ShaHeMap(int BuildingNum) {
        super(BuildingNum);
        IndexToBuilding = new HashMap<>();
        BuildingToIndex = new HashMap<>();
    }

    /**
     * 给定沙河校区的某一个建筑，返回这个建筑在图中的编号
     * @param BuildingName 沙河校区中某个建筑的名称
     * @return 给定建筑在图中的编号
     */
    public int getBuildingIndex(String BuildingName) {
        return this.BuildingToIndex.get(BuildingName);
    }

    /**
     * 给定沙河校区的某一个建筑在图中的编号，返回这个建筑的名字
     * @param index 沙河校区的某一个建筑在图中的编号
     * @return 给定这个建筑的名字
     */
    public String getBuildingName(int index) {
        return this.IndexToBuilding.get(index);
    }
    public static void main(String[] args) {
        ShaHeMap shaHeMap = new ShaHeMap(buildings.length);
        for (int i = 0 ; i < buildings.length ; i ++) {
            shaHeMap.IndexToBuilding.put(i, buildings[i]);
            shaHeMap.BuildingToIndex.put(buildings[i], i);
        }
        for (int i = 0 ; i < buildings.length; i ++) {
            System.out.println(shaHeMap.getBuildingName(i) + " " + i);
            System.out.println(shaHeMap.getBuildingIndex(shaHeMap.getBuildingName(i)) + " " + i);
        }
    }
}
