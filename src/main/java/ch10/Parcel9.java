package java.ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class Parcel9 {

    public Dest desc(final String arg){
        return new Dest(){
            private String s = arg;
            public String label(){
                return s;
            }
        };
    }

    private class Dest {
    }
}
