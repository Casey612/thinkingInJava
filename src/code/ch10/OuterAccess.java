package thinkingInJava.ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class OuterAccess {

    class Inner {
        private String s;

        public Inner(String s) {
            this.s = s;
        }
    }

    public static void main(String[] args) {
        OuterAccess outer = new OuterAccess();
        Inner inner = outer.new Inner("yuki");
    }

}
