package id.kunya.carijurusan.utils;

public class Question {
    private String id;
    private String question;
    private String weight_percent;
    private String answer;
    private String weight_answer;
    private String weight_criteria;

    public Question(){
        id="";
        question = "";
        weight_percent = "";
        answer = "";
        weight_answer = "";
        weight_criteria = "";
    }

    public Question(String question, String weight_percent, String answer, String weight_answer,String weight_criteria){
        this.question = question;
        this.weight_percent = weight_percent;
        this.answer = answer;
        this.weight_answer = weight_answer;
        this.weight_criteria = weight_criteria;
    }


    public String getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getWeight_answer() {
        return weight_answer;
    }

    public String getWeight_criteria() {
        return weight_criteria;
    }

    public String getWeight_percent() {
        return weight_percent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setWeight_answer(String weight_answer) {
        this.weight_answer = weight_answer;
    }

    public void setWeight_criteria(String weight_criteria) {
        this.weight_criteria = weight_criteria;
    }

    public void setWeight_percent(String weight_percent) {
        this.weight_percent = weight_percent;
    }

}
