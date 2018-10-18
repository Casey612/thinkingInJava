package ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class DotNew {

    public class Inner {

    }

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Inner inner = dn.new Inner();
    }

}
