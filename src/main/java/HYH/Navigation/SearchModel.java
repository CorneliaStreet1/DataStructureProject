package HYH.Navigation;

import HYH.Model.*;

import java.util.Scanner;

//搜索模块
public class SearchModel extends System_models {
    private static RepeatModel repeatModel=new RepeatModel("重新输入");
    enum SCHOOL{ShaHe,XiTuCheng};
    public Scanner scanner;
    private static SCHOOL school=SCHOOL.ShaHe;

    public SearchModel(String s) {
        super(s);
        scanner=new Scanner(System.in);
        add_model("r",repeatModel);
    }

    @Override
    public void run() throws Close {
        boolean isRun=true;
        while (isRun){
            if(Search()) isRun=false;
            else{
                ErrorPrint();
                ErrorSelect();
            }
        }
    }
    public boolean Search() throws Close{
        return false;
    }
    public void ErrorPrint(){
        System.out.println("没有搜索到，请选择");
    }
    public void ErrorSelect() throws Close{
        boolean isRun=true;
        while(isRun) {
            print_model();
            if(select_model(scanner.nextLine())) isRun=false;
        }
    }
    public void WriteSchool(SCHOOL i){
        school=i;
    }
    public SCHOOL readSchool(){return school;}
}
