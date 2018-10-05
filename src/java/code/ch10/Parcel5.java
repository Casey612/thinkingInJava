package code.ch10;

import code.ch09.processor2.Processor;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class Parcel5 {

    public Processor function(){
        class Inner implements Processor {
            private String s;
            private Inner(String s){
                this.s = s;
            }
            @Override
            public String name(){
                return this.s;
            }
        }
        return new Inner("inner1");
    }

    public Processor getProcessor(){
        class Inner implements Processor{
            @Override
            public String name(){
                return "inner2";
            }
        }
        return new Inner();
    }

    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        System.out.println(parcel5.function().name());
        System.out.println(parcel5.getProcessor().name());

    }

}
