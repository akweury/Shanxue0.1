package testlearn.shanxue.edu.shanxue01.models;

import java.io.Serializable;

public class BookPlusModel implements Serializable{
    private String book_ID;
    private String book_name;
    private String book_creator;
    private String book_type;
    private String book_creatDate;
    private  int  book_status;
    private String book_cover;
    private String book_admin;
    private int book_difficulty;
    private String book_intro;
    public String getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_creator() {
        return book_creator;
    }

    public void setBook_creator(String book_creator) {
        this.book_creator = book_creator;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getBook_creatDate() {
        return book_creatDate;
    }

    public void setBook_creatDate(String book_creatDate) {
        this.book_creatDate = book_creatDate;
    }

    public int getBook_status() {
        return book_status;
    }

    public void setBook_status(int book_status) {
        this.book_status = book_status;
    }

    public String getBook_cover() {
        return book_cover;
    }

    public void setBook_cover(String book_cover) {
        this.book_cover = book_cover;
    }

    public String getBook_admin() {
        return book_admin;
    }

    public void setBook_admin(String book_admin) {
        this.book_admin = book_admin;
    }

    public int getBook_difficulty() {
        return book_difficulty;
    }

    public void setBook_difficulty(int book_difficulty) {
        this.book_difficulty = book_difficulty;
    }

    public String getBook_intro() {
        return book_intro;
    }

    public void setBook_intro(String book_intro) {
        this.book_intro = book_intro;
    }
}
