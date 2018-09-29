package thinkingInJava.ch10;

import java.util.List;
import java.util.ArrayList;

/**
 * @author yuzhe
 * @since 8/16/18
 */
public class Controller {

    private List<Event> eventList = new ArrayList<>();

    public void addEvent(Event e) {
        eventList.add(e);
    }

    public void run() {
        while (eventList.size() > 0){
            for(Event e : new ArrayList<Event>(eventList)){
                if(e.ready()){
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }

}
