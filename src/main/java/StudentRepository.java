import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;

    public PreparedStatement getPs() {
        return ps;
    }

    private void setConnection() {
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcnt_db", "techpront", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setStatement() {
        try {
            this.st = this.con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setPreparedStatement(String query) {
        try {
            this.ps = con.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable() {
        setConnection();
        setStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS t_student (" +
                    " id SERIAL UNIQUE," +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0 )," +
                    "lastName VARCHAR(50) NOT NULL CHECK(LENGTH(lastName)>0 )," +
                    "city VARCHAR(50) NOT NULL CHECK(LENGTH(city)>0 )," +
                    "age INT NOT NULL CHECK (age>0)" +
                    ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    public void save(Student std) {
        setConnection();
        String query = "insert into t_student (name,lastname,city,age) values(?,?,?,?)";
        setPreparedStatement(query);
        try {
            ps.setString(1, std.getName());
            ps.setString(2, std.getSurName());
            ps.setString(3, std.getCity());
            ps.setInt(4, std.getAge());
            ps.executeUpdate();
            System.out.println("Başarıyla kaydedildi.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void findAll() {
        List<Student> std = new ArrayList<>();
        setConnection();
        String query = "Select * from t_student";
        setStatement();
        try {
            ResultSet rs = st.executeQuery(query);
            System.out.println("--------------------------------------------");
            while (rs.next()) {


                System.out.print(rs.getInt("id") + "\t");
                System.out.print(rs.getString("name") + "\t");
                System.out.print(rs.getString("lastname") + "\t");
                System.out.print(rs.getString("city") + "\t");
                System.out.print(rs.getInt("age") + "\n");

            }
            System.out.println("--------------------------------------------");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void delete(int id) {
        setConnection();
        String query = "DELETE FROM t_student where id=?";
        setPreparedStatement(query);
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
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public Student findById(int id) {
        Student student=null;
        setConnection();
        String sql = "select * from t_student where id=?";
        setPreparedStatement(sql);
        try {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurName(resultSet.getString("lastname"));
                student.setCity(resultSet.getString("city"));
                student.setAge(resultSet.getInt("age"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return student;
    }

    public void updateStudent(Student foundStudent) {
        setConnection();
        String sql="update t_student set name=?,lastname=?,city=?,age=? where id=?";
        setPreparedStatement(sql);
        try {
            ps.setString(1,foundStudent.getName());
            ps.setString(2,foundStudent.getSurName());
            ps.setString(3,foundStudent.getCity());
            ps.setInt(4,foundStudent.getAge());
            ps.setInt(5,foundStudent.getId());
           int updated= ps.executeUpdate();
            if(updated>0)
                System.out.println("Güncelleme İşlemi Başarılı");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}


