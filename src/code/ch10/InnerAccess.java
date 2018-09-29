package thinkingInJava.ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class InnerAccess {

    private static int i = 10;

    private int getI() {
        return i;
    }

    private class Inner {
        public InnerAccess getOuter(){
            return InnerAccess.this;
        }

        public void changeOuterI(int i){
            getOuter().i = i;
        }
    }

    public static void main(String[] args) {
        InnerAccess access = new InnerAccess();
        Inner inner = access.new Inner();
        System.out.println(access.getI());
        inner.changeOuterI(12);
        System.out.println(access.getI());
    }

}
