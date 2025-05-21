import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Unsorted array (or {@link ArrayList}) implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class UnsortedArrayMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the element-priority pairs in no specific order.
     */
    private final List<PriorityNode<E>> elements;

    /**
     * Constructs an empty instance.
     */
    public UnsortedArrayMinPQ() {
        elements = new ArrayList<>();
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public UnsortedArrayMinPQ(Map<E, Double> elementsAndPriorities) {
        //Your code here
        elements = new ArrayList<>();

        for (E key : elementsAndPriorities.keySet()) {
            add(key, elementsAndPriorities.get(key));
        }
    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        elements.add(new PriorityNode<>(element, priority));
    }

    @Override
    public boolean contains(E element) {
        return elements.contains(new PriorityNode<>(element, 0));
    }

    @Override
    public double getPriority(E element) {
        //Your code here
        for (PriorityNode elem : elements) {
            if (elem.getElement().equals(element)) {
                return elem.getPriority();
            }
        }
        return -1;
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        //Your code here
        double lowPriority = Double.MAX_VALUE;
        E lowestItem = null;
        for (PriorityNode<E> i : elements) {
            if (i.getPriority() <= lowPriority) {
                lowPriority = i.getPriority();
                lowestItem = i.getElement();
            }
        }
        return lowestItem;
    }

    @Override
    public E removeMin() {
        if (!contains(elements.getFirst().element)) {
            throw new NoSuchElementException("PQ does not contain " + elements);
        }
        //Your code here
        PriorityNode<E> storage = null;
        for (PriorityNode<E> node : elements) {
            if (storage == null) {
                storage = node;
                continue;
            }
            if (storage.getPriority() < node.getPriority()) {
                storage = node;
            }
        }
        elements.remove(storage);
        return storage.getElement();
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
//       Your code here
        for (PriorityNode<E> node : elements) {
            if (node.getElement() == element) {
                node.priority = priority;
            }
        }
    }

    @Override
    public int size() {
//      Your code here
        return elements.size();
    }
}