package ua.com.study.student.artemenko.project;

public class Person {

    private String lastName;
    private String firstName;
    int age;
    int id_person;

    public Person(String lastName, int id_person, String firstName, int age) {
        this.lastName = lastName;
        this.id_person = id_person;
        this.firstName = firstName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }
}
