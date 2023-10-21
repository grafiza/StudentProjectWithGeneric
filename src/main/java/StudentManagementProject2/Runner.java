package StudentManagementProject2;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        StudentService ss = new StudentService();
        TeacherService ts = new TeacherService();
        ss.createStudentTable();
        ts.createTeacherTable();
        int select, id;
        do {
            System.out.println("Okul Yönetim Paneli");
            System.out.println("1. Öğrenci Ekle");
            System.out.println("2. Öğrenci Listele");
            System.out.println("3. Öğrenci Güncelle");
            System.out.println("4. Öğrenci Sil");
            System.out.println("5. Öğrenci Getir");
            System.out.println("6. Öğretmen Ekle");
            System.out.println("7. Öğretmen Listele");
            System.out.println("8. Öğretmen Güncelle");
            System.out.println("9. Öğretmen Sil");
            System.out.println("10. Öğretmen Getir");
            System.out.println("0. Çıkış");

            select = scan.nextInt();
            scan.nextLine();
            switch (select) {
                case 1:
                    ss.saveStudent();
                    break;
                case 2:
                    ss.getAllStudent();
                    break;
                case 3:
                    id = getId(scan, "Öğrenci");
                    ss.updateStudent(id);
                    break;
                case 4:
                    id = getId(scan, "Öğrenci");
                    ss.deleteStudent(id);
                    break;
                case 5:
                    id = getId(scan, "Öğrenci");
                    ss.displayStudent(id);
                    break;
                case 6:
                    ts.saveTeacher();
                    break;
                case 7:
                    ts.getAllTeacher();
                    break;
                case 8:
                    id = getId(scan, "Öğretmen");
                    ts.updateTeacher(id);
                    break;
                case 9:
                    id = getId(scan, "Öğretmen");
                    ts.deleteTeacher(id);
                    break;
                case 10:
                    id = getId(scan, "Öğretmen");
                    ts.displayTeacher(id);
                    break;
                case 0:
                    System.out.println("İyi Günler");
                    break;
                default:
                    System.out.println("Hatalı Giriş");
                    break;
            }
        } while (select != 0);
    }

    public static int getId(Scanner scan, String value) {
        System.out.println("İşlem yapmak istediğiniz " + value + " id'si");
        int id = scan.nextInt();
        scan.nextLine();
        return id;
    }
}
