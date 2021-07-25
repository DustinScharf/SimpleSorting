/**
 * Schnittstelle zum Vergleich zweier Objekte aus dem zu sortierendem Feld
 *
 * @version 1.0 04.02.01
 * @author	Jan Rudert
 */
public interface SortCondition{
   public abstract boolean isGreaterThan(Object obj1, Object obj2);
}
