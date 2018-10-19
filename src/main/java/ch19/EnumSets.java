package ch19;

import java.util.EnumSet;

/**
 * @author yuzhe
 * @since 10/19/18
 */

enum AlarmPoint {
    STAIR1, STAIR2, LOBBY, OFFICE1, OFFICE2;
}

public class EnumSets {

    public static void main(String[] args) {
        EnumSet<AlarmPoint> points = EnumSet.noneOf(AlarmPoint.class);
        points.add(AlarmPoint.STAIR1);
        System.out.println(points);

        points.addAll(EnumSet.of(AlarmPoint.STAIR1, AlarmPoint.STAIR2));
        System.out.println(points);

        points = EnumSet.allOf(AlarmPoint.class);
        System.out.println(points);
    }

}
