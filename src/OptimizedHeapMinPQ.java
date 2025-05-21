import java.util.*;

/**
 * Optimized binary heap implementation of the {@link MinPQ} interface.
 *
 * @param <E> the type of elements in this priority queue.
 * @see MinPQ
 */
public class OptimizedHeapMinPQ<E> implements MinPQ<E> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the heap of element-priority pairs.
     */
    private final List<PriorityNode<E>> elements;
    /**
     * {@link Map} of each element to its associated index in the {@code elements} heap.
     */
    private final Map<E, Integer> elementsToIndex;
    private int n;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        //Your code is here
        elements = new ArrayList<>();
        elementsToIndex = new HashMap<>();
    }

    /**
     * Constructs an instance containing all the given elements and their priority values.
     *
     * @param elementsAndPriorities each element and its corresponding priority.
     */
    public OptimizedHeapMinPQ(Map<E, Double> elementsAndPriorities) {
        //Your code is here
        elements = new ArrayList<>();
        elementsToIndex = new HashMap<>();
        //elements.add(new PriorityNode<>(null, -1));
        n = 0;

    }

    @Override
    public void add(E element, double priority) {
        if (contains(element)) {
            throw new IllegalArgumentException("Already contains " + element);
        }
        //Your code is here
        n++;
        PriorityNode<E> pn = new PriorityNode(element, priority);
        elements.add(pn); //add to back of list (bottom of tree)
        // If you do the swim before put, notice how you put the item back to the last index
        // So the swim operation basically has no effect
        elementsToIndex.put(element, n - 1); //size - 1 = index of last item in list
        swim(n - 1);
    }

    private void resize(int capacity) {
        //Your code is here

    }

    @Override
    public boolean contains(E element) {
//        //Your code is here
        return elementsToIndex.containsKey(element);
    }

    @Override
    public double getPriority(E element) {
        //Your code is here
        if (element == elementsToIndex.keySet()) {
            return elements.get(1).priority;
        }
        return -1.0;
    }

    @Override
    public E peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        //Your code is here
        return this.elements.get(1).getElement();
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        //Your code is here
        E minimumElement = peekMin();
        swap(0, size() - 1);
        elements.remove(size() - 1);
        elementsToIndex.remove(minimumElement);
        sink(0);

        return minimumElement;
    }

    @Override
    public void changePriority(E element, double priority) {
        if (!contains(element)) {
            throw new NoSuchElementException("PQ does not contain " + element);
        }
        //Your code is here
        int index = elements.indexOf(new PriorityNode<>(element, 0));
        elements.get(index).getPriority();
        swim(index);
        sink(index);
        index = elements.indexOf(new PriorityNode<>(element, 0));
        elementsToIndex.put(element, index);
    }

    @Override
    public int size() {
        //Your code is here
        return elements.size();
    }

    private void swim(int k) {
        while(k > 1 && greater(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if(!greater(k, j)) break;
            swap(j, k);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        double eleI = elements.get(i).getPriority();
        double eleJ = elements.get(j).getPriority();
        return eleI > eleJ;
    }

    private void swap(int i, int j) {
        elementsToIndex.remove(elements.get(i).getElement());
        elementsToIndex.remove(elements.get(j).getElement());

        PriorityNode<E> swap = elements.get(i);
        elements.set(i, new PriorityNode<>(elements.get(j).getElement(), elements.get(j).getPriority()));
        elements.set(j, new PriorityNode<>(swap.getElement(), swap.getPriority()));

        elementsToIndex.put(elements.get(i).getElement(), i);
        elementsToIndex.put(elements.get(j).getElement(), j);
    }
}