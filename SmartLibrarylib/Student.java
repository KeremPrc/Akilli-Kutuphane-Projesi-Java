public class Student {
    public int id;
    public String name, department;

    public Student(String n, String d) {
        name = n;
        department = d;
    }

    public Student(int i, String n, String d) {
        id = i;
        name = n;
        department = d;
    }

    public String toString() {
        return id + " | " + name + " | " + department;
    }
}
