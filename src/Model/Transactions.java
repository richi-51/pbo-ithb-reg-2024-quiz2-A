package Model;

public class Transactions {
    private int id_transactions, user_id, book_id;

    // Constructor
    public Transactions(int id_transactions, int user_id, int book_id) {
        this.id_transactions = id_transactions;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    // Getter and Setter
    public int getId_transactions() {
        return id_transactions;
    }

    public void setId_transactions(int id_transactions) {
        this.id_transactions = id_transactions;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
