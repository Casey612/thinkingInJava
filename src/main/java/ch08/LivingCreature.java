package ch08;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class LivingCreature {

    private Characteristic p = new Characteristic("is alive");
    private Description t  = new Description("basic living creature");

    public LivingCreature(){
        System.out.println("create living creature");
    }

    protected void dispose(){
        System.out.println("living creature dispose");
        t.dispose();
        p.dispose();
    }


}
