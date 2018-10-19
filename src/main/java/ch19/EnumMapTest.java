package ch19;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public class EnumMapTest {

    interface Command {
        void action();
    }

    public static void main(String[] args) {
        EnumMap<AlarmPoint, Command> map = new EnumMap<AlarmPoint, Command>(AlarmPoint.class);
        map.put(AlarmPoint.LOBBY, () -> System.out.println("lobby fire"));
        for(Map.Entry<AlarmPoint, Command> e : map.entrySet()){
            System.out.print(e.getKey() + ":");
            e.getValue().action();
            System.out.println();
        }
        System.out.println(map.get(AlarmPoint.OFFICE1));
    }

}
