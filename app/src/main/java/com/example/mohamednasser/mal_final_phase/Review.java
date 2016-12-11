package com.example.mohamednasser.mal_final_phase;

/**
 * Created by Mohamed Nasser on 12/10/2016.
 */

public class Review {
    String author;
    String review;

    // constructor;

    public Review(String author, String review) {
        this.author = author;
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}