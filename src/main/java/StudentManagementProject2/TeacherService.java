package StudentManagementProject2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TeacherService implements GenericService{
    Scanner scan = new Scanner(System.in);
    private TeacherRepo teacherRepo = new TeacherRepo();
    @Override
    public void createTable() {
        teacherRepo.createTeacherTable();
    }
    @Override
    public void save() {
        System.out.println("Ad:");
        String name = scan.nextLine().trim();
        System.out.println("Soyad:");
        String lastName = scan.nextLine().trim();
        System.out.println("Branş:");
        String branch = scan.nextLine().trim();
        Teacher teacher = new Teacher(name, lastName, branch);
        teacherRepo.save(teacher);
    }
    @Override
    public void getAll() {
        List<String> liste=new ArrayList<>();
        liste=teacherRepo.findAll();
        for (int i = 0; i <liste.size(); i++) {
            System.out.printf("%-3s %-13s %-13s %-13s \n",
                    liste.get(i).split(",")[0],
                    liste.get(i).split(",")[1],
                    liste.get(i).split(",")[2],
                    liste.get(i).split(",")[3]);
        }
    }

    @Override
    public void delete(int id) {
        teacherRepo.delete(id);
    }
    @Override
    public void display(int id) {
        Teacher teacher=getById(id);
        System.out.println("-".repeat(45));
        System.out.printf("%-3s %-13s %-13s %-13s\n","id","Adı","Soyadı","Branş");
        if(teacher!=null){
            System.out.printf("%-3s %-13s %-13s %-13s\n",teacher.getTeacherId(),teacher.getName(), teacher.getLastname(),teacher.getBranch());
        }else System.out.println("Öğrenci Bulunamadı");
        System.out.println("-".repeat(45));
        System.out.println("Devam etmek için Enter'a basın");
        scan.nextLine();
    }
    @Override
    public void update(int id) {
        Teacher foundTeacher=getById(id);
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
    @Override
    public Teacher getById(int id) {
        return teacherRepo.findById(id);
    }
}
