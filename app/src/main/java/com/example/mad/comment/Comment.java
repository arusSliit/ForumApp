package com.example.mad.comment;

public class Comment {

    private String userID;//user ID
    private String questionID;// Question ID
    private String comment; //Comment
    private String commentID;//Comment ID

    public String getUserID() {
        return userID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }
}
