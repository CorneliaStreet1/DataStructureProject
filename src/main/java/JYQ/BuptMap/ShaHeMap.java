package JYQ.BuptMap;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

public class ShaHeMap extends BuptGraph implements Serializable {
    public static final String[] buildings = {"学校大门","综合实验教学楼", "教室楼S1", "实验楼S2","实验楼S3","报告厅","东配楼","学院楼",
             "雁北园","雁南园","信息楼","综合办公室","教工食堂","学生食堂","南区食堂","西餐厅","中国邮政快递","小麦铺","足球场","排球场","网球场",
            "学生活动中心", "图书馆", "地下超市", "校医院", "篮球场", "菜鸟驿站"};
    private Map<Integer, String> IndexToBuilding;
    private Map<String , Integer> BuildingToIndex;
    public ShaHeMap() {
        this(0);
    }
    public ShaHeMap(int BuildingNum) {
        super(BuildingNum);
        IndexToBuilding = new HashMap<>();
        BuildingToIndex = new HashMap<>();
        this.init();
    }

    private void init() {
        for (int i = 0;i < buildings.length;i ++) {
            IndexToBuilding.put(i, buildings[i]);
            BuildingToIndex.put(buildings[i], i);
        }
    }

    /**
     * 给定沙河校区的某一个建筑，返回这个建筑在图中的编号
     * @param BuildingName 沙河校区中某个建筑的名称
     * @return 给定建筑在图中的编号
     */
    @Override
    public Integer getBuildingIndex(String BuildingName) {
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

/*    public static void main(String[] args) {
        ShaHeMap shaHeMap = new ShaHeMap(buildings.length);
        for (int i = 0 ; i < buildings.length ; i ++) {
            shaHeMap.IndexToBuilding.put(i, buildings[i]);
            shaHeMap.BuildingToIndex.put(buildings[i], i);
        }
        for (int i = 0 ; i < buildings.length; i ++) {
            System.out.println(shaHeMap.getBuildingName(i) + " " + i);
            System.out.println(shaHeMap.getBuildingIndex(shaHeMap.getBuildingName(i)) + " " + i);
        }
    }*/
}
