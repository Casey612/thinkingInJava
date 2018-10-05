package code.ch13;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author yuzhe
 * @since 8/29/18
 */
public class SplitDemo {

    public static void main(String[] args) {
        String input = "this!!unusual use!!of exclamation!!points";
        // pattern.split
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input, 3)));

        //String.split
        System.out.println(Arrays.toString(input.split("!!")));
        System.out.println(Arrays.toString(input.split("!!", 3)));


    }

}
