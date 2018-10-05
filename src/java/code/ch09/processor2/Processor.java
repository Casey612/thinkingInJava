package code.ch09.processor2;

public interface Processor {

     public default String name(){
         return getClass().getSimpleName();
     }

     default Object process(Object input){
         return input;
     }

}