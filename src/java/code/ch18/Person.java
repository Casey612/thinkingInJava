package code.ch18;

import nu.xom.Element;

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

    public Element getXML(){
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }
}
