package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Transaction extends JFrame {
    private Users user;

    public Transaction(Users user) {
        this.user = user;

        ArrayList<Books> books = new ArrayList<Books>();

        String sql = "SELECT * FROM transactions t INNER JOIN books b ON t.book_id = b.book_id  WHERE user_id = " + user.getId_user();

        JTable tabel = new JTable(books.size()+1, 5);
        DefaultTableModel tb= new DefaultTableModel();
        // Memberi nama pada setiap kolom tabel
        tb.addColumn("Transaction ID");
        tb.addColumn("User Name");
        tb.addColumn("Book Title");
        tb.addColumn("Book Genre");
        tb.addColumn("Price");
        tabel.setModel(tb);

        try (Connection conn = DatabaseManager.connect()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                tabel.addRow(new Object[]{rs.getInt("id_transaction"), user.getName(), rs.getString("title"),  rs.getString("genre"), rs.getInt("price") });
            }

        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal melakukan operasi data!");
        }
        



    }

}
