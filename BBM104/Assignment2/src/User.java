package assignment2;

import java.util.ArrayList;

public class User extends Person {
    private ArrayList<String[]> rateList = new ArrayList<>();
    private ArrayList<String> idRateList = new ArrayList<>();

    public User(String id, String name, String surname, String country) {
        super(id, name, surname, country);
    }

    public void addRateList(String filmId, int rate, String filmName) {
        rateList.add(new String[] {filmId, String.valueOf(rate), filmName});
        idRateList.add(filmId);
    }

    public void delRateList(String filmId) {
        int index = 0;
        for (int i = 0; i < rateList.size(); i++) {
            if (rateList.get(i)[0].equals(filmId)) {
                index = i;
            }
        }
        rateList.remove(rateList.get(index));
        idRateList.remove(filmId);
    }

    @Override
    public String returnType() {
        return "User";
    }

    public ArrayList<String[]> getRateList() {
        return rateList;
    }

    public void setRateList(ArrayList<String[]> rateList) {
        this.rateList = rateList;
    }

    public ArrayList<String> getIdRateList() {
        return idRateList;
    }

    public void setIdRateList(ArrayList<String> idRateList) {
        this.idRateList = idRateList;
    }
}
