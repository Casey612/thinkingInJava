package thinkingInJava.ch08;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Animal extends LivingCreature {

    private Characteristic p = new Characteristic("has heart");
    private Description t = new Description("animal not vegetable");

    public Animal() {
        System.out.println("animal()");
    }

    @Override
    protected void dispose(){
        System.out.println("animal dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }

}
