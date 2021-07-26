//import SortCondition;

import java.lang.*;

/**
 * Klasse beinhaltet zwei verschiedene Insertionsort-Implementierungen.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
public class InsertionSort extends Ablauf {

    int n;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung fuer die Zeichnung
     * @param wait - Verzoegerungszeit beim Zeichnen
     */
    InsertionSort(Darstellung ds, long wait) {
        this.ds = ds;
        this.wait = wait;
    }


    /**
     * der "pure" Alghorithmus, aufgerufen um seine Laufzeit zu messen
     *
     * @param sc - Schnittstelle SortCondition zum Vergleich von Elementen
     * @param a  - zu sortierendes Feld
     * @param n  - Anzahl der Elemente
     */
    public void totalInsertionSort(SortCondition sc, Object a[], int n) {
        int i, j;
        Object elem;
        for (i = 1; i < n; i++) {
            elem = a[i];
            j = i;
            while (sc.isGreaterThan(elem, a[j - 1])) {
                a[j] = a[j - 1];
                j = j - 1;
                if (j == 0) break;
            }
            a[j] = elem;
        }
    }


    /**
     * Alghorithmus als run-Methode, um ihn steuerbar zu machen und um
     * zu zeichnen
     */
    public void run() {
        int i, j;
        Object elem;
        for (i = 1; i < n; i++) {
            if (stopp == true) break;
            zeichne();
            try {
                this.sleep(wait);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                setzeZurueck();
            }

            elem = a[i];
            j = i;
            while (sc.isGreaterThan(elem, a[j - 1])) {
                a[j] = a[j - 1];
                j = j - 1;
                if (j == 0) break;
            }
            a[j] = elem;
        }
        setzeZurueck();
        zeichne();
    }

}    
