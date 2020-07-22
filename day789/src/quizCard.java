public class quizCard{
    private String answer, question;

    public quizCard(String q, String a){
        question = q;
        answer = a;
    }
    String getAnswer(){
        return answer;
    }
    String getQuestion(){
        return question;
    }
}
