# 更新了什么

## Package DistanceGraph

- 就是以距离为权的地图

- 需要你们关注的只有一个方法，用于获取两个建筑之间的距离的

- 给定两个建筑的编号，返回这两个建筑的距离（要求是相连的两个点嗷）

  ```java
  public int getBuildingDistance(int V1, int V2)
  ```


# 儿童节和端午节的更新

## Package PathUtil

### Class PathUtils

1. 给定建筑物名称，直接得到终点编号（不需要考虑在哪个校区），得到编号后可以根据编号的范围是小于27（沙河）还是大于29（本部）来判断是哪个校区

   - ```java
      public static int getBuildingIndexByLocation(String location) 
     ```

2. 给定课程名称，直接得到终点编号（不需要考虑在哪个校区），得到编号后可以根据编号的范围是小于27（沙河）还是大于29（本部）来判断是哪个校区

   - ```java
     public static int getCourseBuildingIndex(String CourseName) 
     ```

3. 给定课程名称，查询课程所在的建筑物的名称

   - ```java
     public static String getCourseBuilding(String CourseName)
     ```

4. 给定上课时间，查询离给定时间最近的课程所在的建筑物

   - **请注意：由于这个函数对输入的时间的格式(HH mm)有要求，所以交互的部分我直接做了。嵌在里面了**。直接调用即可，交互不用做了。

   - 其他几个都需要你们自己做下图类似的交互，然后把参数传递给方法，得到返回值。

   - 大概长这样：

     - <img src="https://raw.githubusercontent.com/CorneliaStreet1/PictureBed/master/202206031948397.png" alt="image-20220603194819238"  />

   - ```java
     public static int getBuildingIndexByTime()
     ```


## Package ShortestDistancePathFinder

### Class TestMap

- 一个用于测试的简单无向图，以距离为权的时候，紫色的是最短路径树，起点是0。
- 图比较简单，没有做邻接矩阵，手动硬编码的。
- <img src="https://raw.githubusercontent.com/CorneliaStreet1/PictureBed/master/202206031954194.png" alt="image-20220603195416828" style="zoom: 50%;" />



### Class ShortestPathFinder

- Dijkstra的实现类，没什么会被外界调用的方法。可以参考一下实现
- 最短路径的实现可以参考[Shortest Path](https://www.youtube.com/watch?v=iMoFtG1md3w&feature=youtu.be&t=5m03s)
- 最短路径的动画[demo](https://docs.google.com/presentation/d/1_bw2z1ggUkquPdhl7gwdVBoTaoJmaZdpkV6MoAgxlJc/pub?start=false&loop=false&delayms=3000)
- 需要科学上网

### Class Navigation

- 用于完成在三种策略下的距离最短导航

1. 传入起点和终点建筑的编号（就是沙河编号在0-26，西土城编号在30-59的那个编号就可以），用打印控制台消息的方式将路径打印出来。

   - 已经考虑过起点和终点在不同校区的情况了，传进去起点和终点的编号，其他的它会处理好。

   - ```java
      public static void Navigator(int Source, int Dest)
     ```

