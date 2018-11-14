package ch21;

/**
 * @author yuzhe
 * @since 11/14/18
 */
public class SerialNumberGenerator {

    private static volatile int serialNumber = 0;

    public static synchronized int nextSerialNumber() {
        return serialNumber++;
    }

}
