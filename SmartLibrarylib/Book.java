public class Book {
    public int id;
    public String title, author;
    public int year;

    public Book(String t, String a, int y) {
        title = t;
        author = a;
        year = y;
    }

    public Book(int i, String t, String a, int y) {
        id = i;
        title = t;
        author = a;
        year = y;
    }

    public String toString() {
        return id + " | " + title + " | " + author + " | " + year;
    }
}
