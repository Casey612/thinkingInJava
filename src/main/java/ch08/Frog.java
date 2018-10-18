package ch08;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Frog extends Amphibian{

    private Characteristic p = new Characteristic("croaks");
    private Description t = new Description("eats bugs");

    public Frog() {
        System.out.println("frog()");
    }

    @Override
    protected void dispose(){
        System.out.println("frog dispose");
        t.dispose();
        p.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        Frog frog = new Frog();
        System.out.println("bye");
        frog.dispose();
    }

}
