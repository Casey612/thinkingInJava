package ch20;

import org.junit.Test;

/**
 * @author: yuki
 * @date: 2018/10/29
 */
public class AtUnitExample1 {

    public String methodOne(){
        return "This is methodOne";
    }

    public int methodTwo(){
        System.out.println("this is methodTwo");
        return 2;
    }

    @Test
    public boolean methodOneTest(){
        return methodOne().equals("This is methodOne");
    }

    @Test
    public boolean m2(){
        return methodTwo() == 2;
    }

}
