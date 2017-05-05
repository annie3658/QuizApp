package annie.com.quizapp.model;

/**
 * Created by Annie on 28/04/2017.
 */

public class User {

    String userId;
    String username;
    String language;
    String difficulty;
    String score;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public User()
    {

    }

    public User(String userId, String username, String language, String score, String difficulty) {
        this.userId = userId;
        this.username = username;
        this.language = language;
        this.score = score;
        this.difficulty=difficulty;
    }

    public String getUserId() {
        return userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
