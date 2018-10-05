package code.ch03;

/**
 * @author yuzhe
 * @since 8/1/18
 */
public class Binary {

    public static void main(String[] args) {
        Integer a = Integer.parseInt("101010101", 2);
        Integer b = Integer.parseInt("010101010", 2);
        System.out.println("a = " + Integer.toBinaryString(a));
        System.out.println("b = " + Integer.toBinaryString(b));
        System.out.println("a | b = " + Integer.toBinaryString(a | b));
        System.out.println("a & b = " + Integer.toBinaryString(a & b));
        System.out.println("~a = " + Integer.toBinaryString(~a));
        System.out.println("a ^ b = " + Integer.toBinaryString(a ^ b));
    }

}
