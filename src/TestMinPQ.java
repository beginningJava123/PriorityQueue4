import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class TestMinPQ {
    public static void main(String[] args) {
        MinPQ<String> minPQ = new UnsortedArrayMinPQ<>();
        MinPQ<String> optimized = new OptimizedHeapMinPQ<>();
        MinPQ<String> heap = new HeapMinPQ<>();

        if (!testEmptyPriority(minPQ)) return;
        if (!testEmptyPriority(optimized)) return;
        if (!testEmptyPriority(heap)) return;


        String[] test= {"test1", "test2", "test3", "test4", "test5"};
        int index = test.length;
        for (String str : test) {
            minPQ.add(str, index--);
            optimized.add(str, index--);
            heap.add(str, index--);
        }

        if (!testPeekMin(minPQ, "test5")) return;
        if (!testPeekMin(optimized, "test5")) return;
        if (!testPeekMin(heap, "test5")) return;

        if (!testRemove(minPQ, "test5")) return;
        if (!testRemove(optimized, "test5")) return;
        if (!testRemove(heap, "test5")) return;

        if (!testSize(minPQ, 4)) return;
        if (!testSize(optimized, 4)) return;
        if (!testSize(heap, 4)) return;

        System.out.println("Congrats.  You passed all tests and survive the 4th round of the Squid Game!");
    }

    static boolean testPeekMin(MinPQ<String> minPQ, String expected) {
        if (!minPQ.peekMin().equals(expected)) {
            System.out.println("FAIL " + minPQ.getClass().getCanonicalName() + " test peek min expected " + expected + " but got " + minPQ.peekMin());
            return false;
        }
        return true;
    }

    static boolean testSize(MinPQ<String> minPQ, int expected) {
        if (minPQ.size() != expected) {
            System.out.println("FAIL " + minPQ.getClass().getCanonicalName() + " test size() expected " + expected + " but got " + minPQ.size());
            return false;
        }
        return true;
    }

    static boolean testRemove(MinPQ<String> minPQ, String expected) {
        final String result = minPQ.removeMin();
        if (!result.equals(expected)) {
            System.out.println("FAIL test "  + minPQ.getClass().getCanonicalName() + " peek min expected " + expected + " but got " + result);
            return false;
        }
        return true;
    }

    static boolean testEmptyPriority(MinPQ<String> minPQ) {
        try {
            minPQ.peekMin();
        } catch (NoSuchElementException ex) {
            return true;
        }
        System.out.println("FAIL test " + minPQ.getClass().getCanonicalName() + " empty priority expected to throw NoSuchElementException exception");
        return false;
    }
}