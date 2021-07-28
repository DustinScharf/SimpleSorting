import java.lang.*;

/**
 * Klasse beinhaltet zwei verschiedene Bubblesort-Implementierungen.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
class BubbleSort extends Ablauf {

    int n;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung für die Zeichnung
     * @param wait - Verzögerungszeit beim Zeichnen
     */
    BubbleSort(Darstellung ds, long wait) {
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
    public void totalBubbleSort(SortCondition sc, Object a[], int n) {
        int i, j;
        Object temp;

        for (i = n - 1; i >= 0; i--) {// AND unstable
            for (j = 0; j < i; j++) {        // unstable = false

                if (sc.isLessThan(a[j + 1], a[j])) {
                    temp = a[j + 1];            // unstable = true
                    a[j + 1] = a[j];
                    a[j] = temp;
                } // if

            } // for
        } // for

    } // totalBubbleSort


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
        Object temp;
        for (i = n - 1; i >= 0; i--) {
            if (stopp) break;
            zeichne();
            try {
                sleep(Sort.globalWait);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                setzeZurueck();
            }
            for (j = 0; j < i; j++) {
                if (sc.isLessThan(a[j + 1], a[j])) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }

        ds.fertig = true;
        setzeZurueck();
        zeichne();
    }
}