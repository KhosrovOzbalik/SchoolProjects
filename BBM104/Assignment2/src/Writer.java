package assignment2;

public class Writer extends Artist {
    private String writingStyle;

    public Writer(String id, String name, String surname, String country, String writingStyle) {
        super(id, name, surname, country);
        this.writingStyle = writingStyle;
    }

    @Override
    public String returnType() {
        return "Writer";
    }

    public String getWritingStyle() {
        return writingStyle;
    }

    public void setWritingStyle(String writingStyle) {
        this.writingStyle = writingStyle;
    }
}
