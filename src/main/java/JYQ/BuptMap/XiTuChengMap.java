package JYQ.BuptMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class XiTuChengMap extends BuptGraph implements Serializable {
    public static final String[] buildings = {"西土城学校大门","教一楼", "教二楼","教三楼","教四楼","科学会堂","主楼", "辅导员基地", "行政楼", "后勤处",
            "学一公寓", "学二公寓","学三公寓","学四公寓","西土城图书馆", "体育馆", "游泳馆", "篮球场","排球场","校医院", "澡堂", "中国邮政快递", "菜鸟驿站","学生食堂",
            "学苑餐厅","教师食堂","书店","车库","学生活动中心","学苑超市"
    };
    private Map<Integer, String> IndexToBuilding;
    private Map<String, Integer> BuildingToIndex;
    public XiTuChengMap() {
        this(0);
    }
    public XiTuChengMap(int BuildingNum) {
        super(BuildingNum);
        IndexToBuilding = new HashMap<>();
        BuildingToIndex = new HashMap<>();
        this.init();
    }

    private void init() {
        for (int i = 0;i < buildings.length;i ++) {
            IndexToBuilding.put(i + 30, buildings[i]);
            BuildingToIndex.put(buildings[i], i + 30);
        }
    }

    /**
     * 给定西土城校区的某一个建筑，返回这个建筑在图中的编号
     * @param BuildingName 西土城校区中某个建筑的名称
     * @return 给定建筑在图中的编号
     */
    @Override
    public Integer getBuildingIndex(String BuildingName) {
        return this.BuildingToIndex.get(BuildingName);
    }

    /**
     * 给定西土城校区的某一个建筑在图中的编号，返回这个建筑的名字
     * @param index 西土城校区的某一个建筑在图中的编号
     * @return 给定建筑在图中的编号
     */
    public String getBuildingName(int index) {
        return this.IndexToBuilding.get(index);
    }
}
