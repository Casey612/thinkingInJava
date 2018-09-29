package thinkingInJava.ch10;

/**
 * @author yuzhe
 * @since 8/17/18
 */
public class BigEgg extends Egg {

    private Yolk yolk;

    public class Yolk{
        public Yolk(){
            System.out.println("BigEgg.Yolk()");
        }
    }

    public BigEgg(){
        super();
        this.yolk = new Yolk();
    }

    public static void main(String[] args) {
        new BigEgg();
    }

}
