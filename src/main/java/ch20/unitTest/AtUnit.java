package ch20.unitTest;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

        //确定待测试类
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
        //确认类中@Test的方法
        for (Method m : testClass.getDeclaredMethods()) {
            testMethods.addIfTestMethod(m);
            if (creator == null) {
                //是否@TestCreateObject方法
                creator = checkForCreatorMethod(m);
            }
            if (cleanup == null) {
                //是否@TestCleanupObject方法
                cleanup = checkForCleanupMethod(m);
            }
        }
        if (testMethods.size() > 0) {
            if (creator == null) {
                try {
                    if (!Modifier.isPublic(testClass.getDeclaredConstructor().getModifiers())) {
                        System.out.println("error: " + testClass + " default constructor must be public");
                    }
                    System.exit(1);
                } catch (NoSuchMethodException e) {
                    //synthesized default constructor : ok
                }
            }
            System.out.print(testClass.getName());
        }

        for(Method m : testMethods){
            System.out.println("    ." + m.getName() + " ");
            try{
                Object testObject = createTestObject(creator);
                boolean success = false;
                try{
                    if (m.getReturnType().equals(boolean.class)){
                        success = (boolean) m.invoke(testObject);
                    }else {
                        m.invoke(testMethods);
                        success = true;
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getCause());
                }
                System.out.println(success ? "" : "(failed)");
                //统计级信息
                testsRun++;
                if(!success){
                    failures++;
                    failedTests.add(testClass.getName() + ":" + m.getName());
                }
                if(cleanup != null){
                    cleanup.invoke(testObject, testMethods);
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

    }

    private static Method checkForCreatorMethod(Method m) {
        if (m.getAnnotation(TestObjectCreate.class) == null) {
            return null;
        }
        if (!m.getReturnType().equals(testClass)) {
            throw new RuntimeException("@TestObjectCreate must return instance of Class to be tested.");
        }
        if ((m.getModifiers() & Modifier.STATIC) < 1) {
            throw new RuntimeException("@TestObjectCreate must be static.");
        }
        m.setAccessible(true);
        return m;
    }

    private static Method checkForCleanupMethod(Method m) {
        if (m.getAnnotation(TestObjectCleanup.class) == null) {
            return null;
        }
        if (!m.getReturnType().equals(void.class)) {
            throw new RuntimeException("@TestObjectCleanup must return void.");
        }
        if ((m.getModifiers() & Modifier.STATIC) < 1) {
            throw new RuntimeException("@TestObjectCleanup must be static.");
        }
        if (m.getParameterTypes().length == 0 || m.getParameterTypes()[0] != testClass) {
            throw new RuntimeException("@TestObjectCleanup must take an argument of tested up.");
        }
        m.setAccessible(true);
        return m;
    }

    private static Object createTestObject(Method creator) {
        if (creator != null) {
            try {
                return creator.invoke(testClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("cannot run @TestObject(creator) method.", e);
            }
        } else {
            try {
                return testClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("cannot create a test object. Try using a @TestObject method");
            }
        }
    }


    static class TestMethods extends ArrayList<Method> {
        void addIfTestMethod(Method m) {
            if (m.getAnnotation(Test.class) == null) {
                return;
            }
            if (!(m.getReturnType().equals(boolean.class) || m.getReturnType().equals(void.class))) {
                throw new RuntimeException("@Test method must return boolean or void type");
            }
            m.setAccessible(true);
            add(m);
        }
    }
}
