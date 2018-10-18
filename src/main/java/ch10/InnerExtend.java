package ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class InnerExtend {

    class Inner {
    }

    public class InheritInner extends InnerExtend.Inner{
        InheritInner(InnerExtend innerExtend){
            innerExtend.super();
        }
    }

    public static void main(String[] args) {
        InnerExtend innerExtend = new InnerExtend();
        InheritInner inheritInner = innerExtend.new InheritInner(innerExtend);
    }

}
