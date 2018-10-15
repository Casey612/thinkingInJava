package code.ch18;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * @author yuzhe
 * @since 10/15/18
 */
public class Endians {

    public static void main(String[] args) {
        System.out.println("--------------------default endian--------------------");
        ByteBuffer bf = ByteBuffer.wrap(new byte[12]);
        bf.asCharBuffer().put("abcefg");
        System.out.println(Arrays.toString(bf.array()));

        System.out.println("--------------------big endian------------------------");

        bf.rewind();
        bf.order(ByteOrder.BIG_ENDIAN);
        bf.asCharBuffer().put("abcefg");
        System.out.println(Arrays.toString(bf.array()));

        System.out.println("--------------------little endian--------------------");

        bf.rewind();
        bf.order(ByteOrder.LITTLE_ENDIAN);
        bf.asCharBuffer().put("abcefg");
        System.out.println(Arrays.toString(bf.array()));

        System.out.println("------------------------------------------------------");
        bf = ByteBuffer.allocate(2);
        bf.order(ByteOrder.BIG_ENDIAN);
        bf.asShortBuffer().put((short) 97);
        System.out.println(bf.asShortBuffer().get());

        bf.clear();
        bf.rewind();
        bf.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println(bf.asShortBuffer().get());

    }

}
