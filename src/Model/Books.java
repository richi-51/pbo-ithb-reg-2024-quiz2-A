package Model;

public class Books {
    private int id, price;
    private String title, author, genre;

    // Constructor
    public Books(int id, int price, String title, String author, String genre) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
