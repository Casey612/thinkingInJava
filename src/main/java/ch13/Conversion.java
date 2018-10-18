package ch13;

import java.util.Formatter;

/**
 * @author yuzhe
 * @since 8/28/18
 */
public class Conversion {

    public static void main(String[] args) {
        Formatter formatter = new Formatter(System.out);

        char c = 'a';
        System.out.println("----char c = 'a'----");
        formatter.format("s: %s\n", c);
//        formatter.format("d: %d\n", c); //IllegalFormatConversionException 运行时错误
        formatter.format("c: %c\n", c);
        formatter.format("b: %b\n", c);
//        formatter.format("f: %f\n", c);
//        formatter.format("e: %e\n", c);
//        formatter.format("x: %x\n", c);
        formatter.format("h: %h\n", c);

        System.out.println("----int i = 0----");
        int i = 0;
        formatter.format("s: %s\n", i);
        formatter.format("d: %d\n", i);
        formatter.format("c: %c\n", i);
        formatter.format("b: %b\n", i);
//        formatter.format("f: %f\n", i);
//        formatter.format("e: %e\n", i);
        formatter.format("x: %x\n", i);
        formatter.format("h: %h\n", i);
    }

}
