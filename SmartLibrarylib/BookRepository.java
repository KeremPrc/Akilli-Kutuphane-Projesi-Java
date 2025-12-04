import java.sql.*;
import java.util.*;

public class BookRepository {

    public void add(Book b) {
        String sql = "INSERT INTO books(title,author,year) VALUES(?,?,?)";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.title); ps.setString(2, b.author); ps.setInt(3, b.year);
            ps.executeUpdate();
            System.out.println("✅ Kitap eklendi.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(Book b) {
        String sql = "UPDATE books SET title=?,author=?,year=? WHERE id=?";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.title); ps.setString(2, b.author); ps.setInt(3, b.year); ps.setInt(4, b.id);
            int r = ps.executeUpdate();
            System.out.println(r>0 ? "✅ Güncellendi." : "⚠️ ID bulunamadı.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int r = ps.executeUpdate();
            System.out.println(r>0 ? "✅ Silindi." : "⚠️ ID bulunamadı.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Book> search(String q) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            String s = "%" + q + "%";
            ps.setString(1, s); ps.setString(2, s);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<Book> getAll() {
        List<Book> list = new ArrayList<>();
        try (Connection c = Database.connect(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM books")) {
            while (rs.next())
                list.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
