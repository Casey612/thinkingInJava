package ch19;

import static ch19.Signal.*;

/**
 * @author yuzhe
 * @since 10/19/18
 */
public class TrafficLight {

    Signal color = GREEN;

    public void change() {
        switch (color) {
            case RED:
                this.color = GREEN;
                break;
            case GREEN:
                this.color = YELLOW;
                break;
            case YELLOW:
                this.color = RED;
                break;
        }
    }

    @Override
    public String toString() {
        return "this traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            System.out.println(light);
            light.change();
        }
    }
}
