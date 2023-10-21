package StudentManagementProject2;

import java.util.Scanner;

public class StudentService {
    Scanner scan = new Scanner(System.in);
    private StudentRepo stRepo = new StudentRepo();

    public void createStudentTable() {
        stRepo.createStudentTable();
    }
    public void saveStudent(){
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
        stRepo.save(std);
    }

    public void getAllStudent() {
        stRepo.findAll();
    }
    public void deleteStudent(int id) {
        stRepo.delete(id);
    }
    public void displayStudent(int id) {
        Student std=getStudentById(id);
        System.out.println("-".repeat(70));
        System.out.printf("%-3s %-20s %-20s %-20s %-5s\n","id","Adı","Soyadı","Şehir","Yaş");
        if(std!=null){
            System.out.printf("%-3s %-20s %-20s %-20s %-5s\n",std.getId(),std.getName(), std.getLastname(),std.getCity(),std.getAge());
        }else System.out.println("Öğrenci Bulunamadı");
        System.out.println("-".repeat(70));
        System.out.println("Devam etmek için Enter'a basın");
        scan.nextLine();
    }
    private Student getStudentById(int id) {
        return stRepo.findById(id);
    }
    public void updateStudent(int id) {
        Student foundStudent=getStudentById(id);
        if(foundStudent!=null){
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
            foundStudent.setLastname(lastName);
            foundStudent.setCity(city);
            foundStudent.setAge(age);
            stRepo.update(foundStudent);
        }else System.out.println("Öğrenci Bulunamadı");
    }
}
