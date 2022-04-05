package JYQ.Demos;

import JYQ.Utils;

import java.io.File;
import java.io.IOException;

import JYQ.DuplicateRate.*;
public class DuplicateCheckingDemo {
    public static void main(String[] args) throws IOException {
        File CWD = new File(System.getProperty("user.dir"));
        File file1 = Utils.join(CWD, "时间线.md");
        File file2 = Utils.join(CWD, "需求.md");
        double r = DuplicateUtils.getAnalysisResult(file1.toString(), file2.toString());
        System.out.println(r);
    }
}
