package Model;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.GetBookList;

public class MenuBookList extends JFrame {
    private JButton buttonTrans, buttonBuy;
    private JLabel titleLabel, authorLabel, genreLabel, priceLabel;
    private Users user;
    private ArrayList<Books> books = new GetBookList().getDataBukuFromDB();
    public static int counter = 3;

    // Constructor
    public MenuBookList(Users user){
        setTitle("Menu Book List");
        setSize(720, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        buttonTrans = new JButton("Transactions");
        buttonTrans.addActionListener(e -> new Transaction(user)); // nanti ditambahin


        JPanel formPanel = new JPanel(new GridLayout(books.size()+1, 6));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonBuy = new JButton("Buy Button");
        
        formPanel.add(new JLabel("Title"));
        formPanel.add(new JLabel("Image"));
        formPanel.add(new JLabel("Author"));
        formPanel.add(new JLabel("Genre"));
        formPanel.add(new JLabel("Price"));
        formPanel.add(new JLabel("Buy Book"));
        
        for (Books book : books) {
            formPanel.add(new JLabel(book.getTitle()));
            formPanel.add(new JLabel("None"));
            formPanel.add(new JLabel(book.getAuthor()));
            formPanel.add(new JLabel(book.getGenre()));
            formPanel.add(new JLabel(String.valueOf(book.getPrice())));
            buttonBuy.addActionListener(e -> insert_CheckTrans(book.getId()));
            formPanel.add(buttonBuy);
        }
        
        add(formPanel, BorderLayout.CENTER);        
        this.user = user;
    }
    
    public void insert_CheckTrans(int bookID){
        boolean cek = false;
        
        String sqlCek = "SELECT * FROM transactions WHERE user_id = " + user.getId_user() + "AND book_id = " + bookID;

        try (Connection conn = DatabaseManager.connect()){
            PreparedStatement pstmt = conn.prepareStatement(sqlCek);
                cek = pstmt.execute(sqlCek);
                if (cek) {
                    JOptionPane.showMessageDialog(null, "Anda sudah membeli buku ini!");
                }
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal melakukan operasi data!");
        }

        if (!cek) {
            String sqlInsert = "INSERT INTO transactions (id_transaction, user_id, book_id) VALUES (?, ?, ?)";
            

            try (Connection conn = DatabaseManager.connect()){
                PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                pstmt.setInt(1, counter);
                pstmt.setInt(2, user.getId_user());
                pstmt.setInt(3, bookID);
                    
                pstmt.executeUpdate(sqlInsert);
                JOptionPane.showMessageDialog(null, "Buku berhasil dibeli");
                
            } catch(Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Gagal melakukan operasi data!");
            }
        }


    }


}
