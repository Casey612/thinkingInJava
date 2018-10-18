package ch08;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Description {

    private String s;

    public Description(String s){
        this.s = s;
    }

    protected void dispose(){
        System.out.println("disposing Description " + s);
    }

}
