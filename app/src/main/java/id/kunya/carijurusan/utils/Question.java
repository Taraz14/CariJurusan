package id.kunya.carijurusan.utils;

public class Question {
    private String id;
    private String question;
    private String weight_percent;
    private String answer;
    private String weight_answer;
    private String description;
    private String weight_criteria;
    private String weight_alternative;
    private String prev_questions;
    private String next_questions;

    public Question(){
        id="";
        question = "";
        weight_percent = "";
        answer = "";
        weight_answer = "";
        description = "";
        weight_criteria = "";
        weight_alternative = "";
        prev_questions = "";
        next_questions = "";
    }

    public Question(String question, String weight_percent, String answer, String weight_answer,String description,String weight_criteria,String weight_alternative, String prev_questions, String next_questions){
        this.question = question;
        this.weight_percent = weight_percent;
        this.answer = answer;
        this.weight_answer = weight_answer;
        this.description = description;
        this.weight_criteria = weight_criteria;
        this.weight_alternative = weight_alternative;
        this.prev_questions = prev_questions;
        this.next_questions = next_questions;
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

    public String getDescription() {
        return description;
    }

    public String getWeight_alternative() {
        return weight_alternative;
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

    public String getPrev_questions() {
        return prev_questions;
    }

    public String getNext_questions() {
        return next_questions;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight_alternative(String weight_alternative) {
        this.weight_alternative = weight_alternative;
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

    public void setPrev_questions(String prev_questions) {
        this.prev_questions = prev_questions;
    }

    public void setNext_questions(String next_questions) {
        this.next_questions = next_questions;
    }
}
