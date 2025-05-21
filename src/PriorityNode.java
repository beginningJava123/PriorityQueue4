import java.util.Objects;

public class PriorityNode<E> {
    E element;
    double priority;

    public PriorityNode(E element, double priority) {
        this.element = element;
        this.priority = priority;
    }

    public double getPriority() {
        return priority;
    }

    public E getElement() {
        return element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriorityNode<?> that = (PriorityNode<?>) o;
        return Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }
}