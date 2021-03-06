//import SortCondition;

import java.lang.*;

/**
 * Klasse beinhaltet zwei verschiedene Insertion Sort Implementierungen.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
public class InsertionSort extends Ablauf {

    int n;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung für die Zeichnung
     * @param wait - Verzögerungszeit beim Zeichnen
     */
    InsertionSort(Darstellung ds, long wait) {
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
    public void totalInsertionSort(SortCondition sc, Object a[], int n) {
        int i, j;
        Object elem;
        for (i = 1; i < n; i++) {
            elem = a[i];
            j = i;
            while (sc.isLessThan(elem, a[j - 1])) {
                a[j] = a[j - 1];
                j = j - 1;
                if (j == 0) break;
            }
            a[j] = elem;
        }
    }


    /**
     * Algorithmus als run-Methode, um ihn steuerbar zu machen und um
     * zu zeichnen
     */
    public void run() {
        ds.fertig = false;
        zeichne();

        if (Sort.init) {
            ds.laeuft = false;
            suspend();
        }
        Sort.init = true;

        int i, j;
        Object elem;
        for (i = 1; i < n; i++) {
            if (stopp == true) break;
            zeichne();
            try {
                sleep(Sort.globalWait);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                setzeZurueck();
            }

            elem = a[i];
            j = i;
            while (sc.isLessThan(elem, a[j - 1])) {
                a[j] = a[j - 1];
                j = j - 1;
                if (j == 0) break;
            }
            a[j] = elem;
        }

        ds.fertig = true;
        setzeZurueck();
        zeichne();
    }

}    
