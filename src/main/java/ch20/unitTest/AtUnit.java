package ch20.unitTest;

import java.io.File;
import java.lang.reflect.Method;
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
        try {
            String cName = ClassNameFinder.thisClass(BinaryFile.read(file));
            if (cName.contains(".")) {
                return;
            } else {
                testClass = Class.forName(cName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        TestMethods testMethods = new TestMethods();
        Method creator = null;
        Method cleanup = null;
        for (Method m : testClass.getDeclaredMethods()) {

        }

    }

    static class TestMethods extends ArrayList<Method> {
        void addIfTestMethod(Method m){
            if(m.getAnnotation(Test.class) == null){
                return;
            }
            if(!(m.getReturnType().equals(boolean.class) || m.getReturnType().equals(void.class))){
                throw new RuntimeException("@Test method must return boolean or void type");
            }
            m.setAccessible(true);
            add(m);
        }
    }
}
