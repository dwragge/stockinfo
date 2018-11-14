package dwragge;
import io.micronaut.runtime.Micronaut;
/**
 * Hello world!
 *
 */
public class App {
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Micronaut.run(App.class);
    }
}
