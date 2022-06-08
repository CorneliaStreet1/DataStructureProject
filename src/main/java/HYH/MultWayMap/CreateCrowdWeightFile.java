package HYH.MultWayMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateCrowdWeightFile {
    private int VetrexNum;
    private double[][] map;

    CreateCrowdWeightFile(int inVetrex){
        VetrexNum=inVetrex;
        map=new double[VetrexNum][VetrexNum];
    }

    void create(){
        Random random=new Random();
        for(int i=0;i<VetrexNum;++i)
            for(int j=0;j<VetrexNum;++j) {
                map[i][j] = random.nextDouble();
                if(map[i][j]<0.1) map[i][j]+=0.1;
            }
    }

    void run(File f){
        create();
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(f));
            String words;
            words=String.valueOf(VetrexNum);
            writer.write(words);
            writer.write('\n');
            for(int i=0;i<VetrexNum;++i){
                for(int j=0;j<VetrexNum;++j){
                    words=String.valueOf(map[i][j]);
                    writer.write(words.substring(0,3));
                    writer.write(' ');
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CreateCrowdWeightFile cShaHe=new CreateCrowdWeightFile(27);
        CreateCrowdWeightFile cXiTuCheng=new CreateCrowdWeightFile(30);
        File fShaHe=new File("src\\Main\\java\\HYH\\MultWayMap\\ShaHeMap\\crowdWeight.txt");
        File fXiTuCheng=new File("src\\Main\\java\\HYH\\MultWayMap\\XiTuChengMap\\crowdWeight.txt");
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
        cShaHe.run(fShaHe);
        cXiTuCheng.run(fXiTuCheng);
    }

}
