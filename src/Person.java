import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public record Person(int id,
                     String firstname,
                     String lastname,
                     LocalDate birthdate,
                     boolean male,
                     double salary,
                     Address address,
                     String phoneNumber,
                     String email,
                     String jobTitle,
                     String department,
                     LocalTime workStart,
                     LocalTime workEnd,
                     String notes) {
    private static int runningId = 0;
    public Person {
        if (runningId > id){
            id = runningId;
            runningId++;
        } else {
            runningId = id + 1;
        }
    }

    public Person withfirstname(String firstname) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withlastname(String lastname) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withbirthdate(LocalDate birthdate) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withgender(boolean male) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withsalary(double salary) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withaddress(Address address) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withphoneNumber(String phoneNumber) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withemail(String email) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withjobTitle(String jobTitle) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withdepartment(String department) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withworkStart(LocalTime workStart) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withworkEnd(LocalTime workEnd) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }
    public Person withnotes(String notes) {
        return new Person(id, firstname, lastname, birthdate, male, salary, address, phoneNumber, email, jobTitle, department, workStart, workEnd, notes);
    }

    @Override
    public String toString() {
        return Arrays.stream(this.getClass().getDeclaredFields()).map(f -> {
            try {
                return f.getName() + ": " + f.get(this).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining(", "));
    }


}
