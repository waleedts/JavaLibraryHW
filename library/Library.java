package edu.najah.java.library;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

class Library {
    void delete(String isbn){
        boolean found=false;
        for(Book i:array){//finds and removes the book
            if(isbn.equals(i.getISBN())){
                array.remove(i);
                found=true;
            }
        }
        if(!found){//prints a message to indicate that the book is not in the library
            System.out.println("Sorry the book does not exist");
        }
    }
    void add(String isbn){
        boolean found=false;
        for(Book i:array){//searches for the book
            if (isbn.equals(i.getISBN())) {
                found = true;
                break;
            }
        }
        if(found){//if the book is in the library prints a message
            System.out.println("Book is already added");
        }else{//if the book is not in the library it adds it
            array.add(new Book(isbn));
            array.sort(Collections.reverseOrder());
        }
    }
    void set(String isbn,String attribute,String value){//sets the attribute of the book with the isbn to the value
        attribute =attribute.toLowerCase().trim();
        value=value.trim();
        boolean found=false;
        for (Book i: array) {//searches for the book in the library
            if(isbn.equals(i.getISBN())){
                switch (attribute) {//sets the value of the attribute
                    case "author":
                        i.setAuthor(value);
                        break;
                    case "title":
                        i.setTitle(value);
                        break;
                    case "edition":
                        i.setEdition(Integer.parseInt(value.trim()));
                        break;
                    case "published_year":
                        i.setPublishedYear(Integer.parseInt(value.trim()));
                        break;
                    default:
                        System.out.println("There is no such attribute as "+attribute);
                }
                found=true;
            }
        }
        if(!found){//prints a message to indicate that the book is not found
            System.out.println("There is no book with ISBN:"+isbn);
        }
    }
    String get(String isbn,String attribute){
        String s = null;
        for (Book i: array) {//searches for the book in the library
            if(isbn.equals(i.getISBN())){
                switch (attribute) {//sets the value of the attribute
                    case "author":
                        s = i.getAuthor();
                        break;
                    case "title":
                        s = i.getTitle();
                        break;
                    case "edition":
                        s = i.getEdition();
                        break;
                    case "available":
                        if (!i.isBorrowed()) {
                            s="Available";
                        } else {
                            s="Not Available";
                        }
                    case "published_year":
                        s = i.getPublishedYear();
                        break;
                    default:
                        s = "There is no such attribute as "+attribute;
                        break;
                }
            }
        }
        //return a a default string if string is null
        return Objects.requireNonNullElseGet(s, () -> "There is no book with ISBN:" + isbn);
    }
    void rent(String isbn){//change status to borrowed
        boolean found=false;
        for (Book i: array) {
            if(isbn.equals(i.getISBN())){
                found=true;
                if(!i.isBorrowed())
                    i.borrow();
                else{
                    System.out.println("Book is already borrowed");
                }
            }
        }
        if(!found){
            System.out.println("There is no such book");
        }
    }
    void returns(String isbn){//change status to not borrowed
        boolean found=false;
        for (Book i: array) {
            if(isbn.equals(i.getISBN())){
                found=true;
                if(i.isBorrowed())
                    i.returns();
                else{
                    System.out.println("Book is already returned");
                }
            }
        }
        if(!found){
            System.out.println("There is no such book");
        }
    }
    void search(String attribute,String keyword){//searches books by a attribute
        attribute=attribute.toLowerCase();
        String s;
        boolean found=false;
        for (Book i: array) {
            switch (attribute) {
                case "author":
                    s=i.getAuthor();
                    if (s.contains(keyword)) {
                        System.out.println(i);
                        found=true;
                    }
                    break;
                case "title":
                    s = i.getTitle();
                    if (s.contains(keyword)) {
                        System.out.println(i);
                        found=true;
                    }
                    break;
                case "edition":
                    s = i.getEdition();
                    if (s.contains(keyword)) {
                        System.out.println(i);
                        found=true;
                    }
                    break;
                case "isbn":
                    s = i.getISBN();
                    if (s!=null && s.contains(keyword)) {
                        System.out.println(i);
                        found=true;
                    }
                    break;
                case"published_year":
                    s = i.getPublishedYear();
                    if (s.contains(keyword)) {
                        System.out.println(i);
                        found=true;
                    }
                    break;
                default:
                    System.out.println("There is no such attribute as "+attribute);
            }
        }
        if(!found){
            System.out.println("There are no books with "+keyword+" in the "+attribute.substring(0,1).toUpperCase() + attribute.substring(1));
        }
    }
    @Override
    public String toString(){//return a s string of the whole library in a specific format
        StringBuilder builder=new StringBuilder(String.format("%-13s%-22s%-10s%-15s%-8s%-15s\n","ISBN","Title","Edition","Author","Year","Available"));
        for (Book i: array) {
            builder.append(i).append("\n");
        }
        return new String(builder);
    }
    ArrayList<Book> array = new ArrayList<>();
}
