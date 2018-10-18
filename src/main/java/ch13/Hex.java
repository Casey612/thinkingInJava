package java.ch13;

/**
 * @author yuzhe
 * @since 8/28/18
 */
public class Hex {

    public static String format(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (byte b : bytes) {
            if (n % 16 == 0) {
                sb.append(String.format("%05X: ", n));
            }
            sb.append(String.format("%2X", b));
            n++;
            if (n % 16 == 0) {
                sb.append("\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
       byte[] bytes = new byte[]{ 0xc, 0xa, 0xf, 0xe};
        System.out.println(format(bytes));
    }


}
