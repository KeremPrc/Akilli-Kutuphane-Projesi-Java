public class Loan {
    public int id;
    public int bookId, studentId;
    public String borrowed, returned;

    public Loan(int i, int b, int s, String br, String re) {
        id = i;
        bookId = b;
        studentId = s;
        borrowed = br;
        returned = re;
    }

    public String toString() {
        return id + " | Kitap:" + bookId + " | Öğrenci:" + studentId +
               " | Alış:" + borrowed + " | Teslim:" + returned;
    }
}
