package code.ch13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yuzhe
 * @since 8/28/18
 */
public class Groups {

    public static final String POEM = "Twas brillig, and the slithy toves.\n"
            + "Did gyre and gimble in the wabe.\n";


    public static void main(String[] args) {
        Matcher matcher = Pattern.compile("(?m)(\\S+)\\s+(\\S+)\\s+(\\S+)$").matcher(POEM);
        while(matcher.find()){
            for(int j = 0; j <= matcher.groupCount(); j++){
                System.out.print("[" + matcher.group(j) + "]");
            }
            System.out.println();
        }
        System.out.println("-----");
        Matcher matcher1 = Pattern.compile("[A-Z]\\w+").matcher(POEM);
        while(matcher1.find()){
            for(int j = 0; j <= matcher1.groupCount(); j++){
                System.out.print("[" + matcher1.group(j) + "]");
            }
//            System.out.println(matcher1.group());
        }
    }

}
