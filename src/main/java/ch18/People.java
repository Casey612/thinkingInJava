package ch18;

import nu.xom.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author yuzhe
 * @since 10/18/18
 */
public class People extends ArrayList<Person> {

    public People(String fileName) throws ParsingException, IOException {
        Document doc = new Builder().build(fileName);
        Elements elements = doc.getRootElement().getChildElements();
        for (int i = 0; i < elements.size(); i++) {
            add(new Person(elements.get(i)));
        }
    }

    public static void main(String[] args) throws ParsingException, IOException {
        People p = new People("people.xml");
        System.out.println(p);
    }

}
