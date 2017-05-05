package annie.com.quizapp.model;

/**
 * Created by Annie on 28/04/2017.
 */

public class Question {

    private int id;
    private String question;
    private String answer;
    private String optionA;
    private String optionB;
    private String optionC;
    private String difficulty;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Question() {

    }

    private String optionD;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public Question(int id, String question, String answer, String optionA, String optionB, String optionC, String optionD, String difficulty) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.difficulty=difficulty;
    }

}
