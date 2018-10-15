package code.ch18;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class AvailableChatSets {

    public static void main(String[] args) {
        SortedMap<String, Charset> charSets = Charset.availableCharsets();

        for (String cName : charSets.keySet()) {
            System.out.print(cName);
            Iterator<String> aliases = charSets.get(cName).aliases().iterator();
            if (aliases.hasNext()) {
                System.out.print(":");
            }
            while (aliases.hasNext()) {
                System.out.print(aliases.next());
                if (aliases.hasNext()) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }

    }

}
