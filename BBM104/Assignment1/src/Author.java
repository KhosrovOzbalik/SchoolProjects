package assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Author {
    private String id, name, university, department, email;
    private List<String> articleIds;

    public Author(String id, String name, String university, String department, String email, List<String> articleIds) {
         this.id = id;
         this.name = name;
         this.university = university;
         this.department = department;
         this.email = email;
         this.articleIds = articleIds;
    }

    public String getId() { return id;}
    public String getName() { return name;}
    public String getUniversity() { return university;}
    public String getDepartment() { return department;}
    public String getEmail() { return email;}
    public List<String> getArticleIds() { return articleIds;}

    public void addArticle(String articleId) {
        articleIds.add(articleId);
    }
    @Override
    public String toString() {
        return "Author:" + id + "\t" + name + "\t" + university + "\t" + department + "\t" + email;
    }
    public void sortArticle() {
        Collections.sort(articleIds);
    }
}
