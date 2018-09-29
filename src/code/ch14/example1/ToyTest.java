package thinkingInJava.ch14.example1;

/**
 * @author yuzhe
 * @since 8/30/18
 */
public class ToyTest {

    static void printInfo(Class cc){
        System.out.println("class name: " + cc.getName() + "is interface? [" + cc.isInterface() + "]");
        System.out.println("simple name: " + cc.getSimpleName());
        System.out.println("canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("thinkingInJava.ch14.example1.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("cannot find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for(Class clazz : c.getInterfaces()){
            printInfo(clazz);
        }
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            obj = up.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("instantiate error");
            System.exit(1);
        }
        printInfo(obj.getClass());

        try {
            obj = boolean.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("boolean instantiate error");
            //基本类型木有默认构造器
        }

        try {
            obj = Boolean.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("boolean instantiate error");
            //基本类型木有默认构造器
        }

    }

}
