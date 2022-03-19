package assignment2;

public class Performer extends Artist {
    public Performer(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    @Override
    public String returnType() {
        return "Performer";
    }
}
