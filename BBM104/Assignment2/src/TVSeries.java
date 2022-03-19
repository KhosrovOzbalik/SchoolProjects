package assignment2;

import java.util.ArrayList;

public class TVSeries extends Film{
    private String startDate, endDate;
    private ArrayList<String> writers, genres;
    private int numberOfSeasons, numberOfEpisodes;

    public TVSeries(String id, String title, String language, ArrayList<String> directors, int runtime, String country, ArrayList<String> cast, ArrayList<String> genres, ArrayList<String> writers, String startDate, String endDate, int numberOfSeasons, int numberOfEpisodes) {
        super(id, title, language, runtime, country, directors, cast);
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
        this.genres = genres;
        this.writers = writers;
    }

    @Override
    public String returnType() {
        return "TVSeries";
    }

    @Override
    public String viewFilm() {
        String average = getRateAverage();
        String text = getTitle() + " (" + getStartDate().substring(6) + "-" + getEndDate().substring(6) + ")\n" +
                numberOfSeasons + " seasons, " + numberOfEpisodes + " episodes\n" +
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
        return "TVSeries{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", writers=" + writers +
                ", genres=" + genres +
                ", numberOfSeasons=" + numberOfSeasons +
                ", numberOfEpisodes=" + numberOfEpisodes +
                "} " + super.toString();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

}
