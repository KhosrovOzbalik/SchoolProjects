package assignment2;

import java.util.ArrayList;

public class Documentary extends Film{
    private String releaseDate;

    public Documentary(String id, String title, String language, ArrayList<String> directors, int runtime, String country, ArrayList<String> cast, String releaseDate) {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
    }

    @Override
    public String returnType() {
        return "Documentary";
    }

    @Override
    public String viewFilm() {
        String average = getRateAverage();
        String text = getTitle() + " (" + getReleaseDate().substring(6) + ")\n" +
                "\nDirectors: " + String.join(", ", findNames(getDirectors())) +
                "\nStars: " + String.join(", ", findNames(getCast())) + "\n";
        if (getRatingScores().length == 0) {
            text += "Awaiting for votes\n";
        }
        else {
            text += rateString();
        }
        return text;
    }

    @Override
    public String toString() {
        return "Documentary{" +
                "releaseDate='" + releaseDate + '\'' +
                "} " + super.toString();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
