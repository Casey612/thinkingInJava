package code.ch13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuzhe
 * @since 8/28/18
 */
public class Exec11 {

    private static Pattern PATTERN = Pattern.compile("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b");

    public static void main(String[] args) {
        String s = "Arline ate eight apples and one orange while Anita hadn't any";
        Matcher matcher = PATTERN.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
