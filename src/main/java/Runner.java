import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
start();
    }

    private static void start() {
        StudentService service=new StudentService();
        service.createStudentTable();
        int select,id;
        do {
            System.out.println("1. Öğrenci Kaydı");
            System.out.println("2. Öğrencileri Listele");
            System.out.println("3. Öğrenci Güncelleme");
            System.out.println("4. Öğrenci Silme");
            System.out.println("5. Öğrenciyi Gösterme");
            System.out.println("0. Çıkış");
            Scanner scan = new Scanner(System.in);
            select = scan.nextInt();scan.nextLine();
            switch (select){
                case 1:
                    service.saveStudent();
                    service.getAllStudent();
                    break;
                case 2:
                    service.getAllStudent();
                    break;
                case 3:
                    id=getId(scan);
                    service.updateStudent(id);
                    service.getAllStudent();
                    break;
                case 4:
                    id=getId(scan);
                    service.deleteStudent(id);
                    service.getAllStudent();
                    break;
                case 5:
                    id=getId(scan);
                    service.displayStudent(id);
                case 0:
                    System.out.println("İyi Günler");
                    break;
                default:
                    System.out.println("Hatalı Giriş. Tekrar Deneyiniz!");
                    break;
            }
        }while (select!=0);
    }
    private static int getId(Scanner scan){
        System.out.println("Öğrenci Id:");
        int id=scan.nextInt();scan.nextLine();
        return id;
    }
}
