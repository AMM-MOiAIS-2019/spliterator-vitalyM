import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class GSpliteratorTest {

    @Test
    public void test_equals_stream() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        GSpliterator<Integer> GSpliterator = new GSpliterator<>(integerStream.spliterator(), 3);
        Stream<Stream<Integer>> GStream = StreamSupport.stream(GSpliterator, false);
        assertEquals(GStream.count(),3);
    }

    @Test
    public void test_equals_parallel_stream() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        GSpliterator<Integer> GSpliterator = new GSpliterator<>(integerStream.spliterator(), 3);
        Stream<Stream<Integer>> GStream = StreamSupport.stream(GSpliterator, false);
        assertEquals(GStream.parallel().count(),3);
    }
}