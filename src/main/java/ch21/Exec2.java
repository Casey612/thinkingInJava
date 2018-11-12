package ch21;

import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.Random;

/**
 * @author yuzhe
 * @since 11/12/18
 */
public class Exec2 implements Runnable {

    private static final int size = 25;
    public static final Integer[] array = new Integer[size];

    public int n;

    public Exec2(int n) {
        this.n = n;
    }

    public String getArrayStr() {
        for (int i = 0; i < n; i++) {
            if (Objects.isNull(array[i])) {
                array[i] = gen(i);
            }
        }
        return StringUtils.join(array, ",", 0, n);
    }

    private int gen(int i) {
        if (i == 0) {
            return 0;
        } else if (i == 1) {
            return 1;
        } else {
            return array[i - 1] + array[i - 2];
        }
    }

    @Override
    public void run() {
        System.out.println(getArrayStr());
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        for(int i = 0; i < 25; i++){
            new Thread(new Exec2(random.nextInt(size))).start();
        }
    }
}
