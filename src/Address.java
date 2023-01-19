import java.util.Arrays;
import java.util.stream.Collectors;

public record Address(String street,
                      int houseNumber,
                      int zipCode,
                      String city,
                      String country) {
    @Override
    public String toString() {
        return Arrays.stream(this.getClass().getDeclaredFields()).map(af -> {
            try {
                return af.getName() + ": " + af.get(this).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining(", "));
    }
}
