package no.java.massmailer;

import java.util.Map;

/**
 * Result of an evaluation.
 * @author Tobias K Torrissen
 */
public class PersonalizedInfo {

   Person person;
   Map<Integer, String> data;


    public PersonalizedInfo(Person person, Map<Integer, String> data) {
       this.person = person;
        this.data = data;
    }


    public Person getPerson() {
        return person;
    }

    public Map<Integer, String> getData() {
        return data;
    }
}
