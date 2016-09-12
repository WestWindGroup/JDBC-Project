package ua.com.study.student.artemenko.project;

public class Specialty {
    private String nameSpecialty;
    private double salary;

    public Specialty(String nameSpecialty, double salary) {
        this.nameSpecialty = nameSpecialty;
        this.salary = salary;
    }

    public String getNameSpecialty() {
        return nameSpecialty;
    }

    public void setNameSpecialty(String nameSpecialty) {
        this.nameSpecialty = nameSpecialty;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
