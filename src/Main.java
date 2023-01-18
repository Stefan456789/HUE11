import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (FileList p = new FileList(new File("persons.dat"))) {
            System.out.println("\nPERSONALVERWALTUNG\n");
            System.out.println("1. Neue Person hinzufügen");
            System.out.println("2. Person bearbeiten");
            System.out.println("3. Person löschen");
            System.out.println("4. Personen suchen");
            System.out.println("5. Liste aller Personen anzeigen");
            System.out.println("6. Analysefunktionen");
            System.out.println("7. Beenden");
            Scanner s = new Scanner(System.in);

            switch (Integer.parseInt(s.nextLine())) {
                case 1:
                    p.add(input(s));
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Suchparameter: ");
                    String deleteSearchStr = s.nextLine();
                    var match = search(deleteSearchStr, p);
                    for (int i = 0; i < match.size(); i++) {
                        System.out.println("Willst du " + match.get(i) + " löschen? (true/false)");
                        if (Boolean.parseBoolean(s.nextLine())){
                            p.remove(match.get(i));
                            p.update();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Suchparameter: ");
                    String searchStr = s.nextLine();
                    System.out.println("Gefundene Personen:");
                    search(searchStr, p).forEach(System.out::println);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static List<Person> search(String search, List<Person> l){
        List<Person> matches = new ArrayList<Person>();
        for(Person p : l){
            if (p.toString().contains(search)){
                matches.add(p);
            }
        }
        return matches;
    }

    private static Person input(Scanner s) {
        int id;
        System.out.println("Id:");
        id = Integer.parseInt(s.nextLine());

        String firstname;
        System.out.println("Firstname:");
        firstname = s.nextLine();

        String lastname;
        System.out.println("Lastname:");
        lastname = s.nextLine();

        LocalDate birthdate;
        System.out.println("Birthdate (yyyy-mm-dd):");
        birthdate = LocalDate.parse(s.nextLine());

        boolean male;
        System.out.println("Männlich (true/false):");
        male = Boolean.parseBoolean(s.nextLine());

        double salary;
        System.out.println("Salary:");
        salary = Double.parseDouble(s.nextLine());

        String street;
        System.out.println("Street:");
        street = s.nextLine();

        int houseNumber;
        System.out.println("House Number:");
        houseNumber = Integer.parseInt(s.nextLine());

        int zipCode;
        System.out.println("Zip Code:");
        zipCode = Integer.parseInt(s.nextLine());

        String city;
        System.out.println("City:");
        city = s.nextLine();

        String country;
        System.out.println("Salary:");
        country = s.nextLine();

        Address address = new Address(street,
                houseNumber,
                zipCode,
                city,
                country);

        String phoneNumber;
        System.out.println("Phone Number:");
        phoneNumber = s.nextLine();

        String email;
        System.out.println("Email:");
        email = s.nextLine();

        String jobTitle;
        System.out.println("Job Title:");
        jobTitle = s.nextLine();

        String department;
        System.out.println("Department:");
        department = s.nextLine();

        LocalTime workStart;
        System.out.println("Work Start (hh:mm):");
        workStart = LocalTime.parse(s.nextLine());

        LocalTime workEnd;
        System.out.println("Work End (hh:mm):");
        workEnd = LocalTime.parse(s.nextLine());

        String notes;
        System.out.println("Notes:");
        notes = s.nextLine();

        return new Person(id,
                firstname,
                lastname,
                birthdate,
                male,
                salary,
                address,
                phoneNumber,
                email,
                jobTitle,
                department,
                workStart,
                workEnd,
                notes);
    }
}
