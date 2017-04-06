import java.time.*;
import java.util.UUID;

/**
 * Created by marcin.majka on 21/2/2017.
 */
public class Test {
    private static final String MINUS = "-";

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace(MINUS, ""));
        System.out.println(UUID.randomUUID().toString().replace(MINUS, ""));
        System.out.println(UUID.randomUUID().toString().replace(MINUS, ""));
    }

}
