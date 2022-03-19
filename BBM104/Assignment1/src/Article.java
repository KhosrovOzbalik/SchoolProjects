package assignment1;

public class Article {
    private String paperId, name, publisherName, publishYear;
    public Article() {}
    public Article(String paperId, String name, String publisherName, String publishYear) {
        this.paperId = paperId;
        this.name = name;
        this.publisherName = publisherName;
        this.publishYear = publishYear;
    }
    public String getPaperId() { return paperId;}
    public String getName() { return name;}
    public String getPublisherName() { return publisherName;}
    public String getPublishYear() { return publishYear;}
}
