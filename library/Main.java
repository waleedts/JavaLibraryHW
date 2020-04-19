package edu.najah.java.library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);//Scanner to get user input
        Library library=new Library();//A library object that contains the books
        boolean in=true;//Turns false to exit programme
        while (in) {
            String [] strArr = scanner.nextLine().split(" ", 4);//To split line to 4 parts
            String command=strArr[0].toLowerCase();//To make command case insensitive
            switch (command){
                case "add":library.add(strArr[1]);break;
                case "delete":library.delete(strArr[1]);break;
                case "set":library.set(strArr[1],strArr[2],strArr[3]);break;
                case "get":
                    System.out.println(library.get(strArr[1],strArr[2]));break;
                case "rent": library.rent(strArr[1]);break;
                case "return":library.returns(strArr[1]);break;
                case "search": library.search(strArr[1],strArr[2]+" "+strArr[3]);break;
                case "print":System.out.println(library);break;
                case "quit":in=false;break;//Exits the loop
                default:
                    System.err.println("There is no such command as "+command);//if the command is fals
            }
        }
    }
}
