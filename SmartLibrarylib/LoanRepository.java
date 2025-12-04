import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class LoanRepository {

    public void borrowBook(int bookId, int studentId) {

        String check = "SELECT * FROM loans WHERE bookId=? AND dateReturned IS NULL";

        try (Connection c = Database.connect();
             PreparedStatement ps = c.prepareStatement(check)) {

            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Bu kitap zaten ödünçte!");
                return;
            }

        } catch (Exception e) { e.printStackTrace(); }

        String sql = "INSERT INTO loans(bookId,studentId,dateBorrowed) VALUES(?,?,?)";

        try (Connection c = Database.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ps.setInt(2, studentId);
            ps.setString(3, LocalDate.now().toString());
            ps.executeUpdate();
            System.out.println("Kitap ödünç verildi.");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void returnBook(int id) {
        String sql = "UPDATE loans SET dateReturned=? WHERE id=?";
        try (Connection c = Database.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Kitap iade alındı.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Loan> getAll() {
        List<Loan> list = new ArrayList<>();
        try (Connection c = Database.connect();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM loans")) {

            while (rs.next()) {
                list.add(new Loan(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("studentId"),
                        rs.getString("dateBorrowed"),
                        rs.getString("dateReturned")
                ));
            }

        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
