package ch18;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @author yuzhe
 * @since 10/18/18
 */
public class PreferencesDemo {

    public static void main(String[] args) throws BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("location", "oz");
        prefs.put("footwear", "slippers");
//        prefs.putInt("companions", 4);
        prefs.putBoolean("are there witches", false);

        int usageCount = prefs.getInt("usage count", 0);
        prefs.putInt("usage count", usageCount);
        for (String s : prefs.keys()) {
            System.out.println(s + ":" + prefs.get(s, null));
        }
        System.out.println("how many companions does dorothy have? " + prefs.getInt("companions", 0));
    }

}
