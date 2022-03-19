package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static List<Person> people = new ArrayList<>();
    static List<Film> films = new ArrayList<>();
    static List<String> filmIds = new ArrayList<>();
    public static void main(String[] args) {
        //args = people.txt films.txt commands.txt output.txt


        // People
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            String id, name, surname, country, height, agent, writerType, age;
            Person person = null;
            while (scanner.hasNextLine()) {
                String personType = scanner.next().replaceAll(":$", "");
                id = scanner.next();
                name = scanner.next();
                surname = scanner.next();
                country = scanner.next();
                if (personType.equals("Director")) {
                    agent = scanner.next();
                    person = new Director(id, name, surname, country, agent);
                } else if (personType.equals("Writer")) {
                    writerType = scanner.next();
                    person = new Writer(id, name, surname, country, writerType);
                } else if (personType.equals("Actor")) {
                    height = scanner.next();
                    person = new Actor(id, name, surname, country, height);
                } else if (personType.equals("ChildActor")) {
                    age = scanner.next();
                    person = new ChildActor(id, name, surname, country, age);
                } else if (personType.equals("StuntPerformer")) {
                    height = scanner.next();
                    ArrayList<String> actorsId = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));
                    person = new StuntPerformer(id, name, surname, country, height, actorsId);
                } else if (personType.equals("User")) {
                    person = new User(id, name, surname, country);
                }
                people.add(person);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("People.txt error");
            e.printStackTrace();
        }


        // Films
        try {
            File file = new File(args[1]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String filmType = scanner.next().replaceAll(":$", "");
                addFilm(films, filmIds, scanner, filmType);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Films.txt error");
            e.printStackTrace();
        }


        // Resetting output file
        try {
            FileWriter outputFile = new FileWriter(args[3]);
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Commands
        try {
            File file = new File(args[2]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String text = "";
                String command = scanner.next();
                if (command.equals("RATE")) {
                    String userId = scanner.next();
                    String filmId = scanner.next();
                    int ratingPoint = scanner.nextInt();
                    text += "RATE" + "\t" + userId + "\t" + filmId + "\t" + ratingPoint + "\n\n";
                    for (Person p : people) {
                        if (p.getId().equals(userId) && p instanceof User) {
                            if (!((User) p).getIdRateList().contains(filmId)) {
                                for (Film film: films) {
                                    if (film.getId().equals(filmId)) {
                                        ((User) p).addRateList(filmId, ratingPoint, film.getTitle());
                                        film.addRating(ratingPoint);
                                        text += "Film rated successfully\n" +
                                                "Film type: " + film.returnType() + "\n" +
                                                "Film title: " + film.getTitle() + "\n\n";
                                        break;
                                    }
                                }
                            }
                            else {
                                text += "This film was earlier rated\n\n";
                            }
                            break;
                        }
                    }
                    if (text.equals("RATE" + "\t" + userId + "\t" + filmId + "\t" + ratingPoint + "\n\n")) {
                        text += "Command Failed\n" +
                                "User ID: " + userId + "\n" +
                                "Film ID: " + filmId + "\n\n";
                    }
                }
                else if (command.equals("ADD")) {
                    String filmType = scanner.next();
                    String line = scanner.nextLine();
                    String[] elements = line.split("\t");
                    text += command + "\t" + filmType + line + "\n\n";
                    boolean doesFilmExist = films.stream().map(Film::getId).collect(Collectors.toList()).contains(elements[1]);

                    List<String> directorIds = people.stream().filter(person -> person instanceof Director).map(Person::getId).collect(Collectors.toList());
                    boolean doesDirectorsExist = Arrays.stream(elements[4].split(",")).allMatch(directorIds::contains);

                    List<String> performerIds = people.stream().filter(person -> person instanceof Performer).map(Person::getId).collect(Collectors.toList());
                    boolean doesPerformerExist = Arrays.stream(elements[7].split(",")).allMatch(performerIds::contains);

                    List<String> writerIds = people.stream().filter(person -> person instanceof Writer).map(Person::getId).collect(Collectors.toList());
                    boolean doesWritersExist = Arrays.stream(elements[10].split(",")).allMatch(writerIds::contains);

                    if (!(doesFilmExist) && doesDirectorsExist && doesPerformerExist && doesWritersExist) {
                        text += "FeatureFilm added successfully\n" +
                                "Film ID: " + elements[1] + "\n" +
                                "Film title: " + elements[2] + "\n\n";
                        Scanner slyScanner = new Scanner(line);
                        addFilm(films, filmIds, slyScanner, filmType);
                    }
                    else {
                        text += "Command Failed\n" +
                                "Film ID: " + elements[1] + "\n" +
                                "Film title: " + elements[2] + "\n\n";
                    }
                }
                else if (command.equals("VIEWFILM")) {
                    String filmId = scanner.next();
                    text += command + "\t" + filmId + "\n\n";
                    for (Film film : films) {
                        if (film.getId().equals(filmId)) {
                            text += film.viewFilm() + "\n";
                            break;
                        }
                    }
                    if (text.equals(command + "\t" + filmId + "\n\n")) {
                        text += "Command Failed\n" +
                                "Film ID: " + filmId + "\n\n";
                    }
                }
                else if (command.equals("LIST")) {
                    String command2 = scanner.next();
                    if (command2.equals("USER")) {
                        String userId = scanner.next();
                        String command3 = scanner.next();
                        text += command + "\t" +command2 + "\t" + userId + "\t" + command3 + "\n\n";
                        for (Person p : people) {
                            if (userId.equals(p.getId()) && p instanceof User) {
                                for (String[] FilmRate : ((User) p).getRateList()) {
                                    text += FilmRate[2] + ": " + FilmRate[1] + "\n";
                                }
                                text += "\n";
                                break;
                            }
                        }
                        if (text.equals(command + "\t" +command2 + "\t" + userId + "\t" + command3 + "\n\n")) {
                            text += "Command Failed\n" +
                                    "User ID: " + userId + "\n\n";
                        }
                    }
                    else if (command2.equals("FILM")) {
                        String type = scanner.next();
                        text += command + "\t" + command2 + "\t" + type + "\n\n";
                        for (Film film : films) {
                            if (film.returnType().equals("TVSeries")) {
                                text += film.getTitle() + " (" + ((TVSeries) film).getStartDate().substring(6) + "-" + ((TVSeries) film).getEndDate().substring(6) + ")\n" + ((TVSeries) film).getNumberOfSeasons() + " seasons and " + ((TVSeries) film).getNumberOfEpisodes() + " episodes\n\n";
                            }
                        }
                    }
                    else if (command2.equals("FILMS")) {
                        scanner.next();
                        String command3 = scanner.next();
                        String command4 = scanner.next();
                        text += command + "\tFILMS\tBY\t" + command3 + "\t" + command4 + "\n\n";
                        if (command3.equals("COUNTRY")) {
                            for (Film film : films) {
                                if (film.getCountry().equals(command4)) {
                                    text += "Film title: " + film.getTitle() + "\n" +
                                            film.getRuntime() + " min\n" +
                                            "Language: " + film.getLanguage() + "\n\n";
                                }
                            }
                        }
                        else if (command3.equals("RATE")) {
                            Comparator<Film> rateComparator = Comparator.comparing(Film::getRateAverage);

                            List<Film> featureFilms = films.stream().filter(film -> film instanceof FeatureFilm).collect(Collectors.toList());
                            Collections.sort(featureFilms, rateComparator.reversed());
                            text += "FeatureFilm:\n";
                            for (Film film : featureFilms) {
                                text += film.getTitle() + " (" + ((FeatureFilm) film).getReleaseDate().substring(6) + ") " + film.rateString();
                            }

                            List<Film> shortFilms = films.stream().filter(film -> film instanceof ShortFilm).collect(Collectors.toList());
                            Collections.sort(shortFilms, rateComparator.reversed());
                            text += "\nShortFilm:\n";
                            for (Film film : shortFilms) {
                                text += film.getTitle() + " (" + ((ShortFilm) film).getReleaseDate().substring(6) + ") " + film.rateString();
                            }

                            List<Film> documentaries = films.stream().filter(film -> film instanceof Documentary).collect(Collectors.toList());
                            Collections.sort(documentaries, rateComparator.reversed());
                            text += "\nDocumentary:\n";
                            for (Film film : documentaries) {
                                text += film.getTitle() + " (" + ((Documentary) film).getReleaseDate().substring(6) + ") " + film.rateString();
                            }

                            List<Film> TVSeries = films.stream().filter(film -> film instanceof TVSeries).collect(Collectors.toList());
                            Collections.sort(TVSeries, rateComparator.reversed());
                            text += "\nTVSeries:\n";
                            for (Film film : TVSeries) {
                                text += film.getTitle() + " (" + ((TVSeries) film).getStartDate().substring(6) + "-" + ((TVSeries) film).getEndDate().substring(6) + ") " + film.rateString();
                            }
                            text += "\n";
                        }
                        if (text.equals(command + "\tFILMS\tBY\t" + command3 + "\t" + command4 + "\n\n")) {
                            text += "No result\n\n";
                        }

                    }
                    else if (command2.equals("FEATUREFILMS")) {
                        String command3 = scanner.next();
                        int year = scanner.nextInt();
                        text += command + "\t" + command2 + "\t" + command3 + "\t"+ year + "\n\n";
                        for (Film film: films) {
                            if (film.returnType().equals("FeatureFilm")) {
                                if (Integer.parseInt(((FeatureFilm) film).getReleaseDate().substring(6)) > year && command3.equals("AFTER")) {
                                    text += "Film title: " + film.getTitle() + " (" + ((FeatureFilm) film).getReleaseDate().substring(6) + ")\n" + film.getRuntime() + " min\nLanguage: " + film.getLanguage() + "\n\n";
                                }
                                else if (Integer.parseInt(((FeatureFilm) film).getReleaseDate().substring(6)) < year && command3.equals("BEFORE")) {
                                    text += "Film title: " + film.getTitle() + " (" + ((FeatureFilm) film).getReleaseDate().substring(6) + ")\n" + film.getRuntime() + " min\nLanguage: " + film.getLanguage() + "\n\n";
                                }
                            }
                        }
                        if (text.equals(command + "\t" + command2 + "\t" + command3 + "\t"+ year + "\n\n")) {
                            text += "No result\n\n";
                        }
                    }
                    else if (command2.equals("ARTISTS")) {
                        scanner.next();
                        String country = scanner.next();
                        text += command + "\t" + command2 + "\tFROM\t" + country + "\n\n";
                        ArrayList<String> directors = new ArrayList<>();
                        ArrayList<String> writers = new ArrayList<>();
                        ArrayList<String> actors = new ArrayList<>();
                        ArrayList<String> childActors = new ArrayList<>();
                        ArrayList<String> stuntPerformers = new ArrayList<>();
                        for (Person p: people) {
                            if (p.getCountry().equals(country)) {
                                if (p.returnType().equals("Director")) {
                                    directors.add(p.getName() + " " + p.getSurname() + " " + ((Director) p).getAgent());
                                } else if (p.returnType().equals("Writer")) {
                                    writers.add(p.getName() + " " + p.getSurname() + " " + ((Writer) p).getWritingStyle());
                                } else if (p.returnType().equals("Actor")) {
                                    actors.add(p.getName() + " " + p.getSurname() + " " + ((Actor) p).getHeight() + " cm");
                                } else if (p.returnType().equals("ChildActor")) {
                                    childActors.add(p.getName() + " " + p.getSurname() + " " + ((ChildActor) p).getAge());
                                } else if (p.returnType().equals("StuntPerformer")) {
                                    stuntPerformers.add(p.getName() + " " + p.getSurname() + " " + ((StuntPerformer) p).getHeight() + " cm");
                                }
                            }
                        }
                        text += "Directors:\n";
                        text += (directors.isEmpty()) ? "No result\n\n" :  String.join("\n", directors) + "\n\n";

                        text += "Writers:\n";
                        text += (writers.isEmpty()) ? "No result\n\n" :  String.join("\n", writers) + "\n\n";

                        text += "Actors:\n";
                        text += (actors.isEmpty()) ? "No result\n\n" :  String.join("\n", actors) + "\n\n";

                        text += "ChildActors:\n";
                        text += (childActors.isEmpty()) ? "No result\n\n" :  String.join("\n", childActors) + "\n\n";

                        text += "StuntPerformers:\n";
                        text += (stuntPerformers.isEmpty()) ? "No result\n\n" :  String.join("\n", stuntPerformers) + "\n\n";
                    }
                }
                else if (command.equals("EDIT")) {
                    scanner.next();
                    String userId = scanner.next();
                    String filmId = scanner.next();
                    int rate = scanner.nextInt();
                    String filmTitle = "";
                    text += command + "\t" + "RATE" + "\t" + userId + "\t" + filmId + "\t" + rate + "\n\n";
                    for (Person p: people) {
                        if (p.getId().equals(userId) && (p.returnType().equals("User"))) {
                            for (Film film: films) {
                                if (film.getId().equals(filmId) && ((User) p).getIdRateList().contains(film.getId())) {
                                    for (String[] s : ((User) p).getRateList()) {
                                        if (s[0].equals(filmId)) {
                                            film.delRating(Integer.parseInt(s[1]));
                                            s[1] = String.valueOf(rate);
                                            film.addRating(rate);
                                            filmTitle = film.getTitle();
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if ((filmTitle.equals(""))) {
                        text += "Command Failed\n" +
                                "User ID: " + userId + "\n" +
                                "Film ID: " + filmId + "\n\n";
                    }
                    else {
                        text += "New ratings done successfully\n" +
                                "Film title: " + filmTitle + "\n" +
                                "Your rating: " + rate + "\n\n";
                    }
                }
                else if (command.equals("REMOVE")) {
                    scanner.next();
                    String userId = scanner.next();
                    String filmId = scanner.next();
                    text += command + "\t" + "RATE" + "\t" + userId + "\t" + filmId + "\n\n";
                    for (Person p : people) {
                        if (p.getId().equals(userId) && (p.returnType().equals("User"))) {
                            if (((User) p).getIdRateList().contains(filmId)) {
                                for (String[] s : ((User) p).getRateList()) {
                                    if (s[0].equals(filmId)) {
                                        for (Film film: films) {
                                            if (film.getId().equals(filmId)) {
                                                film.delRating(Integer.parseInt(s[1]));
                                                text += "Your film rating was removed successfully\n" +
                                                        "Film title: " + film.getTitle() + "\n\n";
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                ((User) p).delRateList(filmId);
                            }
                            break;
                        }
                    }
                    if (text.equals(command + "\t" + "RATE" + "\t" + userId + "\t" + filmId + "\n\n")) {
                        text += "Command Failed\n" +
                                "User ID: " + userId + "\n" +
                                "Film ID: " + filmId + "\n\n";
                    }
                }
                writeOutput(text,args[3]);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Commands.txt error");
            e.printStackTrace();
        }
    }

    public static void addFilm(List<Film> films, List<String> filmIds, Scanner scanner, String filmType) {
        String id = scanner.next();
        String title = scanner.next();
        String language = scanner.next();
        ArrayList<String> directors = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
        int length = scanner.nextInt();
        String country = scanner.next();
        ArrayList<String> performers = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
        ArrayList<String> genres;
        String releaseDate;
        ArrayList<String> writers;
        filmIds.add(id);
        if (filmType.toUpperCase(Locale.ROOT).equals("FEATUREFILM")) {
            genres = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
            releaseDate = scanner.next();
            writers = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
            String budget = scanner.next();
            Film featureFilm = new FeatureFilm(id, title, language, directors, length, country, performers, genres, releaseDate, writers, budget);
            films.add(featureFilm);
        } else if (filmType.toUpperCase(Locale.ROOT).equals("SHORTFILM")) {
            genres = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
            releaseDate = scanner.next();
            writers = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
            Film shortFilm = new ShortFilm(id, title, language, directors, length, country, performers, genres, releaseDate, writers);
            films.add(shortFilm);
        } else if (filmType.toUpperCase(Locale.ROOT).equals("DOCUMENTARY")) {
            releaseDate = scanner.next();
            Film documentary = new Documentary(id, title, language, directors, length, country, performers, releaseDate);
            films.add(documentary);
        } else if (filmType.toUpperCase(Locale.ROOT).equals("TVSERIES")) {
            genres = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
            writers = new ArrayList<>(Arrays.asList(scanner.next().split(",")));
            String startDate = scanner.next();
            String endDate = scanner.next();
            int seasons = scanner.nextInt();
            int episodes = scanner.nextInt();
            Film tvSeries = new TVSeries(id, title, language, directors, length, country, performers, genres, writers, startDate, endDate, seasons, episodes);
            films.add(tvSeries);
        }
    }
    public static void writeOutput(String text, String fileName) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            writer.write(text);
            writer.write("-----------------------------------------------------------------------------------------------------\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
