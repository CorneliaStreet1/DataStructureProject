package HYH;

public class System_main extends System_models{
    public System_main(String s){
        super(s);
        add_model("1",new CourseManager("course"));
        //如上把各模块加到主模块上
    }


    public void add_model(String i, System_model m) {
        super.add_model(i, m);
    }


    public void run() throws Close {
        super.run();
    }

    public static void main(String arg[]){
        System_main centre=new System_main(" ");
        System_log log=new System_log(" ");//只写了个框架
        try{
            while(true){
                log.run();
                centre.run();

            }
        }catch (Close a){

        }
    }
}
