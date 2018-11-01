package ch20.unitTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yuki
 * @date: 2018/11/1
 */
public class AtUnit implements ProcessFiles.Strategy {

    static Class<?> testClass;
    static List<String> failedTests = new ArrayList<>();
    static long testsRun = 0;
    static long failures = 0;

    @Override
    public void process(File file) {
        try{
        } catch (Exception e){

        }
    }
}
