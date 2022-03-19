package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Film {
    private String id, title, language, country;
    private int runtime;
    private int[] ratingScores = new int[0];
    private ArrayList<String> directors, cast;

    public Film(String id, String title, String language, int runtime, String country, ArrayList<String> directors, ArrayList<String> cast) {

        this.id = id;
        this.title = title;
        this.language = language;
        this.runtime = runtime;
        this.country = country;
        this.directors = directors;
        this.cast = cast;
    }

    public String returnType() { return "Film";}

    public String viewFilm() {
        return "Film";
    }

    // To find names of needed people
    public List<String> findNames(List<String> list) {
        List<String> names = new ArrayList<>();
        for (String id : list) {
            for (Person p : Main.people) {
                if (p.getId().equals(id)) {
                    names.add(p.getName() + " " + p.getSurname());
                    break;
                }
            }
        }
        return names;
    }

    public void addRating(int rate) {
        int[] ratingScore2 = new int[ratingScores.length + 1];
        for (int i = 0; i < ratingScores.length; i++) {
            ratingScore2[i] = ratingScores[i];
        }
        ratingScore2[ratingScore2.length - 1] = rate;
        ratingScores = ratingScore2;
    }

    public void delRating(int rate) {
        int[] ratingScore2 = new int[ratingScores.length - 1];
        int flag = 0;
        for (int i = 0; i < ratingScores.length; i++) {
            if (flag == 0 && (ratingScores[i] == rate)) {
                flag = -1;
                continue;
            }
            ratingScore2[i + flag] = ratingScores[i];
        }
        ratingScores = ratingScore2;
    }

    public String getRateAverage() {
        double ratingScore = 0;
        for (int rate :  getRatingScores()) {
            ratingScore += rate;
        }
        ratingScore = Math.round((ratingScore / (ratingScores.length)) * 10.0) / 10.0;
        if (ratingScore % 1 == 0) {
            return String.valueOf((int) ratingScore);
        }
        else {
            return String.valueOf(ratingScore).replace(".",",");
        }
    }

    // returns: "Ratings: xx/10 from x users"
    public String rateString() {
        return "Ratings: " + getRateAverage() + "/10 from " + getRatingScores().length + " users\n";
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", runtime=" + runtime +
                ", ratingScores=" + Arrays.toString(ratingScores) +
                ", directors=" + directors +
                ", cast=" + cast +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public int[] getRatingScores() {
        return ratingScores;
    }

    public void setRatingScores(int[] ratingScores) {
        this.ratingScores = ratingScores;
    }
}
