package assignment2;

import java.util.ArrayList;

public class FeatureFilm extends Film{
    private String releaseDate, budget;
    private ArrayList<String> writers, genres;

    public FeatureFilm(String id, String title, String language, ArrayList<String> directors,  int runtime, String country, ArrayList<String> cast, ArrayList<String> genres, String releaseDate, ArrayList<String> writers, String budget) {
        super(id, title, language, runtime, country, directors, cast);
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.writers = writers;
        this.genres = genres;
    }

    @Override
    public String returnType() {
        return "FeatureFilm";
    }

    @Override
    public String viewFilm() {
        String average = getRateAverage();
        String text = getTitle() + " (" + getReleaseDate().substring(6) + ")\n" +
                String.join(", ", genres) +
                "\nWriters: " + String.join(", ", findNames(writers)) +
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
        return "FeatureFilm{" +
                "releaseDate='" + releaseDate + '\'' +
                ", budget='" + budget + '\'' +
                ", writers=" + writers +
                ", genres=" + genres +
                "} " + super.toString();
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public void setWriters(ArrayList<String> writers) {
        this.writers = writers;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }
}
