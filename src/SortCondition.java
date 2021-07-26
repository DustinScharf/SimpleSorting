/**
 * Schnittstelle zum Vergleich zweier Objekte aus dem zu sortierendem Feld
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
public interface SortCondition {
    public abstract boolean isGreaterThan(Object obj1, Object obj2);
}
