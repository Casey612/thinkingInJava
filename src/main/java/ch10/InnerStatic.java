package java.ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class InnerStatic {

    private String name;
    private static String desc;

    static class Inner{
        public String superName(){
            //no keyword this
            //cannot access to outer no-static filed
//            return InnerStatic.this.name;
            return null;
        }
        public String superDesc(){
            return desc;
        }
    }

}
