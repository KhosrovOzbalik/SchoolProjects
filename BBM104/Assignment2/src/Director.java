package assignment2;

public class Director extends Artist {
    private String agent;

    public Director(String id, String name, String surname, String country, String agent) {
        super(id, name, surname, country);
        this.agent = agent;
    }

    @Override
    public String returnType() {
        return "Director";
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
