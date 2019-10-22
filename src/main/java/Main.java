import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String... args) {
        Stream<Integer> strm = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        GSpliterator<Integer> GSpliterator = new GSpliterator<>(strm.spliterator(), 3);
        Stream<Stream<Integer>> GStream = StreamSupport.stream(GSpliterator, false);

        GStream.forEach(
                stream -> {
                    String s = stream.map(Object::toString).collect(Collectors.joining(", "));
                    System.out.println(s);
                }
        );
    }
}
