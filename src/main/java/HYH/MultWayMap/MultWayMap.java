package HYH.MultWayMap;

import JYQ.DijkstraUtils.*;
import JYQ.BuptMap.*;
import JYQ.DistanceGraph.*;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultWayMap implements DijkstraGraph{
    enum Transport{WALK,BIKE};
    class vPoint{
        private ArrayList<WeigtedEgde> neighbors;
        private Map<Integer,Transport> method;

        vPoint(){
            neighbors=new ArrayList<WeigtedEgde>();
            method=new HashMap<Integer, HYH.MultWayMap.MultWayMap.Transport>();
        }

        void addNeighbor(int v,double w,Transport m){
            WeigtedEgde weigtedEgde=new WeigtedEgde(v,w);
            neighbors.add(weigtedEgde);
            method.put(v,m);
        }
    }

    private ArrayList<vPoint> vPoints;
    private int VetrexNum;
    private ShaHeDistanceGraph shaheMap;
    private XiTuChengDistanceGraph xituchengMap;
    private static String path="src\\main\\java\\HYH\\MultWayMap";
    private static String pathSha="ShaHeMap";
    private static String pathXi="XiTuChengMap";

    MultWayMap(int mapkind){
        vPoints=new ArrayList<>();

        shaheMap=ShaHeDistanceGraph.ReadInGraph();
        xituchengMap=XiTuChengDistanceGraph.ReadInGraph();
        init(mapkind);
    }

    int getKind(int v1,int v2){
        int value=1;
         if(vPoints.get(v1).method.get(v2)==Transport.WALK) value=1;
         else if(vPoints.get(v1).method.get(v2)==Transport.BIKE) value=2;
         return value;
    }

    void init(int mapkind){
        File fLink,fBike,fCrowd;
        String fLinkPath=null,fBikePath=null,fCrowdPath=null;
        switch (mapkind){
            case 1:
                fLinkPath="UserFiles\\MapRepo\\27.txt";
                fBikePath="src\\main\\java\\HYH\\MultWayMap\\ShaHeMap\\bikeLink.txt";
                fCrowdPath="src\\main\\java\\HYH\\MultWayMap\\ShaHeMap\\crowdWeight.txt";
                break;
            case 2:
                fLinkPath="UserFiles\\MapRepo\\30.txt";
                fBikePath="src\\main\\java\\HYH\\MultWayMap\\XiTuChengMap\\bikeLink.txt";
                fCrowdPath="src\\main\\java\\HYH\\MultWayMap\\XiTuChengMap\\crowdWeight.txt";
                break;
        }
        fLink=new File(fLinkPath);
        fBike=new File(fBikePath);
        fCrowd=new File(fCrowdPath);
        try {
            BufferedReader readerLink=new BufferedReader(new FileReader(fLink));
            BufferedReader readerBike=new BufferedReader(new FileReader(fBike));
            BufferedReader readerCrowd=new BufferedReader(new FileReader(fCrowd));

            readerBike.readLine();
            readerCrowd.readLine();

            double walkSpeed=1;
            double bikeSpeed=Double.parseDouble(readerBike.readLine());

            bikeSpeed=1.1;
            //System.out.println("bikeSpeed:"+bikeSpeed);

            double fastSpeed;
            Transport tkind;

            if(mapkind==1) VetrexNum=27;
            else if(mapkind==2) VetrexNum=30;

            for(int i=0;i<VetrexNum;++i){
                vPoint tempPoint=new vPoint();
                vPoints.add(tempPoint);
                String[] crowds=readerCrowd.readLine().split(" ");

                for(int j=0;j<VetrexNum;++j){
                    char tLink=(char)readerLink.read();
                    char tBike=(char)readerBike.read();
                    readerBike.read();//读空格

                   // System.out.println("i:"+i+",j:"+j+",tLink:"+tLink);
                    if(tBike!='0'&&tBike!='1') System.out.println("i:"+i+",j:"+j+",error:"+tBike);

                    if(tLink=='1'){
                        fastSpeed=walkSpeed;
                        tkind=Transport.WALK;
                        if(tBike=='1' && bikeSpeed>fastSpeed){
                            fastSpeed=bikeSpeed;
                            tkind=Transport.BIKE;
                        }
                        double distance=0;
                        if(mapkind==1) distance=shaheMap.getBuildingDistance(i,j);
                        else if(mapkind==2) distance=xituchengMap.getBuildingDistance(i,j);

                        double crowdWeight=Double.parseDouble(crowds[j]);
                        double fastTime=distance/(fastSpeed*crowdWeight);
                        tempPoint.addNeighbor(j,fastTime,tkind);
                    }
                }
                readerLink.read();//要读末尾的换行和回车
                readerLink.read();
                readerBike.read();//为什么27.txt要读两次，我就要读一次就行了
                //System.out.println("final2:"+readerBike.read());
                //System.out.println("final2:"+readerCrowd.read());//crowd不用读回车，因为前面是readline
            }
            readerLink.close();
            readerBike.close();
            readerCrowd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ioError");
        }

    }


    @Override
    public int getVertices() {
        return VetrexNum;
    }

    @Override
    public WeigtedEgde[] Adjcents(int bestNode) {
        WeigtedEgde[] egdes=new WeigtedEgde[vPoints.get(bestNode).neighbors.size()];
        vPoints.get(bestNode).neighbors.toArray(egdes);
        return egdes;
    }
}
