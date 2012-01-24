package no.java.massmailer;

public class MailMsg {

    Person recipient;
    String message;

    public MailMsg(Person recipient, String message) {
        this.recipient = recipient;
        this.message = message;

    }

    public Person getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

}
