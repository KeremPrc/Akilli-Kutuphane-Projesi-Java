import java.util.Scanner;

public class Main {

    // Güvenli UTF-8 scanner
    static Scanner sc = new Scanner(System.in, "UTF-8");
    static BookRepository bookRepo = new BookRepository();
    static StudentRepository studentRepo = new StudentRepository();
    static LoanRepository loanRepo = new LoanRepository();

    public static void main(String[] args) {
        Database.createTables();

        while (true) {
            anaMenu();
            int secim = sayiAl("Seçiminiz: ");

            switch (secim) {
                case 1 -> kitapMenu();
                case 2 -> ogrenciMenu();
                case 3 -> oduncMenu();
                case 0 -> { System.out.println("Çıkılıyor..."); System.exit(0); }
                default -> System.out.println("❌ Hatalı seçim!");
            }
        }
    }

    static void anaMenu() {
        System.out.println("\n=== SMART LIBRARY ===");
        System.out.println("1 - Kitap İşlemleri");
        System.out.println("2 - Öğrenci İşlemleri");
        System.out.println("3 - Ödünç İşlemleri");
        System.out.println("0 - Çıkış");
    }

    // ---------------- KİTAP MENÜ ----------------
    static void kitapMenu() {
        while (true) {
            System.out.println("\n--- KİTAP MENÜ ---");
            System.out.println("1 - Kitap Ekle");
            System.out.println("2 - Kitapları Listele");
            System.out.println("3 - Kitap Ara");
            System.out.println("4 - Kitap Güncelle");
            System.out.println("5 - Kitap Sil");
            System.out.println("0 - Geri Dön");

            int c = sayiAl("Seçiminiz: ");
            switch (c) {
                case 1 -> kitapEkle();
                case 2 -> {
                    var all = bookRepo.getAll();
                    if (all.isEmpty()) System.out.println("Kayıtlı kitap yok.");
                    else all.forEach(System.out::println);
                }
                case 3 -> {
                    String q = yaziAl("Arama (ad/yazar): ");
                    var r = bookRepo.search(q);
                    if (r.isEmpty()) System.out.println("Eşleşen kitap yok.");
                    else r.forEach(System.out::println);
                }
                case 4 -> {
                    int id = sayiAl("Güncellenecek kitap ID: ");
                    String t = yaziAl("Yeni ad: ");
                    String a = yaziAl("Yeni yazar: ");
                    int y = sayiAl("Yeni yıl: ");
                    bookRepo.update(new Book(id, t, a, y));
                }
                case 5 -> {
                    int id = sayiAl("Silinecek kitap ID: ");
                    bookRepo.delete(id);
                }
                case 0 -> { return; }
                default -> System.out.println("❌ Hatalı seçim!");
            }
        }
    }

    // ---------------- ÖĞRENCİ MENÜ ----------------
    static void ogrenciMenu() {
        while (true) {
            System.out.println("\n--- ÖĞRENCİ MENÜ ---");
            System.out.println("1 - Öğrenci Ekle");
            System.out.println("2 - Öğrencileri Listele");
            System.out.println("3 - Öğrenci Ara");
            System.out.println("4 - Öğrenci Güncelle");
            System.out.println("5 - Öğrenci Sil");
            System.out.println("0 - Geri Dön");

            int c = sayiAl("Seçiminiz: ");
            switch (c) {
                case 1 -> {
                    String n = yaziAl("Ad Soyad: ");
                    String d = yaziAl("Bölüm: ");
                    studentRepo.add(new Student(n, d));
                }
                case 2 -> {
                    var all = studentRepo.getAll();
                    if (all.isEmpty()) System.out.println("Kayıtlı öğrenci yok.");
                    else all.forEach(System.out::println);
                }
                case 3 -> {
                    String q = yaziAl("Arama (ad/bölüm): ");
                    var r = studentRepo.search(q);
                    if (r.isEmpty()) System.out.println("Eşleşen öğrenci yok.");
                    else r.forEach(System.out::println);
                }
                case 4 -> {
                    int id = sayiAl("Güncellenecek öğrenci ID: ");
                    String n = yaziAl("Yeni ad: ");
                    String d = yaziAl("Yeni bölüm: ");
                    studentRepo.update(new Student(id, n, d));
                }
                case 5 -> {
                    int id = sayiAl("Silinecek öğrenci ID: ");
                    studentRepo.delete(id);
                }
                case 0 -> { return; }
                default -> System.out.println("❌ Hatalı seçim!");
            }
        }
    }

    // ---------------- ÖDÜNÇ MENÜ ----------------
    static void oduncMenu() {
        while (true) {
            System.out.println("\n--- ÖDÜNÇ MENÜ ---");
            System.out.println("1 - Kitap Ödünç Ver");
            System.out.println("2 - Ödünç Listesi");
            System.out.println("3 - Kitap Geri Al");
            System.out.println("0 - Geri Dön");

            int c = sayiAl("Seçiminiz: ");
            switch (c) {
                case 1 -> {
                    int bid = sayiAl("Kitap ID: ");
                    int sid = sayiAl("Öğrenci ID: ");
                    loanRepo.borrowBook(bid, sid);
                }
                case 2 -> {
                    var all = loanRepo.getAll();
                    if (all.isEmpty()) System.out.println("Ödünç kaydı yok.");
                    else all.forEach(System.out::println);
                }
                case 3 -> {
                    int id = sayiAl("Loan ID: ");
                    loanRepo.returnBook(id);
                }
                case 0 -> { return; }
                default -> System.out.println("❌ Hatalı seçim!");
            }
        }
    }

    // ---------------- YARDIMCI OKUMA METOTLARI (GÜVENLİ) ----------------
    static int sayiAl(String mesaj) {
        while (true) {
            System.out.print(mesaj + " [SAYI]: ");
            String line = sc.nextLine();
            if (line == null) line = "";
            line = line.trim();
            if (line.isEmpty()) {
                System.out.println("⚠️ Boş bırakmayınız!");
                continue;
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Lütfen geçerli bir sayı giriniz!");
            }
        }
    }

    static String yaziAl(String mesaj) {
        while (true) {
            System.out.print(mesaj + " [YAZI]: ");
            String line = sc.nextLine();
            if (line == null) line = "";
            line = line.trim();
            if (line.isEmpty()) {
                System.out.println("⚠️ Boş bırakamazsınız!");
                continue;
            }
            return line;
        }
    }

    static void kitapEkle() {
        String t = yaziAl("Kitap adı: ");
        String a = yaziAl("Yazar: ");
        int y = sayiAl("Yıl: ");
        bookRepo.add(new Book(t, a, y));
    }
}
