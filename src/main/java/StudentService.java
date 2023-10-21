import java.util.Scanner;

public class StudentService {
    Scanner scan = new Scanner(System.in);
    private StudentRepository repo = new StudentRepository();

    public void createStudentTable() {

        repo.createTable();
    }

    public void saveStudent() {
        System.out.println("Ad:");
        String name = scan.nextLine().trim();
        System.out.println("Soyad:");
        String lastName = scan.nextLine().trim();
        System.out.println("Şehir:");
        String city = scan.nextLine().trim();
        System.out.println("Yaş:");
        int age = scan.nextInt();
        scan.nextLine();
        Student std = new Student(name, lastName, city, age);
        repo.save(std);
    }

    public void getAllStudent() {
        repo.findAll();
    }

    public void deleteStudent(int id) {
        repo.delete(id);
    }

    public Student getStudentById(int id) {
        Student student = repo.findById(id);
        return student;
    }

    public void displayStudent(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else System.out.println("Öğrenci bulunamadı");
    }

    public void updateStudent(int id) {
        Student foundStudent = getStudentById(id);
        if (foundStudent != null) {
            System.out.println("Ad:");
            String name = scan.nextLine().trim();
            System.out.println("Soyad:");
            String lastName = scan.nextLine().trim();
            System.out.println("Şehir:");
            String city = scan.nextLine().trim();
            System.out.println("Yaş:");
            int age = scan.nextInt();
            scan.nextLine();
            foundStudent.setName(name);
            foundStudent.setSurName(lastName);
            foundStudent.setCity(city);
            foundStudent.setAge(age);
            repo.updateStudent(foundStudent);
        } else System.out.println("Öğrenci bulunamadı");
    }


}
