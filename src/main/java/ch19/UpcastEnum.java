package ch19;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 10/19/18
 */
enum Search {
    HITHER,
    YON;
}


public class UpcastEnum {

    public static void main(String[] args) {
        Search[] vals = Search.values();
        System.out.println(Arrays.toString(vals));
        //up cast
        Enum e = Search.HITHER;
        for (Enum en : e.getClass().getEnumConstants()) {
            System.out.println(en);
        }
    }

}
