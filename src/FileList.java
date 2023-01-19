import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileList extends ArrayList<Person> {

    final File f;

    public FileList(File file) {
        super();
        this.f = file;
        try {
            var r = Files.newBufferedReader(f.toPath());
            String lines = new String(Base64.getDecoder().decode(r.readLine()));
            this.addAll(Arrays.stream(lines.split("\n")).map(line -> {
                var person = Arrays.stream(line.split(", ")).map(field -> field.split(": ")[field.split(": ").length-1]).toArray(String[]::new);
                return new Person(Integer.parseInt(person[0]), person[1], person[2], LocalDate.parse(person[3]), Boolean.parseBoolean(person[4]), Double.parseDouble(person[5]), new Address(person[6], Integer.parseInt(person[7]), Integer.parseInt(person[8]), person[9], person[10]), person[11], person[12], person[13], person[14], LocalTime.parse(person[15]), LocalTime.parse(person[16]), person[17]);
            }).toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Person person) {
        boolean returnVal =super.add(person);
        update();
        return returnVal;
    }

    @Override
    public boolean remove(Object person) {
        boolean returnVal = super.remove(person);
        update();
        return returnVal;
    }

    public void update(){
        var fields = this.stream().map(x -> x.toString()).collect(Collectors.joining("\n"));
        try (BufferedWriter w = Files.newBufferedWriter(f.toPath())) {
            w.write(Base64.getEncoder().encodeToString(fields.getBytes()));
            w.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
