// import SortCondition;

import java.lang.*;

/**
 * Klasse beinhaltet zwei verschiedene Mergesort-Implementierungen.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
public class MergeSort extends Ablauf {

    int lo;
    int hi;
    private long wait;
    private long wait2;

    /**
     * @param ds   - Referenz zu Darstellung für die Zeichnung
     * @param wait - Verzögerungszeit beim Zeichnen
     */
    MergeSort(Darstellung ds, long wait) {
        this.ds = ds;
        this.wait = wait;
        this.wait2 = wait * 10;
    }

    /**
     * der "pure" Algorithmus, aufgerufen um seine Laufzeit zu messen
     *
     * @param sc - Schnittstelle SortCondition zum Vergleich von Elementen
     * @param a  - zu sortierendes Feld
     * @param lo - erstes Element im Feld
     * @param hi - letztes Element im feld
     */
    public void totalMergeSort(SortCondition sc, Object a[], int lo, int hi) {
        if (lo == hi) return;
        int length = hi - lo + 1;
        int pivot = (lo + hi) / 2;
        totalMergeSort(sc, a, lo, pivot);
        totalMergeSort(sc, a, pivot + 1, hi);
        Object working[] = new Object[length];
        for (int i = 0; i < length; i++) working[i] = a[lo + i];
        int m1 = 0;
        int m2 = pivot - lo + 1;
        for (int i = 0; i < length; i++) {
            if (m2 <= hi - lo)
                if (m1 <= pivot - lo)
                    if (sc.isLessThan(working[m2], working[m1])) a[i + lo] = working[m2++];
                    else a[i + lo] = working[m1++];
                else a[i + lo] = working[m2++];
            else a[i + lo] = working[m1++];
        }
    }

    /**
     * Algorithmus als run-Methode, um ihn steuerbar zu machen und um
     * zu zeichnen
     */
    public void run() {
        zeichneMergeSort(sc, a, lo, hi);
        setzeZurueck();
        zeichne();
    }

    private void zeichneMergeSort(SortCondition sc, Object a[], int lo, int hi) {
        if (lo == hi) return;
        int length = hi - lo + 1;
        int pivot = (lo + hi) / 2;
        zeichne();
        try {
            sleep(Sort.globalWait);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            setzeZurueck();
        }
        if (stopp != true) {
            zeichneMergeSort(sc, a, lo, pivot);
            zeichneMergeSort(sc, a, pivot + 1, hi);
            Object working[] = new Object[length];
            for (int i = 0; i < length; i++) working[i] = a[lo + i];
            int m1 = 0;
            int m2 = pivot - lo + 1;
            for (int i = 0; i < length; i++) {
                if (m2 <= hi - lo)
                    if (m1 <= pivot - lo)
                        if (sc.isLessThan(working[m2], working[m1]))
                            a[i + lo] = working[m2++];
                        else a[i + lo] = working[m1++];
                    else a[i + lo] = working[m2++];
                else a[i + lo] = working[m1++];
            }

            if ((hi - lo) > 50) {
                zeichne();
                try {
                    sleep(Sort.globalWait);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                    setzeZurueck();
                }
            }//if
        }
    }
}
