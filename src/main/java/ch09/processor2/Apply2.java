package ch09.processor2;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public class Apply2 {

    public static void process(Processor p, Object s){
        System.out.println("using processsor " + p.name());
        System.out.println(p.process(s));
    }
}
