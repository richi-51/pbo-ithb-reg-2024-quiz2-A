package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Books;
import Model.DatabaseManager;

public class GetBookList {
    public ArrayList<Books> getDataBukuFromDB() {
        ArrayList<Books> books = new ArrayList<Books>();

        Connection conn = DatabaseManager.connect();
        if (conn != null) {
            try {
                // Query SQL untuk mengambil data
                String sql = "SELECT * FROM books";

                // Membuat statement
                Statement stmt = conn.createStatement();

                // Eksekusi query
                ResultSet rs = stmt.executeQuery(sql);

                // Iterasi hasil
                while (rs.next()) {
                    books.add(new Books(rs.getInt("book_id"), rs.getInt("price"), rs.getString("title"), rs.getString("author"), rs.getString("genre")));
                }

                // Tutup koneksi
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return books;
    }
}
