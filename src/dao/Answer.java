package dao;

/**
 *
 * @author Paritosh
 */
public class Answer {
    int answerID;
    int qID;
    String answer;
    String employeeName;
    int upvotes;
    int downvotes;

    public Answer(int answerID, int qID, String answer, String employeeName, int upvotes, int downvotes) {
        this.answerID = answerID;
        this.qID = qID;
        this.answer = answer;
        this.employeeName = employeeName;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
    
    
    
    
}
