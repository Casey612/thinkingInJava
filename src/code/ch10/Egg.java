package thinkingInJava.ch10;

/**
 * @author yuzhe
 * @since 8/17/18
 */
public class Egg {

    private Yolk y;

    protected class Yolk {
        public Yolk(){
            System.out.println("Egg.Yolk()");
        }
    }

    public Egg(){
        System.out.println("new Egg()");
        this.y = new Yolk();
    }

}
