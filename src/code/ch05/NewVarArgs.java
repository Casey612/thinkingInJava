package thinkingInJava.ch05;

/**
 * @author yuzhe
 * @since 8/1/18
 */
public class NewVarArgs {

    public static void printArray(Object... args) {
        for (Object arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {
        printArray((Object[]) new Integer[]{1 , 2, 3});
    }

}
