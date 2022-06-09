package JYQ.DistanceGraph;

import JYQ.Directories;
import JYQ.Utils;

import java.io.*;

public class Demo {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("dict.dic"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      String s;
        try {
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      /*  char c;
        int end = 1;
        try {
            while (end != -1) {
                end = reader.read();
                if (end == -1) {
                    break;
                }
                System.out.print((char)end);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
