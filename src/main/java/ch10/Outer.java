package ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class Outer {

    private String s;
    public Outer(String s){
        this.s = s;
    }
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Inner getInner(String name){
        return new Inner(name);
    }

    @Override
    public String toString(){
        return this.s;
    }

    class Inner{

        private String s;

        public Inner(String s){
            this.s = s;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String outerToString(){
            return Outer.this.toString();
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer("outer");
        Outer.Inner inner = outer.getInner("inner");
        System.out.println(outer.toString());
        System.out.println(inner.outerToString());
    }
}
