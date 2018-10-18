package ch13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuzhe
 * @since 8/29/18
 */
public class ReFlags {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("java\\w*+", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher("java language\n" +
                "JAVA coffee\n" + "JavaScript is an other language\n" + "xxx java\n");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
