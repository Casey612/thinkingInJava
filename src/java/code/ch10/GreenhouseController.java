package code.ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class GreenhouseController {

    public static void main(String[] args) {
        GreenHouseControls gc = new GreenHouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] events = {
                gc.new ThermostatDay(0),
                gc.new LinghtOn(200),
                gc.new LinghtOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatNight(1400)
        };
        gc.addEvent(gc.new Restart(2000, events));
        gc.addEvent(new GreenHouseControls.Terminate(3000));
        gc.run();
    }

}
