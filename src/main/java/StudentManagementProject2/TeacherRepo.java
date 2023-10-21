package StudentManagementProject2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TeacherRepo implements Repository<Teacher> {
    DbProcess db = new DbProcess();
    Scanner scan = new Scanner(System.in);

    @Override
    public void save(Teacher teacher) {
        db.setConnection();
        String query = "insert into t_teacher1 (name,lastname,branch) values(?,?,?)";
        db.setPreparedStatement(query);
        PreparedStatement ps = db.getPs();
        try {
            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getLastname());
            ps.setString(3, teacher.getBranch());
            ps.executeUpdate();
            System.out.println("Başarıyla kaydedildi.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                db.getCon().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Teacher findById(int id) {
        Teacher teacher = null;
        db.setConnection();
        String sql = "select * from t_teacher1 where teacherid=?";
        db.setPreparedStatement(sql);
        PreparedStatement ps = db.getPs();
        try {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(resultSet.getInt("teacherid"));
                teacher.setName(resultSet.getString("name"));
                teacher.setLastname(resultSet.getString("lastname"));
                teacher.setBranch(resultSet.getString("branch"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                db.getCon().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return teacher;


    }

    @Override
    public void findAll() {
        String query = "Select * from t_teacher1";
        db.setConnection();
        db.setStatement();
        try {
            ResultSet rs = db.getSt().executeQuery(query);
            System.out.println("-".repeat(40));
            System.out.printf("%-3s %-13s %-13s %-13s \n", "id", "Adı", "Soyadı", "Branşı");
            while (rs.next()) {

                System.out.printf("%-3s %-13s %-13s %-13s\n",
                        rs.getInt("teacherid"),
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("branch"));
            }
            System.out.println("-".repeat(40));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                db.getSt().close();
                db.getCon().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Devam etmek için bir tuşa basın");
        scan.nextLine();
    }

    @Override
    public void update(Teacher teacher) {
        db.setConnection();
        String sql = "update t_teacher1 set name=?,lastname=?,branch=? where teacherid=?";
        db.setPreparedStatement(sql);
        PreparedStatement ps = db.getPs();
        try {
            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getLastname());
            ps.setString(3, teacher.getBranch());
            ps.setInt(4, teacher.getTeacherId());
            int updated = ps.executeUpdate();
            if (updated > 0)
                System.out.println("Güncelleme İşlemi Başarılı");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                db.getCon().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void delete(int id) {
        db.setConnection();
        String query = "Delete from t_teacher1 where teacherid=?";
        db.setPreparedStatement(query);
        PreparedStatement ps = db.getPs();
        try {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted > 0)
                System.out.println("Kayıt silme işlemi başarılı");
            else
                System.out.println("Öğretmen bulunamadı");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                db.getCon().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Devam etmek için bir tuşa basın");
        scan.nextLine();
    }

    public void createTeacherTable() {
        db.setConnection();
        db.setStatement();
        try {
            db.getSt().execute("CREATE TABLE IF NOT EXISTS t_teacher1(" +
                    "teacherId SERIAL UNIQUE," +
                    "name VARCHAR(20) NOT NULL CHECK(LENGTH(name)>0)," +
                    "lastname VARCHAR(20) NOT NULL CHECK(LENGTH(lastname)>0)," +
                    "branch VARCHAR(20) NOT NULL CHECK(LENGTH(branch)>0))");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                db.getSt().close();
                db.getCon().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
