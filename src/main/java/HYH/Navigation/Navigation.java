package HYH.Navigation;

import HYH.Model.*;


import java.util.Scanner;

public class Navigation extends Total_models{
    private Guide guide;

    public Navigation(String s) {
        super(s);
        guide=new Guide("导航");
        add_model("1",guide);
    }



}
