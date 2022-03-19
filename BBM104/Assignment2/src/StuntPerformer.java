package assignment2;

import java.util.ArrayList;

public class StuntPerformer extends Performer{
    private String height;
    private ArrayList<String> ActorsId;

    public StuntPerformer(String id, String name, String surname, String country, String height, ArrayList<String> ActorsId) {
        super(id, name, surname, country);
        this.height = height;
        this.ActorsId = ActorsId;
    }

    @Override
    public String returnType() {
        return "StuntPerformer";
    }

    public ArrayList<String> getActorsId() {
        return ActorsId;
    }

    public void setActorsId(ArrayList<String> actorsId) {
        ActorsId = actorsId;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
