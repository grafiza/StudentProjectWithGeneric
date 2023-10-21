package StudentManagementProject2;

import java.util.Scanner;

public class TeacherService {
    Scanner scan = new Scanner(System.in);
    private TeacherRepo teacherRepo = new TeacherRepo();
    public void createTeacherTable() {
        teacherRepo.createTeacherTable();
    }

    public void saveTeacher() {
        System.out.println("Ad:");
        String name = scan.nextLine().trim();
        System.out.println("Soyad:");
        String lastName = scan.nextLine().trim();
        System.out.println("Branş:");
        String branch = scan.nextLine().trim();

        Teacher teacher = new Teacher(name, lastName, branch);
        teacherRepo.save(teacher);
    }

    public void getAllTeacher() {
        teacherRepo.findAll();
    }
    public void updateTeacher(int id) {
        Teacher foundTeacher=getTeacherById(id);
        if(foundTeacher!=null){
            System.out.println("Ad:");
            String name = scan.nextLine().trim();
            System.out.println("Soyad:");
            String lastName = scan.nextLine().trim();
            System.out.println("Branş:");
            String city = scan.nextLine().trim();

            foundTeacher.setName(name);
            foundTeacher.setLastname(lastName);
            foundTeacher.setBranch(city);
            teacherRepo.update(foundTeacher);
        }else System.out.println("Öğretmen Bulunamadı");
    }

    public void deleteTeacher(int id) {
        teacherRepo.delete(id);
    }

    public void displayTeacher(int id) {
        Teacher teacher=getTeacherById(id);
        System.out.println("-".repeat(45));
        System.out.printf("%-3s %-13s %-13s %-13s\n","id","Adı","Soyadı","Branş");
        if(teacher!=null){
            System.out.printf("%-3s %-13s %-13s %-13s\n",teacher.getTeacherId(),teacher.getName(), teacher.getLastname(),teacher.getBranch());
        }else System.out.println("Öğrenci Bulunamadı");
        System.out.println("-".repeat(45));
        System.out.println("Devam etmek için Enter'a basın");
        scan.nextLine();
    }

    private Teacher getTeacherById(int id) {
        return teacherRepo.findById(id);
    }
}
