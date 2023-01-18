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

    @Override
    public String toString() {
        return Arrays.stream(this.getClass().getDeclaredFields()).map(f -> {
            try {
                return f.get(this).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining(", "));
    }
}
