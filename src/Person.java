public class Person {
    String ID;
    String name;
    String birthplace;
    String dob;
    public Person() {}

    public Person(String ID, String name, String birthplace, String dob) {
        this.ID = ID;
        this.name = name;
        this.birthplace = birthplace;
        this.dob = dob;
    }

    @Override

    public String toString() {
        return String.format("%-10s %-10s %-20s %-10s", ID, name, dob, birthplace);
    }
}



