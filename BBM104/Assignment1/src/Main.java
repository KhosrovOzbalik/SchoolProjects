package assignment1;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Author> authors = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        // Reads author file
        try {
            File authorFile = new File(args[0]);
            Scanner scanner = new Scanner(authorFile);
            while (scanner.hasNextLine()) {
                scanner.next();
                String id, name, university, department, email;
                Author author;
                List<String> articleNames = new ArrayList<>();
                String[] nextLine = scanner.nextLine().trim().split(" ");
                if (nextLine.length != 1) {
                    id = nextLine[0];
                    name = nextLine[1];
                    university = nextLine[2];
                    department = nextLine[3];
                    email = nextLine[4];
                    try {
                        articleNames = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(nextLine,5,nextLine.length)));
                    }
                    catch(NoSuchElementException ignored) {}
                    catch (IllegalArgumentException e) {
                        articleNames = new ArrayList<>();
                    }
                    author = new Author(id, name, university, department, email, articleNames);
                }
                else {
                    author = new Author(nextLine[0],"","","","", new ArrayList<>());
                }

                authors.add(author);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(-1);
        }

        // Reads command file and Creates output file
        List<String> commands = new ArrayList<>();
        try {
            File commandFile = new File(args[1]);
            Scanner scanner = new Scanner(commandFile);
            while (scanner.hasNextLine()) {
                commands.add(scanner.nextLine());
            }

            FileWriter outputFile = new FileWriter("output.txt");
            outputFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Command File not found.");
            System.exit(-1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (String command : commands) {
            if (command.contains("read")) {
                // Articles
                try {
                    File articleFile = new File(command.substring(5));
                    Scanner scanner = new Scanner(articleFile);
                    while (scanner.hasNextLine()) {
                        scanner.next();
                        String paperId = scanner.next();
                        String name = scanner.next();
                        String publisherName = scanner.next();
                        String publishYear = scanner.next();
                        Article article = new Article(paperId,name,publisherName,publishYear);
                        articles.add(article);
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("Article File not found.");
                    System.exit(-1);
                }
            }
            else if (command.contains("list")) {
                try {
                    FileWriter writer = new FileWriter("output.txt",true);
                    writer.write("--------------------------List--------------------------------------\n");
                    for (Author author : authors) {
                        writer.write(author.toString() + "\n");
                        List<String> articleIds = author.getArticleIds();
                        for (String arId : articleIds) {
                            for (Article article : articles) {
                                if (arId.equals(article.getPaperId())) {
                                    writer.write("+" + article.getPaperId() +
                                            "\t" + article.getName() +
                                            "\t" + article.getPublisherName() +
                                            "\t" + article.getPublishYear() + "\n");
                                    break;
                                }
                            }
                        }
                        writer.write("\n");

                    }
                    writer.write("--------------------------End---------------------------------------\n");
                    writer.close();
                }
                catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            else if (command.contains("completeAll")) {
                try {
                    FileWriter writer = new FileWriter("output.txt", true);
                    for (Author author : authors) {
                        if (author.getArticleIds().size() < 5) {
                            for (Article article : articles) {
                                if ((!(author.getArticleIds().contains(article.getPaperId()))) &&
                                        (author.getId().equals(article.getPaperId().substring(0,3)))) {
                                    author.addArticle(article.getPaperId());
                                }
                            }
                        }
                    }
                    writer.write("**************************CompleteAll Successful*******************\n");
                    writer.close();
                }
                catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            }
            else if (command.contains("sortedAll")) {
                for (Author author: authors) {
                    author.sortArticle();
                }
                try {
                    FileWriter writer = new FileWriter("output.txt", true);
                    writer.write("*****************************SortedAll Successful*********************\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (command.contains("del")) {
                String authorId = command.substring(4);
                for (Author author: authors) {
                    if (author.getId().equals(authorId)) {
                        authors.remove(author);
                        break;
                    }
                }
                try {
                    FileWriter writer = new FileWriter("output.txt", true);
                    writer.write("*************************del Successful************************************\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
