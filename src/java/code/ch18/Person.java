package code.ch18;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
import org.apache.commons.compress.utils.CharsetNames;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuzhe
 * @since 10/17/18
 */
public class Person {
    private String first, last;

    public Person(String f, String l) {
        this.first = f;
        this.last = l;
    }

    public Element getXML() {
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }

    public Person(Element person) {
        first = person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
    }

    @Override
    public String toString() {
        return first + " " + last;
    }

    public static void format(OutputStream out, Document doc) throws Exception {
        Serializer serializer = new Serializer(out, CharsetNames.UTF_8);
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args) throws Exception {
        List<Person> people = Arrays.asList(
                new Person("Dr a", "b"),
                new Person("c", "d"),
                new Person("e", "f"),
                new Person("g", "h")
        );
        System.out.println(people);
        Element root = new Element("people");
        for (Person p : people) {
            root.appendChild(p.getXML());
        }
        Document doc = new Document(root);
        format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream("people.xml")), doc);
    }

}
