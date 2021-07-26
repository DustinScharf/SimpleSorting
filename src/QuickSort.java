import java.lang.*;

/**
 * Klasse beinhaltet zwei verschiedene Quicksort-Implementierungen.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
public class QuickSort extends Ablauf {


    int l;
    int r;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung für die Zeichnung
     * @param wait - Verzögerungszeit beim Zeichnen
     */
    QuickSort(Darstellung ds, long wait) {
        this.ds = ds;
        this.wait = wait;
    }

    /**
     * der "pure" Algorithmus, aufgerufen um seine Laufzeit zu messen
     *
     * @param sc - Schnittstelle SortCondition zum Vergleich von Elementen
     * @param a  - zu sortierendes Feld
     * @param l  - erstes Element im Feld
     * @param r  - letztes Element im Feld
     */
    public void totalQuickSortRekursiv(SortCondition sc, Object a[], int l, int r) {
        int i;
        if (r > l) {
            i = TeileAuf(sc, a, l, r);
            totalQuickSortRekursiv(sc, a, l, i - 1);
            totalQuickSortRekursiv(sc, a, i + 1, r);
        }
    }

    /**
     * Algorithmus als run-Methode, um ihn steuerbar zu machen und um
     * zu zeichnen
     */

    public void run() {
        if (Sort.init) {
            ds.laeuft = false;
            suspend();
        }
        Sort.init = true;

        zeichneQuickSortRekursiv(sc, a, 0, a.length - 1);
        setzeZurueck();
        zeichne();
    }

    private void zeichneQuickSortRekursiv(SortCondition sc, Object a[], int l, int r) {
        int i;
        zeichne();
        try {
            sleep(Sort.globalWait);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            setzeZurueck();
        }
        if ((r > l) && (stopp != true)) {
            i = TeileAuf(sc, a, l, r);
            zeichneQuickSortRekursiv(sc, a, l, i - 1);
            zeichneQuickSortRekursiv(sc, a, i + 1, r);
        }
    }


    private static int TeileAuf(SortCondition sc, Object a[], int l, int r) {
        int i, j;
        Object u, v;
        v = a[r];
        i = l - 1;
        j = r;
        do {
            do {// von links suchen
                i = i + 1;
            } while (sc.isLessThan(a[i], v));
            do { //von rechts suchen
                j = j - 1;
                if (j == 0) break;
            } while (sc.isLessThan(v, a[j]));
            // linkes und rechtes Element tauschen
            u = a[i];
            a[i] = a[j];
            a[j] = u;
        } while (j > i);
        a[j] = a[i];
        a[i] = a[r];
        a[r] = u;
        return i;
    }
}