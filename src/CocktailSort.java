/**
 * Klasse beinhaltet die Cocktail Sort Implementierungen.
 *
 * @author Dustin Scharf
 * @version 1.0 26.07.2021
 */
class CocktailSort extends Ablauf {

    int n;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung für die Zeichnung
     * @param wait - Verzögerungszeit beim Zeichnen
     */
    CocktailSort(Darstellung ds, long wait) {
        this.ds = ds;
        this.wait = wait;
    }


    /**
     * der "pure" Algorithmus, aufgerufen um seine Laufzeit zu messen
     *
     * @param sc - Schnittstelle SortCondition zum Vergleich von Elementen
     * @param a  - zu sortierendes Feld
     * @param n  - Anzahl der Elemente
     */
    public void totalCocktailSort(SortCondition sc, Object a[], int n) {
        int begin = -1;
        int end = n - 2;

        while (begin < end) {
            boolean swapped = false;
            ++begin;
            for (int i = begin; i <= end; ++i) {
                if (sc.isLessThan(a[i + 1], a[i])) {
                    Object temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            --end;
            for (int i = end; i >= begin; --i) {
                if (sc.isLessThan(a[i + 1], a[i])) {
                    Object temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

    } // totalCocktailSort


    /**
     * Algorithmus als run-Methode, um ihn steuerbar zu machen und um
     * zu zeichnen
     */
    public void run() {
        int begin = -1;
        int end = n - 2;

        while (begin < end) {
            if (stopp) break;
            zeichne();
            try {
                sleep(Sort.globalWait);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                setzeZurueck();
            }
            boolean swapped = false;
            ++begin;
            for (int i = begin; i <= end; ++i) {
                if (sc.isLessThan(a[i + 1], a[i])) {
                    Object temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            --end;
            for (int i = end; i >= begin; --i) {
                if (sc.isLessThan(a[i + 1], a[i])) {
                    Object temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        setzeZurueck();
        zeichne();
    }
}