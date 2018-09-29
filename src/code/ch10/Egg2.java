package thinkingInJava.ch10;

/**
 * @author yuzhe
 * @since 8/17/18
 */
public class Egg2 {

    protected class Yolk {
        public Yolk() {
            System.out.println("Egg2.yolk()");
        }

        public void f() {
            System.out.println("Egg2.yolk.f()");
        }
    }

    private Yolk yolk = new Yolk();

    public Egg2() {
        System.out.println("new Egg2()");
    }

    public void insertYolk(Yolk yy) {
        this.yolk = yy;
    }

    public void g() {
        yolk.f();
    }

}
