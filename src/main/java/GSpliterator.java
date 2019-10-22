import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class GSpliterator<T> implements Spliterator<Stream<T>> {

    private final Spliterator<T> spliterator;
    private final int groupBy;

    public GSpliterator(Spliterator<T> spliterator, int groupBy) {
        this.spliterator = spliterator ;
        this.groupBy = groupBy;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Stream<T>> action) {
        boolean isFinished = false;
        Stream.Builder<T> builder = Stream.builder() ;
        for (int i = 0 ; i < groupBy ; i++) {
            if (!spliterator.tryAdvance(item -> {
                builder.add(item) ;
            }))
            {
                isFinished = true ;
            }
        }
        Stream<T> subStream = builder.build() ;
        action.accept(subStream) ;
        return !isFinished ;
    }

    @Override
    public Spliterator<Stream<T>> trySplit() {
        Spliterator<T> spliterator = this.spliterator.trySplit() ;
        return new GSpliterator<T>(spliterator, groupBy) ;
    }

    @Override
    public long estimateSize() {
        return spliterator.estimateSize() / groupBy ;
    }

    @Override
    public int characteristics() {
        return IMMUTABLE | SIZED | SUBSIZED;
    }
}