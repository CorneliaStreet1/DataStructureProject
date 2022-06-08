package HYH.MultWayMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateBikeLinkFile {
    private int VetrexNum;
    private double speed;
    private char[][] map;

    CreateBikeLinkFile(int inVetrex,double inSpeed){
        VetrexNum=inVetrex;
        map=new char[inVetrex][inVetrex];
        speed=inSpeed;
    }

    void createUndirected(){
        createDirected();
        for(int i=0;i<VetrexNum;++i)
            for(int j=0;j<VetrexNum;++j)
                if(map[i][j]=='1')
                    map[j][i]='1';
    }

    void createDirected(){
        Random random=new Random();
        for(int i=0;i<VetrexNum;++i){
            for(int j=0;j<VetrexNum;++j){
                if(i==j){
                    map[i][j]='0';
                    continue;
                }
                boolean is_1=random.nextBoolean();
                if(is_1) map[i][j]='1';
                else map[i][j]='0';
            }
        }
    }

    void run(int method, File f){
        switch (method){
            case 1:createDirected(); break;
            case 2:createUndirected(); break;
        }
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(f));
            String words;
            words=String.valueOf(VetrexNum);
            writer.write(words);
            writer.write('\n');
            words=String.valueOf(speed);
            writer.write(words);
            writer.write('\n');
            for(int i=0;i<VetrexNum;++i){
                for(int j=0;j<VetrexNum;++j){
                    writer.write(map[i][j]);
                    writer.write(' ');
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("写入失败");
        }
    }

    public static void main(String[] args) {
        CreateBikeLinkFile cShaHe=new CreateBikeLinkFile(27,5);
        CreateBikeLinkFile cXiTuCheng =new CreateBikeLinkFile(30,5);
        File fShaHe=new File("src\\Main\\java\\HYH\\MultWayMap\\ShaHeMap\\bikeLink.txt");
        File fXiTuCheng=new File("src\\Main\\java\\HYH\\MultWayMap\\XiTuChengMap\\bikeLink.txt");
        if(!fShaHe.exists()){
            try {
                fShaHe.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!fXiTuCheng.exists()){
            try {
                fXiTuCheng.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cShaHe.run(2,fShaHe);
        cXiTuCheng.run(2,fXiTuCheng);
    }
}
