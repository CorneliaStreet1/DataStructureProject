package JYQ.DistanceGraph;

import JYQ.Directories;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(Directories.MapRepo.getAbsolutePath() + "\\30.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 /*       String s;
        try {
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();*/
        char c;
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
        }
    }
}
