package ch16;

import ch15.Generator;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/17/18
 */
public class Exec12 {

    public static void main(String[] args) {
        Generator<Double> gen = new CountingGenerator.Double();
        double[] array = createDoubleArray(gen, 10);
        System.out.println(Arrays.toString(array));
    }

    public static double[] createDoubleArray(Generator<Double> gen, int length) {
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = gen.next();
        }
        return result;
    }

}
