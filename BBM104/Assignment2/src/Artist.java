package assignment2;

public class Artist extends Person {
    public Artist(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    @Override
    public String returnType() {
        return "Artist";
    }
}
