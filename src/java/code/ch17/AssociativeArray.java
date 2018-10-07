package code.ch17;

/**
 * @author: yuki
 * @date: 2018/10/6
 */
public class AssociativeArray<K, V> {

    private Object[][] pairs;
    private int index;

    public AssociativeArray(int length) {
        pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        if (index >= pairs.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        pairs[index++] = new Object[]{key, value};
    }

    public V get(K key) {
        for (int i = 0; i < index; i++) {
            if (key.equals(pairs[i][0])) {
                return (V) pairs[i][1];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            sb.append(pairs[i][0].toString())
                    .append(':')
                    .append(pairs[i][1]);
            if(i < index - 1){
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AssociativeArray<String, String> map = new AssociativeArray<>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try{
            map.put("extra", "object");
        } catch (Exception e){
            System.out.println("too many objects");
        }
        System.out.println(map);
        System.out.println(map.get("ocean"));

    }

}
