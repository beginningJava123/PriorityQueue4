import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * {@link PriorityQueue} implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class HeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link PriorityQueue} storing {@link PriorityNode} objects representing each element-priority pair.
     */
    private final PriorityQueue<PriorityNode<E>> pq;

    /**
     * Constructs an empty instance.
     */
    public HeapMinPQ() {
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::getPriority));
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public HeapMinPQ(Map<E, Double> elementsAndPriorities) {
        //Your code here
        pq = new PriorityQueue<>(Comparator.comparingDouble(PriorityNode::getPriority));

        for (E key : elementsAndPriorities.keySet()) {
            add(key, elementsAndPriorities.get(key));

        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        //Your code here
        pq.add(new PriorityNode<>(element, priority));
    }

    @Override
    public boolean contains(E element) {
        return pq.contains(new PriorityNode<>(element, 0));
    }

    @Override
    public double getPriority(E element) {
        //Your code here
        if (element == pq.peek()) {
            return pq.peek().priority;
        }
        return -1.0;
    }


    @Override
    public E peekMin() {
        //Your code here
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return this.pq.peek().element;
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        //Your code here
//        PriorityNode<E> minNode = pq.poll();
//        if (minNode != null) {
//            return minNode.getElement();
//        } else {
//            throw new NoSuchElementException("PQ is empty");
//        }
        return this.pq.poll().getElement();
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        //Your code here
        this.pq.remove(new PriorityNode<>(element, 0.0)); //linear time
        this.pq.add(new PriorityNode<>(element, priority));
    }

    @Override
    public int size() {
        //Your code here
        return pq.size();
    }
}