package StudentManagementProject2;

public class Teacher {
    private int teacherId;
    private String name;
    private String lastname;
    private String branch;

    public Teacher(String name, String lastname, String branch) {
        this.name = name;
        this.lastname = lastname;
        this.branch = branch;
    }

    public Teacher() {
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
