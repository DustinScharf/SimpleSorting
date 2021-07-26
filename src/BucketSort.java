import java.awt.*;

/**
 * Klasse beinhaltet zwei verschiedene Bucketsort-Implementierungen.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
public class BucketSort extends Ablauf {

    int N;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung fuer die Zeichnung
     * @param wait - Verzoegerungszeit beim Zeichnen
     */
    BucketSort(Darstellung ds, long wait) {
        this.ds = ds;
        this.wait = wait;
    }

    protected void zeichne(Integer[] zeichenBucketArray) {
        ds.zeichintarr = (Integer[]) a;
        ds.bucketintarr = (Integer[]) zeichenBucketArray;
        ds.repaint();
    }

    /**
     * der "pure" Alghorithmus, aufgerufen um seine Laufzeit zu messen
     *
     * @param sc - Schnittstelle SortCondition zum Vergleich von Elementen
     * @param a  - zu sortierendes Feld
     * @param N  - Anzahl der Elemente
     */
    public void totalBucketSort(SortCondition sc, Object a[], int N) {
        if (N <= 0) return;// Case of empty array
        Integer min = (Integer) a[0];
        Integer max = min;
        for (int i = 1; i < N; i++)// Find the minimum and maximum
            if (sc.isGreaterThan(max, a[i]))
                max = (Integer) a[i];
            else if (sc.isGreaterThan(a[i], min))
                min = (Integer) a[i];

        int bucket[] = new int[max.intValue() - min.intValue() + 1];// Create buckets

        for (int i = 0; i < N; i++)// "Fill" buckets
            bucket[((Integer) a[i]).intValue() - min.intValue()]++;// by counting each datum

        int i = 0;
        for (int b = 0; b < bucket.length; b++)// "Empty" buckets
            for (int j = 0; j < bucket[b]; j++)// back into array
                a[i++] = new Integer(b + min.intValue());// by creating one per count
    }

    /**
     * Alghorithmus als run-Methode, um ihn steuerbar zu machen und um
     * zu zeichnen
     */
    public void run() {
        if (N <= 0) return;// Case of empty array

        Integer sortiertesBucketArray[] = new Integer[N];
        Integer zeichenBucketArray[] = new Integer[N];

        for (int i = 0; i < N; i++) {//Arrays mit dummy-El. fuellen
            sortiertesBucketArray[i] = new Integer(-1);
            zeichenBucketArray[i] = new Integer(-1);
        }

        Integer min = (Integer) a[0];
        Integer max = min;
        for (int i = 1; i < N; i++) // Find the minimum and maximum
            if (sc.isGreaterThan(max, a[i]))
                max = (Integer) a[i];
            else if (sc.isGreaterThan(a[i], min))
                min = (Integer) a[i];

        int bucket[] = new int[max.intValue() - min.intValue() + 1];// Create buckets

        for (int i = 0; i < N; i++)// "Fill" buckets
            bucket[((Integer) a[i]).intValue() - min.intValue()]++;// by counting each datum

        int i = 0;
        int f = 0;
        int g = 0;
        for (int b = 0; b < bucket.length; b++) {// "Empty" buckets
            for (int j = 0; j < bucket[b]; j++) {// back into array
                sortiertesBucketArray[i++] = new Integer(b + min.intValue());// by creating one per count
            }
        }

        for (f = 0; f < N; f++) {
            for (g = 0; g < N; g++) {
                try {
                    if (sortiertesBucketArray[g].equals(a[f]) == true) {
                        zeichenBucketArray[g] = (Integer) a[f];
                        a[f] = new Integer(-1);
                        sortiertesBucketArray[g] = new Integer(-1);
                        zeichne(zeichenBucketArray);
                        try {
                            this.sleep(wait);
                        } catch (InterruptedException e) {
                            System.out.println(e.toString());
                            setzeZurueck();
                        }
                        break;
                    }
                } catch (NullPointerException e) {
                }
            }
            if (stopp == true) break;
        }
        setzeZurueck();
    }

}

