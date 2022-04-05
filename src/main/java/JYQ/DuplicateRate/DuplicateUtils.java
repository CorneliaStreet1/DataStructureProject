package JYQ.DuplicateRate;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class DuplicateUtils {
    public static double getAnalysisResult(String s1, String s2) throws IOException {
        //输出的单位：B字节
        //System.out.println(f.length());

        //when the size of the file is small we use the Consine to obtain the similarity (accuracy)
        // and is large we use the simHash to get that (fast)
        double Result = 0.0;
        try{

            File f = new File(s1);
            File f1 = new File(s2);
            FileReader reader = new FileReader(f);
            FileReader reader1 = new FileReader(f1);
            if (f.length()!=0 && f1.length()!=0){
                if (f.length()<=30000){
                    double sim = 100* CalculateSimilar.getSimilar(reader,reader1);
                    DecimalFormat df = new DecimalFormat();
                    String s = df.format(sim);
                    Result = Double.parseDouble(s);
                    System.out.println("本次查重的结果为："+ s+"%");
                    return Result;
                   // writeResultToFile(s1,s2,s3,s);
                }else {
                    double sim = SimHash.Construct(reader,reader1);
                    Result = sim * 100;
                    System.out.println("本次查重的结果为："+sim*100+"%");
                    return Result;
                   // writeResultToFile(s1,s2,s3,String.valueOf(sim));
                }
            }else {
                System.out.println("文件为空，请重新输入比较文本");
                System.exit(1);
            }
        }catch (Exception e){
            System.out.println("文件路径有误，请重新输入");
        }
        return Result;
    }
   /* public static void writeResultToFile(String origin, String compared,String path, String result) throws IOException {
        FileWriter writer = new FileWriter(new File(path),true);
        if (writer!=null){
            StringBuilder buffer = new StringBuilder();
            buffer.append("The origin text:");
            buffer.append(origin);
            buffer.append("\r\n");
            buffer.append("The compared text:");
            buffer.append(compared);
            buffer.append("\r\n");
            buffer.append("The compare result is: ");
            buffer.append(result+"%");
            buffer.append("\r\n");
            buffer.append("\r\n");
            writer.write(buffer.toString());
            writer.flush();
        }else {
            System.out.println("文件写入失败，请检查输入的文件是否存在...");
        }
        writer.close();
    }*/
}
