package code.ch08;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Characteristic {

    private String s;

    public Characteristic(String s){
        this.s = s;
        System.out.println("create Characteristic " + s);
    }

    protected void dispose(){
        System.out.println("disposing Characteristic" + s);
    }

}
