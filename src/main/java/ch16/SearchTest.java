package ch16;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/17/18
 */
public class SearchTest {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int result1 = Arrays.binarySearch(array, 3);
        int result2 = Arrays.binarySearch(array, 8);
        System.out.println(result1);
        System.out.println(result2);
    }

}
