package ch20.unitTest;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yuki
 * @date: 2018/11/2
 */
public class ClassNameFinder {

    public static String thisClass(byte[] classBytes) {

        Map<Integer, Integer> offsetTable = new HashMap<>();
        Map<Integer, String> classNameTable = new HashMap<>();
        try {
            //解析字节流
            DataInputStream data = new DataInputStream(
                    new ByteArrayInputStream(classBytes)
            );
            // 0xcafebabe
            int magic = data.readInt();
            int minorVersion = data.readShort();
            int majorVersion = data.readShort();

            int constant_pool_count = data.readShort();
            int[] constant_pool = new int[constant_pool_count];
            for (int i = 1; i < constant_pool_count; i++) {
                int tag = data.read();
                int tableSize;
                //JVM Page 169
                switch (tag) {
                    case 1:
                        //UTF 编码的字符串
                        int length = data.readShort();
                        char[] bytes = new char[length];
                        for (int k = 0; k < bytes.length; k++) {
                            bytes[k] = (char) data.read();
                        }
                        String className = new String(bytes);
                        classNameTable.put(i, className);
                        break;
                    case 5:
                        //long
                    case 6:
                        //double
                        data.readLong();
                        i++;
                        break;
                    case 7:
                        //class
                        int offset = data.readShort();
                        offsetTable.put(i, offset);
                        break;
                    case 8:
                        //String 字符串类型字面量
                        data.readShort();
                        break;
                    case 3:
                        //integer
                    case 4:
                        //float
                    case 9:
                        //field ref
                    case 10:
                        //method ref
                    case 11:
                        //interface method ref
                    case 12:
                        // name and type
                        data.readInt();
                        break;
                    default:
                        throw new RuntimeException("Bad tag " + tag);
                }
            }
            short access_flag = data.readShort();
            int this_class = data.readShort();
            int super_class = data.readShort();
            System.out.println(this_class);
            System.out.println(JSON.toJSON(offsetTable).toString());
            System.out.println(JSON.toJSON(classNameTable).toString());
            return classNameTable.get(offsetTable.get(this_class))
                    .replace('/', '.');

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String path ="target/classes/ch20/unitTest/Testable2.class";
        String result = thisClass(BinaryFile.read(path));
        System.out.println(result);
    }

}
