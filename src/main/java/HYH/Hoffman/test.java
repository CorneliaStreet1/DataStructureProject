package HYH.Hoffman;

import java.io.File;

public class test {

    public static void main(String arg[]){
        String path=".\\src\\main\\java\\HYH\\Hoffman";
        File file=new File((path+"\\1.txt"));
        File compress_file=new File(path+"\\1.hoff");
        Hoffman hoffman=new Hoffman();
        hoffman.compress(file,path);
        hoffman.decompress(compress_file,path);
    }
}
