package java.ch17;

import java.util.BitSet;
import java.util.Random;

/**
 * @author: yuki
 * @date: 2018/10/7
 */
public class Bits {

    public static void printBitSet(BitSet bitSet) {
        System.out.println("bits: " + bitSet);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.size(); i++) {
            sb.append(bitSet.get(i) ? '1' : '0');
        }
        System.out.println("bit pattern: " + sb.toString());
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        byte bt = (byte) rand.nextInt();
        BitSet bb = new BitSet();
        for(int i = 7; i >= 0; i--){
            if(((1 << i) & bt) != 0){
                bb.set(i);
            }
            else {
                bb.clear(i);
            }
        }
        System.out.println("byte value:" + bt);
        printBitSet(bb);
    }

}
