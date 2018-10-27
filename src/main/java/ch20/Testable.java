package ch20;

import org.junit.Test;

/**
 * @author: yuki
 * @date: 2018/10/27
 */
public class Testable {

    public void execute(){
        System.out.println("Executing...");
    }

    @Test
    public void testExecute(){
        execute();
    }

}
