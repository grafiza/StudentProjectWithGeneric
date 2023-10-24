package StudentManagementProject2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentRepo implements Repository<Student> {
    DbProcess db = new DbProcess();
    Scanner scan = new Scanner(System.in);

    @Override
    public void save(Student object) {
        db.setConnection();
        String query = "insert into t_student1 (name,lastname,city,age) values(?,?,?,?)";
        db.setPreparedStatement(query);
        PreparedStatement ps = db.getPs();
        try {
            ps.setString(1, object.getName());
            ps.setString(2, object.getLastname());
            ps.setString(3, object.getCity());
            ps.setInt(4, object.getAge());
            ps.executeUpdate();
            System.out.println("Başarıyla kaydedildi.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public Student findById(int id) {
        Student student = null;
        db.setConnection();
        String sql = "select * from t_student1 where studentId=?";
        db.setPreparedStatement(sql);
        PreparedStatement ps = db.getPs();
        try {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("studentId"));
                student.setName(resultSet.getString("name"));
                student.setLastname(resultSet.getString("lastname"));
                student.setCity(resultSet.getString("city"));
                student.setAge(resultSet.getInt("age"));
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
        return student;
    }

    @Override
    public List<String> findAll() {
        db.setConnection();
        String query = "Select * from t_student1";
        db.setStatement();
        List<String> liste=new ArrayList<>();
        try {
            ResultSet rs = db.getSt().executeQuery(query);
            System.out.println("-".repeat(50));
            System.out.printf("%-3s %-13s %-13s %-13s %-5s\n", "id", "Adı", "Soyadı", "Şehir", "Yaş");
            while (rs.next()) {
                liste.add(rs.getInt("studentId")+","+
                        rs.getString("name")+","+
                        rs.getString("lastname")+","+
                        rs.getString("city")+","+
                        rs.getInt("age"));

            }
            System.out.println("-".repeat(50));

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

        return liste;
    }

    @Override
    public void update(Student foundStudent) {
        db.setConnection();
        String sql = "update t_student1 set name=?,lastname=?,city=?,age=? where studentId=?";
        db.setPreparedStatement(sql);
        PreparedStatement ps = db.getPs();
        try {
            ps.setString(1, foundStudent.getName());
            ps.setString(2, foundStudent.getLastname());
            ps.setString(3, foundStudent.getCity());
            ps.setInt(4, foundStudent.getAge());
            ps.setInt(5, foundStudent.getId());
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
        String query = "DELETE FROM t_student1 where studentId=?";
        db.setPreparedStatement(query);
        PreparedStatement ps = db.getPs();
        try {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted > 0)
                System.out.println("Kayıt silme işlemi başarılı");
            else
                System.out.println("Öğrenci bulunamadı");
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

    public void createStudentTable() {
        db.setConnection();
        db.setStatement();
        try {
            db.getSt().execute("CREATE TABLE IF NOT EXISTS t_student1 (" +
                    "studentId SERIAL UNIQUE," +
                    "name VARCHAR(20) NOT NULL CHECK(LENGTH(name)>0)," +
                    "lastname VARCHAR(20) NOT NULL CHECK(LENGTH(lastname)>0)," +
                    "city VARCHAR(20) NOT NULL CHECK(LENGTH(city)>0)," +
                    "age INT  NOT NULL CHECK(age>0))");
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
