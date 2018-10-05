package code.ch03;

/**
 * @author yuzhe
 * @since 7/31/18
 */
public class Literals {

    public static void main(String[] args) {
        int i1 = 0x2f;
//        System.out.println("i1: " + Integer.toBinaryString(i1));

        int i2 = 0x2F;
//        System.out.println("i2: " + Integer.toBinaryString(i2));

        int i3 = 0177;
//        System.out.println("i3: " + Integer.toBinaryString(i3));

        char c = 0xffff;
//        System.out.println("c: " + Integer.toBinaryString(c));

        byte b = 0x7f;
//        System.out.println("b: " + Integer.toBinaryString(b));

        short s = 0x7fff;
//        System.out.println("s: " + Integer.toBinaryString(s));

        long n1 = 100L;
        long n2 = 100l;
        long n3 = 100;

        float f1 =1;
        float f2 =1f;
        float f3 =1F;

        double d1 = 1d;
        double d2 = 1D;
    }

}
