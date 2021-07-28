import java.util.LinkedList;
import java.util.Queue;

/**
 * Klasse beinhaltet die Radix Sort Implementierungen.
 *
 * @author Dustin Scharf
 * @version 1.0 26.07.2021
 */
class RadixSort extends Ablauf {

    int n;
    private long wait;

    /**
     * @param ds   - Referenz zu Darstellung für die Zeichnung
     * @param wait - Verzögerungszeit beim Zeichnen
     */
    RadixSort(Darstellung ds, long wait) {
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
    public void totalRadixSort(SortCondition sc, Object a[], int n) {
        // find the digit amount of the number with the most digits
        int longestNumbersDigits = 0;
        for (Object o : a) {
            // we get the digit amount of a number by using log to the base ten of the number
            // (since we use number system based on ten)
            // but we add one to the number, since a number below ten still has one digit
            int currentNumberDigits = (int) (Math.log10((Integer) o) + 1);
            // then we possibly increment longestNumbersDigits
            // by taken the higher number of longestNumbersDigits so far
            // and the currentNumberDigits calculate in this loop step above
            longestNumbersDigits = Math.max(longestNumbersDigits, currentNumberDigits);
        }

        // now we create 10 buckets for each number 0-9 as queues
        Queue<Object>[] digitBuckets = new LinkedList[10];
        // and make space for them
        for (int digit = 0; digit < 10; ++digit) {
            digitBuckets[digit] = new LinkedList<>();
        }

        // now we do the following for each digit of all numbers in the array,
        // staring from the last digit of a number
        for (int digitPlace = 0; digitPlace < longestNumbersDigits; ++digitPlace) {

            // first we go through the whole array
            for (Object o : a) {
                int currentValue = (Integer) o;
                // ... and get the current watched digit n (starting from end of number) of a number i
                // by the formula FLOOR{ [ i mod 10^(n+1) ] / 10^n }
                int currentDigit = (int) ((currentValue % (Math.pow(10, digitPlace + 1))) / (Math.pow(10, digitPlace)));
                // finally we put the whole number in the bucket corresponding to the digit we calculated above
                digitBuckets[currentDigit].add(o);
            }

            // now we take all the bucket elements and put them back into the array
            int insertPosition = 0;
            // we start from the 0 bucket and walk to the 9 bucket
            for (int i = 0; i < 10; ++i) {
                // since we use a queue for the buckets,
                // the elements will be inserted into the array
                // in the same order as they were put into the bucket
                // (this is necessary for radix sort)
                while (!digitBuckets[i].isEmpty()) {
                    a[insertPosition++] = digitBuckets[i].remove();
                }
            }
        }
    } // totalRadixSort


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

        int longestNumbersDigits = 0;
        for (Object o : a) {
            int currentNumberDigits = (int) (Math.log10((Integer) o) + 1);
            longestNumbersDigits = Math.max(longestNumbersDigits, currentNumberDigits);
        }

        Queue<Object>[] digitBuckets = new LinkedList[10];
        for (int digit = 0; digit < 10; ++digit) {
            digitBuckets[digit] = new LinkedList<>();
        }

        for (int digitPlace = 0; digitPlace < longestNumbersDigits; ++digitPlace) {


            for (Object o : a) {
                int currentValue = (Integer) o;
                int currentDigit = (int) ((currentValue % (Math.pow(10, digitPlace + 1))) / (Math.pow(10, digitPlace)));
                digitBuckets[currentDigit].add(o);
            }

            int insertPosition = 0;
            for (int i = 0; i < 10; ++i) {
                if (stopp) break;
                zeichne();
                try {
                    sleep(Sort.globalWait);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                    setzeZurueck();
                }
                while (!digitBuckets[i].isEmpty()) {
                    a[insertPosition++] = digitBuckets[i].remove();
                }
            }
        }

        ds.fertig = true;
        setzeZurueck();
        zeichne();
    }
}