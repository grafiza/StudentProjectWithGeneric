public class Student {
    private int id;
    private String name;
    private String surName;

    private String city;
    private int age;

    public Student() {
    }

    public Student(String name, String surName,  String city, int age) {
        this.name = name;
        this.surName = surName;

        this.city = city;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name=" + name +
                ", surName=" + surName +

                ", city=" + city +
                ", age=" + age
                ;
    }
}
