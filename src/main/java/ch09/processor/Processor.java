package java.ch09.processor;

/**
 * @author yuzhe
 * @since 8/14/18
 */
public abstract class Processor {

     public String name(){
         return getClass().getSimpleName();
     }

     Object process(Object input){
         return input;
     }
}
