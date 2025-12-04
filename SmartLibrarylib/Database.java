import java.sql.*;

public class Database {
    static final String URL = "jdbc:sqlite:smartlibrary.db";

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTables() {
        String books = "CREATE TABLE IF NOT EXISTS books(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT, year INTEGER)";
        String students = "CREATE TABLE IF NOT EXISTS students(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, department TEXT)";
        String loans = "CREATE TABLE IF NOT EXISTS loans(id INTEGER PRIMARY KEY AUTOINCREMENT, bookId INTEGER, studentId INTEGER, dateBorrowed TEXT, dateReturned TEXT)";

        try (Connection con = connect(); Statement st = con.createStatement()) {
            st.execute(books);
            st.execute(students);
            st.execute(loans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
