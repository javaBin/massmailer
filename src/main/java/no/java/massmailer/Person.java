package no.java.massmailer;

/**
 * a representation of a speaker
 */
public class Person {
    String speakerName;
    String email;
    String URL;
    String presentationName;

    public Person(String speakerName, String email, String url, String presentationName) {
        this.speakerName = speakerName;
        this.email = email;
        this.URL = url;
        this.presentationName = presentationName;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public String getEmail() {
        return email;
    }

    public String getURL() {
        return URL;
    }

    public String getPresentationName() {
        return presentationName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "speakerName='" + speakerName + '\'' +
                ", email='" + email + '\'' +
                ", URL='" + URL + '\'' +
                ", presentationName='" + presentationName + '\'' +
                '}';
    }
}
