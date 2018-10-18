package java.ch08;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Amphibian extends Animal{

    private Characteristic p = new Characteristic("can live in the water");
    private Description t = new Description("both water and land");

    public Amphibian() {
        System.out.println("Amphibian()");
    }

    @Override
    protected void dispose(){
        System.out.println("Amphibian dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }

}
