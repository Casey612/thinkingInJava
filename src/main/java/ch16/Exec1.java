package java.ch16;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 9/13/18
 */
public class Exec1 {


    public static void arrayToString(BerylliumSphere[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        arrayToString(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()});
        //普通聚集初始化error
//        arrayToString({new BerylliumSphere(), new BerylliumSphere()});
        BerylliumSphere[] array = {};
    }

}
