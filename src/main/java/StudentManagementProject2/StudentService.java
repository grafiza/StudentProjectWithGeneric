package StudentManagementProject2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentService implements GenericService{
    Scanner scan = new Scanner(System.in);
    private StudentRepo stRepo = new StudentRepo();


    @Override
    public void createTable() {
        stRepo.createStudentTable();
    }

    @Override
    public void save() {
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

    @Override
    public void getAll() {
        List<String> liste=new ArrayList<>();
        liste=stRepo.findAll();
        for (int i = 0; i <liste.size(); i++) {
            System.out.printf("%-3s %-13s %-13s %-13s %-5s\n",
                    liste.get(i).split(",")[0],
                    liste.get(i).split(",")[1],
                    liste.get(i).split(",")[2],
                    liste.get(i).split(",")[3],
                    liste.get(i).split(",")[4]);
        }
    }

    @Override
    public void delete(int id) {
        stRepo.delete(id);
    }

    @Override
    public void display(int id) {
        Student std=getById(id);
        System.out.println("-".repeat(70));
        System.out.printf("%-3s %-20s %-20s %-20s %-5s\n","id","Adı","Soyadı","Şehir","Yaş");
        if(std!=null){
            System.out.printf("%-3s %-20s %-20s %-20s %-5s\n",std.getId(),std.getName(), std.getLastname(),std.getCity(),std.getAge());
        }else System.out.println("Öğrenci Bulunamadı");
        System.out.println("-".repeat(70));
        System.out.println("Devam etmek için Enter'a basın");
        scan.nextLine();
    }

    @Override
    public void update(int id) {
        Student foundStudent=getById(id);
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

    @Override
    public Student getById(int id) {
        return stRepo.findById(id);
    }


}
