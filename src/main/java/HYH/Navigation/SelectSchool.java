package HYH.Navigation;

//选校区
public class SelectSchool extends SearchModel{

    public SelectSchool(String s) {
        super(s);
    }

    @Override
    public boolean Search() {
        System.out.println("请选择校区");
        System.out.println("输入\"1\": 沙河校区");
        System.out.println("输入\"2\": 西土城校区");
        String i=super.scanner.nextLine();
        SCHOOL temp;
        switch (i){
            case "1":temp=SCHOOL.ShaHe; return true;
            case "2":temp=SCHOOL.XiTuCheng; return true;
            default: return false;
        }
    }

    @Override
    public void ErrorPrint() {
        System.out.println("输入错误，请选择");
    }
}
