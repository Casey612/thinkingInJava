package thinkingInJava.ch07;

/**
 * @author: yuki
 * @date: 2018/8/12
 */
public class Frog extends Amphibian{

    public static void main(String[] args) {
        Frog frog = new Frog();
        frog.sayI();
    }

    @Override
    public void sayI() {
        System.out.println("-----frog-----");
        super.sayI();
        System.out.println("-----frog-----");
    }
}
