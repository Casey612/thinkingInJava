package ch21;

/**
 * @author: yuki
 * @date: 2018/12/2
 */
public class Fat {

    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat(){
        for(int i = 0; i < 10000; i++){
            d += (Math.PI + Math.E) / i;
        }
    }

    public void operation(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}
