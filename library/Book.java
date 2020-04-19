package edu.najah.java.library;


import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Book implements Comparable <Book>{
    private String isbn;
    private String author;
    private String title;
    private int edition;
    private int publishedYear;
    private boolean borrowed;
    //constructor
    public Book(String isbn) {
        this.isbn=isbn;
        author= title=null;
        edition=publishedYear=0;
        borrowed=false;
    }
    //setters
    public void setAuthor(String author){
        this.author=author;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setEdition(int edition){
        this.edition=edition;
    }
    public void setPublishedYear(int publishedYear){
        this.publishedYear=publishedYear;
    }
    public void borrow(){
            borrowed=true;
    }
    public void returns(){
            borrowed=false;
    }
    //getters
    public String getAuthor(){
        return Objects.requireNonNullElse(author, "Author not specified");
    }
    public String getTitle(){
        return Objects.requireNonNullElse(title, "Title not specified");
    }

    public String getPublishedYear(){
        if(publishedYear!=0)
            return Integer.toString(publishedYear);
        else{
            return "Published Year not specified";
        }
    }
    public String getISBN(){
        return isbn;
    }
    public String getEdition(){
        if(edition!=0)
            return Integer.toString(edition);
        else{
            return "Edition Year not specified";
        }
    }
    public boolean isBorrowed(){return borrowed;}
    //overRidden Functions
    @Override
    public int compareTo(@NotNull Book o) {//compares books depending on the ISBN
        return this.getISBN().compareTo(o.getISBN());
    }
    @Override
    public String toString() {//return a s string form of the book in a specific format
        StringBuilder builder=new StringBuilder(String.format("%-13s%-22s%-10s%-15s%-8s",this.getISBN(),this.getTitle(),ordinal(this.getEdition()),this.getAuthor(),this.getPublishedYear()));
        if (!this.isBorrowed()) {
            builder.append("Available");
        } else {
            builder.append("Not Available");
        }
        return new String(builder);
    }
    private static @NotNull String ordinal(String s) {
        //prints the order of the number as in 1 becomes 1st
        if(s==null) {
            int i = Integer.parseInt(s);
            String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
            switch (i % 100) {
                case 11:
                case 12:
                case 13:
                    return i + "th";
                default:
                    return i + suffixes[i % 10];
            }
        }
        return "Edition is not specified";
    }
}
