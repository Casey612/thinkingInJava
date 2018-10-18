package java.ch03;

/**
 * @author yuzhe
 * @since 7/31/18
 */
public class Equals {

    public static void main(String[] args) {
        Integer a = new Integer(47);
        Integer b = new Integer(47);
        Integer c = 47;
        Integer d = 47;
        Integer e = 200;
        Integer f = 200;
        System.out.println(a == b); //false
        System.out.println(a == c); //false
        System.out.println(b == c); //false
        System.out.println(c == d); //true 装箱 编译优化 -128 ~ 127 IntegerCache
        System.out.println(e == f); //false 装箱 编译优化 -128 ~ 127 超出范围 IntegerCache
        System.out.println(a.equals(b));
    }

}
