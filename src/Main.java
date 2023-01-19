import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        try {
            FileList p = new FileList(new File("persons.dat"));
            //p.add(new Person(0, "debug", "debug", LocalDate.now(), true, 500, new Address("debug", 0, 0, "debug", "debug"), "debug", "debug", "debug", "debug", LocalTime.now(), LocalTime.now(), "debug"));
            while (true) {
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
                        System.out.println("Suchparameter: ");
                        String changeSearchStr = s.nextLine();
                        var match = search(changeSearchStr, p);
                        for (int i = 0; i < match.size(); i++) {
                            System.out.println("Willst du " + match.get(i) + " bearbeiten? (true/false)");
                            if (Boolean.parseBoolean(s.nextLine())) {
                                p.remove(match.get(i));
                                p.add(change(s, match.get(i)));
                                p.update();
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Suchparameter: ");
                        String deleteSearchStr = s.nextLine();
                        match = search(deleteSearchStr, p);
                        for (int i = 0; i < match.size(); i++) {
                            System.out.println("Willst du " + match.get(i) + " löschen? (true/false)");
                            if (Boolean.parseBoolean(s.nextLine())) {
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
                        p.forEach(System.out::println);
                        break;
                    case 6:
                        long numbMale = p.stream().filter(Person::male).count();
                        System.out.println("Es wurden " + numbMale + " Männliche und " + (p.size() - numbMale) + " Weibliche Personen gefunden");
                        System.out.println("Das Durchschnittsalter ist " + p.stream().mapToDouble(x -> x.birthdate().until(LocalDate.now(), ChronoUnit.YEARS)).average().orElse(0) + " Jahre.");
                        break;
                    case 7:
                        return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static List<Person> search(String search, List<Person> l) {
        List<Person> matches = new ArrayList<Person>();
        for (Person p : l) {
            if (p.toString().contains(search)) {
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
        System.out.println("Männlich (true|false):");
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
        System.out.println("Country:");
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

    private static Person change(Scanner s, Person from) {
        System.out.println("Was willst du ändern?(Firstname|Lastname|Birthdate|Gender|Salary|Address|phonenumber|email|jobtitle|department|workstart|workend|notes):");
        switch (s.nextLine().toLowerCase()) {
            case "firstname":
                System.out.println("Firstname:");
                from = from.withfirstname(s.nextLine());
                break;
            case "lastname":
                System.out.println("Lastname:");
                from = from.withlastname(s.nextLine());
                break;
            case "birthdate":
                System.out.println("birthdate:");
                from = from.withbirthdate(LocalDate.parse(s.nextLine()));
                break;
            case "gender":
                System.out.println("Male(true|false):");
                from = from.withgender(Boolean.parseBoolean(s.nextLine()));
                break;
            case "salary":
                System.out.println("Salary:");
                from = from.withsalary(Double.parseDouble(s.nextLine()));
                break;
            case "address":
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
                from = from.withaddress(address);
                break;
            case "phonenumber":
                System.out.println("Phone Number:");
                from = from.withphoneNumber(s.nextLine());
                break;
            case "email":
                System.out.println("Email:");
                from = from.withemail(s.nextLine());
                break;
            case "jobtitle":
                System.out.println("Job Title:");
                from = from.withjobTitle(s.nextLine());
                break;
            case "department":
                System.out.println("Department:");
                from = from.withdepartment(s.nextLine());
                break;
            case "workstart":
                System.out.println("Work Start (hh:mm):");
                from = from.withworkStart(LocalTime.parse(s.nextLine()));
                break;
            case "workend":
                System.out.println("Work End (hh:mm):");
                from = from.withworkEnd(LocalTime.parse(s.nextLine()));
                break;
            case "notes":
                System.out.println("Notes:");
                from = from.withnotes(s.nextLine());
                break;
        }

        return from;
    }
}

