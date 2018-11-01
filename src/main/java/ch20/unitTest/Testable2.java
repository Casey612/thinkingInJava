package ch20.unitTest;


/**
 * @author: yuki
 * @date: 2018/11/1
 */
public class Testable2 {

    public void execute(){
        System.out.println("executing ...");
    }

    @Test
    void testExecute(){
        execute();
    }

}
