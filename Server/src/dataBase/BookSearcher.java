package dataBase;


import com.mysql.jdbc.Statement;
import request.Book;
import request.BookText;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Светлана
 */
public class BookSearcher {
    private final Connection connection;

    public BookSearcher() {
        connection = new DConnection().connect();
    }

    //TODO переделать.
    public synchronized List<Book> search(String searchdata) {

        List<Book> array = new ArrayList<>();
        String sq2 = "select Author, NameOfBook FROM library where"
                + " (Author LIKE '%" + searchdata + "%' OR NameOfBook LIKE '%" + searchdata + "%') ";
        try {
            PreparedStatement ps2 = connection.prepareStatement(sq2);

            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                array.add(new Book(rs.getString("Author"), rs.getString("NameOfBook")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return array;


    }

    public synchronized void deleteBook(Book book) {
        String req = "delete from library WHERE Author=? AND NameOfBook=?";
        try {
            PreparedStatement ps3 = connection.prepareStatement(req);
            ps3.setString(1, book.getAuthor());
            ps3.setString(2, book.getName());
            ps3.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public BookText foundText(Book book) {

        String sq1 = "select * from library where Author =? and NameOfBook =?";
        String filename;
        List<String> result = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sq1);

            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getName());
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                filename = rs.getString("BookText");
                //System.out.println(filename);
                try {
                    //return new String(Files.readAllBytes(Paths.get(filename)));
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(filename), "UTF-8"));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.add(line);
                    }
                    return new BookText(result);
                } catch (IOException ex) {
                    System.err.println("Cant read file " + ex);
                }
            } else {
                return new BookText(result);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new BookText(result);


    }

    public synchronized void bookAdded(Book book, BookText bookText) {
        String sq1 = "select * from library where Author =? and NameOfBook =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sq1);

            ps.setString(1, book.getAuthor());
            ps.setString(2, book.getName());
            ResultSet rs = ps.executeQuery();


            if (!rs.next()) {
                File newFile = new File("books/" + book.getName() + ".txt");
                if (newFile.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists");
                }
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(newFile)));

                List<String> text = bookText.textAsList();
                for (String line : text) {
                    writer.write(line + "\n");
                    writer.flush();

                }


                System.out.println(newFile.getPath());
                String sq = "INSERT INTO library VALUES ('" + book.getAuthor() + "', '"
                        + book.getName() + "', '" + "books/" + book.getName() + ".txt" + "' )";

                Statement ps1 = (Statement) connection.createStatement();

                ps1.executeUpdate(sq);

            }

        } catch (SQLException | IOException ex) {
            System.out.println(ex);
        }

    }
}
