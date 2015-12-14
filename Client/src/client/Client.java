package client;


import request.Book;
import request.BookText;
import request.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private ObjectInputStream in;
    private ObjectOutputStream out;


    public void connect() {
        int serverPort = 7777;
        String address = "127.0.0.1";

        try {
            Socket socket = new Socket(address, serverPort);
            System.out.println(socket);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException x) {
            System.err.println(x);
            System.out.println("Cant connect to server");
        }
    }


    public void textRequest(Book book) {
        try {
            out.flush();
            out.writeUTF("TEXT_REQUEST");
            out.flush();
            out.writeObject(book);
            out.flush();
        } catch (IOException x) {
            System.err.println("Error when write object");
        }
    }

    /* текст книги */
    public BookText receiveText() {
        try {
            return (BookText) in.readObject();
        } catch (IOException x) {
            System.err.println("Error when read object");

        } catch (ClassNotFoundException x) {
            System.err.println("Error when read object (No such class)");
        }
        return null;
    }

    /*  список найденых книг */
    public ArrayList<Book> readBookData() {

        try {
            return (ArrayList<Book>) in.readObject();
        } catch (IOException x) {
            System.err.println("Error when read object");

        } catch (ClassNotFoundException x) {
            System.err.println("Error when read object (No such class)");
        }
        return null;
    }

    public void writeString(String string) {


        try {
            out.flush();
            out.writeUTF(string);
            out.flush();
        } catch (IOException x) {
            System.err.println("Error when write object");
        }


    }

    public void writeUserData(User user) {

        try {
            out.flush();
            out.writeObject(user);
            out.flush();
        } catch (IOException x) {
            System.err.println("Error when write object");
        }

    }

    public User readUserData() {

        try {
            return (User) in.readObject();
        } catch (IOException x) {
            System.err.println("Error when read object");

        } catch (ClassNotFoundException x) {
            System.err.println("Error when read object (No such class)");
        }

        return null;
    }

    public void sendBook(Book book, BookText text) {
        String req2 = "SEND_REQUEST";
        try {
            out.flush();
            out.writeUTF(req2);
            out.flush();
            out.writeObject(book);
            out.flush();
            out.writeObject(text);
            out.flush();
        } catch (IOException ex) {
            System.out.println("error!" + ex);
        }
    }

    public void bookDelete(Book book) {
        String req1 = "DELETE_REQUEST";
        try {
            out.flush();
            out.writeUTF(req1);
            out.flush();
            out.writeObject(book);
            out.flush();

        } catch (IOException ex) {
            System.out.println("cannot delete this book");
        }

    }
}
