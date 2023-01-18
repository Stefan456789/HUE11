import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;

public class FileList extends ArrayList<Person> implements AutoCloseable{
    final BufferedWriter w;

    public FileList(File file) {
        super();
        try {
            this.w = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Person person) {
        super.add(person);
        update();
        return false;
    }

    public void update(){
        var fields = this.stream().map(x -> x.toString()).collect(Collectors.joining("\n"));
        try {
            w.write(Base64.getEncoder().encodeToString(fields.getBytes()));
            w.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        w.close();
    }
}
