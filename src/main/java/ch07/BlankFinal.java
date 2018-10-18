package ch07;

/**
 * @author: yuki
 * @date: 2018/8/12
 */
public class BlankFinal {

    private final int i = 0;
    private final int j;
    private final People people;

    public BlankFinal() {
        j = 1;
        people = new People(1);
    }

    public BlankFinal(int x){
        j = x;
        people = new People(x);
    }


    public static class People {
        private int i;

        public People(int ii) {
            this.i = ii;
        }

        @Override
        public String toString(){
            return "People.i=" + String.valueOf(i);
        }
    }

}
