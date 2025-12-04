import java.sql.*;
import java.util.*;

public class StudentRepository {

    public void add(Student s) {
        String sql = "INSERT INTO students(name,department) VALUES(?,?)";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.name); ps.setString(2, s.department);
            ps.executeUpdate();
            System.out.println("✅ Öğrenci eklendi.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(Student s) {
        String sql = "UPDATE students SET name=?,department=? WHERE id=?";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, s.name); ps.setString(2, s.department); ps.setInt(3, s.id);
            int r = ps.executeUpdate();
            System.out.println(r>0 ? "✅ Güncellendi." : "⚠️ ID bulunamadı.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int r = ps.executeUpdate();
            System.out.println(r>0 ? "✅ Silindi." : "⚠️ ID bulunamadı.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Student> search(String q) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR department LIKE ?";
        try (Connection c = Database.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            String s = "%" + q + "%";
            ps.setString(1, s); ps.setString(2, s);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("department")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        try (Connection c = Database.connect(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM students")) {
            while (rs.next())
                list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("department")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
