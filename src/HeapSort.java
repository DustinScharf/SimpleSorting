//import java.awt.*;
//import SortCondition;
//import java.lang.*;

/**
 * Klasse beinhaltet zwei verschiedene Heapsort-Implementierungen.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
public class HeapSort extends Ablauf {

    int anz;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung für die Zeichnung
     * @param wait - Verzögerungszeit beim Zeichnen
     */
    HeapSort(Darstellung ds, long wait) {
        this.ds = ds;
        this.wait = wait;
    }


    /**
     * der "pure" Algorithmus, aufgerufen um seine Laufzeit zu messen
     *
     * @param sc  - Schnittstelle SortCondition zum Vergleich von Elementen
     * @param a   - zu sortierendes Feld
     * @param anz - Anzahl der Elemente
     */
    public void totalHeapSort(SortCondition sc, Object a[], int anz) {
        int k;
        int l;
        Object t;
        int m;
        int n;
        m = anz - 1;
        n = m;

        //Heap konstruieren
        for (k = m / 2; k >= 0; k--) {
            DownHeap(sc, k, n, a);
        }

        // Heap in der richtigen Reihenfolge abbauen
        do {
            t = a[0];
            a[0] = a[m];
            a[m] = t;
            m = m - 1;
            DownHeap(sc, 0, m, a);
        } while (m > 0);

    } // totalHeapSort


    /**
     * Algorithmus als run-Methode, um ihn steuerbar zu machen und um
     * zu zeichnen
     */
    public void run() {
        int k;
        int l;
        Object t;
        int m;
        int n;
        m = anz - 1;
        n = m;

        zeichne();
        try {
            this.sleep(wait);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
            setzeZurueck();
        }
        //Heap konstuieren
        for (k = m / 2; k >= 0; k--) {
            DownHeap(sc, k, n, a);
            if (stopp == true) break;
            zeichne();
            try {
                this.sleep(wait);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                setzeZurueck();
            }
        }
        // Heap in der richtigen Reihenfolge abbauen
        do {
            t = a[0];
            a[0] = a[m];
            a[m] = t;
            m = m - 1;
            DownHeap(sc, 0, m, a);
            if (stopp == true) break;
            zeichne();
            try {
                this.sleep(wait);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                setzeZurueck();
            }
        } while (m > 0);
        setzeZurueck();
        zeichne();
    }

    private static void DownHeap(SortCondition sc, int k, int n, Object a[]) {
        Object t;
        int i;
        int j;
        t = a[k];
        while (k <= n / 2) {
            j = k + k;
            if (j < n) {
                if (sc.isGreaterThan(a[j], a[j + 1])) j = j + 1;
            }
            if (!sc.isGreaterThan(t, a[j])) break;
            a[k] = a[j];
            k = j;
        }
        a[k] = t;
    } // DownHeap


}