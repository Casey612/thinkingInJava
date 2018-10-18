package ch10;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class GreenHouseControls extends Controller {

    private boolean light = false;

    public class LinghtOn extends Event{

        protected LinghtOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = true;
        }

        @Override
        public String toString(){
            return "light is on";
        }
    }

    public class LinghtOff extends Event{

        protected LinghtOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = false;
        }

        @Override
        public String toString(){
            return "light is off";
        }
    }

    private boolean water = false;

    public class WaterOn extends Event{

        protected WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = true;
        }

        @Override
        public String toString(){
            return "water is on";
        }
    }

    public class WaterOff extends Event{

        protected WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }

        @Override
        public String toString(){
            return "water is off";
        }
    }

    private String thermostat = "Day";

    public class ThermostatDay extends Event{

        protected ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostat = "Day";
        }

        @Override
        public String toString(){
            return "thermostat on day setting";
        }
    }

    public class ThermostatNight extends Event{

        protected ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostat = "Night";
        }

        @Override
        public String toString(){
            return "thermostat on night setting";
        }
    }

    public class Bell extends Event{

        protected Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString(){
            return "Bing!";
        }
    }

    public class Restart extends Event{

        private Event[] eventList;

        protected Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for(Event e : eventList){
                addEvent(e);
            }
        }

        @Override
        public void action() {
            for(Event e : eventList){
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }

        @Override
        public String toString(){
            return "restarting system";
        }
    }

    public static class Terminate extends Event{

        protected Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.exit(0);
        }

        @Override
        public String toString(){
            return "Terminating";
        }
    }
}
