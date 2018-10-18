package ch13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuzhe
 * @since 8/28/18
 */
public class PatternTest {

    private static Pattern PATTERN = Pattern.compile("(abc)+");

    public static void main(String[] args) {
        String s1 = "abcabcabcabcfefghiabcabc";
        String s2 = "abcabcabcabcabcabcabcabc";
        String s3 = "defdefdefdef";
        Matcher matcher1 = PATTERN.matcher(s1);
        System.out.println(matcher1.matches());
        System.out.println(matcher1.lookingAt());
        System.out.println(matcher1.find());
        System.out.println("------");
        Matcher matcher2 = PATTERN.matcher(s2);
        System.out.println(matcher2.matches());
        System.out.println(matcher2.lookingAt());
        System.out.println(matcher2.find());
        System.out.println("------");
        Matcher matcher3 = PATTERN.matcher(s3);
        System.out.println(matcher3.matches());
        System.out.println(matcher3.lookingAt());
        System.out.println(matcher3.find());

    }

}
