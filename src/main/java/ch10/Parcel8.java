package java.ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class Parcel8 {

    public Wrapping wrapping(int i){
        return new Wrapping(i){
            @Override
            public int getI(){
                return super.i * i;
            }
        };
    }

    private class Wrapping {
        private int i;
        public Wrapping(int i){
            this.i = i;
        }
        public int getI() {
            return i;
        }
    }

    public static void main(String[] args) {
        Parcel8 parcel8 = new Parcel8();
        Wrapping w1 = parcel8.new Wrapping(6);
        System.out.println(w1.getI());
        System.out.println(parcel8.wrapping(6).getI());
    }
}
