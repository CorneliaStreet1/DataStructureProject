package JYQ.BuptMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class XiTuChengMap extends BuptGraph implements Serializable {
    public static final String[] buildings = {"教一楼", "教二楼", "教三楼", "教四楼", "主楼", "辅导员基地", "行政楼", "后勤处",
            "学一", "学二", "学三", "学四","图书馆", "体育馆", "游泳馆", "校医院", "澡堂", "邮局", "学生食堂", "学苑餐厅", "家属区",
    };
    private Map<Integer, String> IndexToBuilding;
    private Map<String, Integer> BuildingToIndex;
    public XiTuChengMap(int BuildingNum) {
        super(BuildingNum);
        IndexToBuilding = new HashMap<>();
        BuildingToIndex = new HashMap<>();
    }

    /**
     * 给定西土城校区的某一个建筑，返回这个建筑在图中的编号
     * @param BuildingName 西土城校区中某个建筑的名称
     * @return 给定建筑在图中的编号
     */
    public int getBuildingIndex(String BuildingName) {
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
