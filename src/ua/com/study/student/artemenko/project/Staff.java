package ua.com.study.student.artemenko.project;

public class Staff {
    private Person person;
    private Specialty specialty;

    public Staff(Person person, Specialty specialty) {
        this.person = person;
        this.specialty = specialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
