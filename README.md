SmartLibrary – Akıllı Kütüphane Yönetim Sistemi

Java OOP + JDBC + SQLite kullanılarak geliştirilmiş basit bir masaüstü konsol tabanlı kütüphane yönetim sistemidir.
Sistem kitap, öğrenci ve ödünç alma işlemlerinin yönetimini sağlar.

Özellikler:

📘 Kitap işlemleri

- Kitap ekleme

- Kitapları listeleme

- Güncelleme & silme (Repository içinde)

🎓 Öğrenci işlemleri

- Öğrenci ekleme

- Öğrencileri listeleme

🔄 Ödünç alma işlemleri

- Kitap ödünç verme

- Kitabın ödünçte olup olmadığını kontrol etme

- Ödünç listesini görüntüleme

- Kitap iade işlemi


SQLite Tablo Yapısı:
  
| Alan   | Tip                         |
| ------ | --------------------------- |
| id     | INTEGER (PK, AUTOINCREMENT) |
| title  | TEXT                        |
| author | TEXT                        |
| year   | INTEGER                     |

| Alan       | Tip                         |
| ---------- | --------------------------- |
| id         | INTEGER (PK, AUTOINCREMENT) |
| name       | TEXT                        |
| department | TEXT                        |

| Alan         | Tip                         |
| ------------ | --------------------------- |
| id           | INTEGER (PK, AUTOINCREMENT) |
| bookId       | INTEGER                     |
| studentId    | INTEGER                     |
| dateBorrowed | TEXT                        |
| dateReturned | TEXT                        |


📜 Uygulama Menüsü
1 - Kitap Ekle
2 - Kitapları Listele
3 - Öğrenci Ekle
4 - Öğrencileri Listele
5 - Kitap Ödünç Ver
6 - Ödünç Listesini Görüntüle
7 - Kitap Geri Teslim Al
0 - Çıkış


! Projenin çalışması için SQLite JDBC sürücüsü gerekmektedir:
sqlite-jdbc-3.36.0.3.jar

! ÇALIŞTIRMA !
javac -classpath ".;lib/sqlite-jdbc.jar" Main.java
java -classpath ".;lib/sqlite-jdbc.jar" Main


🧪 OOP Yapısının Kısa Özeti

Class Kullanımı: Kitap, Öğrenci ve Loan yapıları ayrı modeller olarak oluşturulmuştur.

Nesne İlişkileri: Loan → Book & Student arasında ilişki kurar.

Constructors: Modeller parametreli/parametresiz constructor içerir.

Repository Pattern: Her entity için CRUD işlemlerini yöneten repository sınıfları vardır.

PreparedStatement: SQL injection koruması sağlanır.

Veritabanı Otomatik Oluşturma: Program açılırken gerekli tablolar oluşturulur.
