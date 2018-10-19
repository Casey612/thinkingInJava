package ch19;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public enum EnumConstructorAccess {

    ONE("one", 1),
    TWO("tow", 2);

    private int i;
    private String s;

    EnumConstructorAccess(String s, int i) {
        this.i = i;
        this.s = s;
    }

    public static void main(String[] args) {
        //compile error. enum types cannot be instantiated.
//        EnumConstructorAccess test = new EnumConstructorAccess("three", 3);
    }
}
