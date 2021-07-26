import java.io.*;

/**
 * Die Klasse Sort steuert die Hauptfunktionalität des Sortierprogramms. Sie
 * beinhaltet alle verwendeten Algorithmen in gesonderten Klassen. Sie stellt
 * ein Auswahlmenü zur Verfügung und ruft nach Auswahl eines Sortieralgorithmus`
 * dessen grafische darstellung auf.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
class Sort implements SortCondition {

    Darstellung ds;

    BubbleSort bs;
    BucketSort bks;
    InsertionSort is;
    SelectionSort ses;
    ShellSort shs;
    HeapSort hs;
    QuickSort qs;
    MergeSort ms;
    CocktailSort cs;
    /**
     * Verzögerungszeit bei Visualisierung von Bubblesort
     */
    long bsWait = 100;
    /**
     * Verzögerungszeit bei Visualisierung von Bucket Sort
     */
    long bksWait = 50;
    /**
     * Verzögerungszeit bei Visualisierung von Insertion Sort
     */
    long isWait = 50;
    /**
     * Verzögerungszeit bei Visualisierung von Selection Sort
     */
    long sesWait = 50;
    /**
     * Verzögerungszeit bei Visualisierung von Shell Sort
     */
    long shsWait = 50;
    /**
     * Verzögerungszeit bei Visualisierung von Heapsort
     */
    long hsWait = 10;
    /**
     * Verzögerungszeit bei Visualisierung von Quicksort
     */
    long qsWait = 100;
    /**
     * Verzögerungszeit bei Visualisierung von Mergesort
     */
    long msWait = 100;
    /**
     * Verzögerungszeit bei Visualisierung von Cocktail Sort
     */
    long csWait = 100;
    /**
     * Originalarray des Sortierfeldes
     */
    Integer[] intarr;
    /**
     * Zeichen-Array des Sortierfeldes
     */
    Integer[] zeichintarr;
    /**
     * gemessene Sortierzeit
     */
    long zeit;

    public static long globalWait = 100;

    /**
     * Implementierung der Funktion aus der Schnittstelle SortCondition,
     * vergleicht zwei Objekte nach ihrem Integer-Wert.
     *
     * @param o1 - erstes zu vergleichendes Objekt
     * @param o2 - zweites Onjekt
     * @return true, falls o1 kleiner als o2
     */
    public boolean isGreaterThan(Object o1, Object o2) {
        Integer i1 = (Integer) o1;
        Integer i2 = (Integer) o2;
        return (i1.intValue() < i2.intValue());
    }

    /**
     * Erfasst einen String von der Eingabe
     */
    private static String erfasse() {
        DataInputStream input = new DataInputStream(System.in);
        String name = new String();
        try {
            name = input.readLine();
        } catch (IOException e) {
            return erfasse();
        }
        return name;
    }

    /**
     * Zeigt ein kleines Menu zur Auswahl des Sortieralgorithmus' an und
     * kontrolliert die eingabe.
     *
     * @return die Nummer des gewählten Algorithmus
     */
    private static byte chooseFunktion() {

        byte select;
        System.out.println("\nWie wollen Sie sortieren?");
        System.out.println("===========================");
        System.out.println("1 -> Bubblesort");
        System.out.println("2 -> Bucketsort");
        System.out.println("3 -> Heapsort");
        System.out.println("4 -> Insertionsort");
        System.out.println("5 -> Mergesort");
        System.out.println("6 -> Quicksort");
        System.out.println("7 -> Selectionsort");
        System.out.println("8 -> Shellsort");
        System.out.println("9 -> Cocktailsort");
        System.out.println("===========================");
        System.out.println("0 -> Beenden");
        System.out.println("===========================");
        String sel = erfasse();
        if ((sel.equals("0") == false)
                && (sel.equals("1") == false) && (sel.equals("2") == false) && (sel.equals("3") == false)
                && (sel.equals("4") == false) && (sel.equals("5") == false) && (sel.equals("6") == false)
                && (sel.equals("7") == false) && (sel.equals("8") == false) && (sel.equals("9") == false)) {
            System.out.println("Bitte eine der gegebenen Möglichkeiten auswählen!");
            return chooseFunktion();
        }
        Integer s = new Integer(sel);
        select = s.byteValue();

        return select;
    }

    /**
     * Erfragt die gewünschte Feldgröße beim Benutzer
     */
    private void chooseFeldGroesse() {
        intarr = null;
        zeichintarr = null;
        ds.zeichintarr = null;
        try {
            int laenge;
            System.out.println("\nWie viele Elemente soll das zu sortierende Feld besitzen?( <5000 )");
            String l = erfasse();
            Integer s = new Integer(l);
            laenge = s.intValue();
            if (laenge >= 5000) {
                System.out.println("der arme Rechner.....");
                chooseFeldGroesse();
            }
            intarr = new Integer[laenge];
            zeichintarr = new Integer[laenge];
            ds.zeichintarr = new Integer[laenge];
        } catch (NumberFormatException e) {
            System.out.println("bitte nur numerische Eingaben...");
            chooseFeldGroesse();
        }
    }

    /**
     * Misst die Zeit, die ein Algorithmus zur Ausführung OHNE Visualisierung
     * benötigt.
     *
     * @param auswahl - die Nummer des gewählten Algorithmus
     */
    private void messeZeit(byte auswahl) {
        switch (auswahl) {
            case 1:
                zeit = System.currentTimeMillis();
                bs.totalBubbleSort(this, (Object[]) intarr, (int) intarr.length);
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 2:
                zeit = System.currentTimeMillis();
                bks.totalBucketSort(this, (Object[]) intarr, (int) intarr.length);
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 3:
                zeit = System.currentTimeMillis();
                hs.totalHeapSort(this, (Object[]) intarr, (int) intarr.length);
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 4:
                zeit = System.currentTimeMillis();
                is.totalInsertionSort(this, (Object[]) intarr, (int) intarr.length);
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 5:
                zeit = System.currentTimeMillis();
                ms.totalMergeSort(this, (Object[]) intarr, 1, (int) (intarr.length - 1));
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 6:
                zeit = System.currentTimeMillis();
                qs.totalQuickSortRekursiv(this, (Object[]) intarr, 0, (int) intarr.length - 1);
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 7:
                zeit = System.currentTimeMillis();
                ses.totalSelectionSort(this, (Object[]) intarr, (int) intarr.length);
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 8:
                zeit = System.currentTimeMillis();
                shs.totalShellSort(this, (Object[]) intarr, (int) intarr.length);
                zeit = System.currentTimeMillis() - zeit;
                break;
            case 9:
                zeit = System.currentTimeMillis();
                cs.totalCocktailSort(this, (Object[]) intarr, (int) intarr.length);
                zeit = System.currentTimeMillis() - zeit;
                break;
        }
        ds.zeit = zeit;
    }

    /**
     * Erstellt ein Feld der gewünschten Größe mit Hilfe des java-internen
     * Zufallsgenerators.
     */
    private void erstelleFeld() {
        int i;

        for (i = 0; i < intarr.length; i++) {
            intarr[i] = new Integer((int) (intarr.length * Math.random()));
            zeichintarr[i] = intarr[i];
            ds.zeichintarr[i] = intarr[i];

        }
    }

    /**
     * Erstellt die Textzeile aus Name, Elementzahl und Zeitaufwand des benutzten
     * Algorithmus.
     */
    private String erstelleUeberschrift() {
        if (zeit != 0) return ("   -   Elemente: " + zeichintarr.length
                + "   -   Dauer der Sortierung:  " + zeit + " Millisekunden");
        else return ("   -   Elemente: " + zeichintarr.length
                + "   -   Dauer der Sortierung:  nicht im messbaren Bereich");
    }

    /**
     * Startet das Programm und startet je nach Benutzerauswahl eine bestimmte
     * Visualisierung.
     */
    public static void main(String arg[]) {

        System.out.println("=========================");
        System.out.println("Willkommen bei SimpleSort");
        System.out.println("=========================");
        System.out.println("1. Wählen Sie einen Sortieralgorithmus");
        System.out.println("2. Geben Sie eine beliebige Anzahl zu sortierender Elemente ein");
        System.out.println("3. Starten/stoppen Sie die Animation mit SPACE");
        System.out.println("4. Geschwindigkeit+ mit W");
        System.out.println("5. Geschwindigkeit- mit Q");

        byte auswahl;

        Sort s = new Sort();

        s.ds = new Darstellung();


        while (true) {
            auswahl = chooseFunktion();
            if (auswahl == 0) System.exit(0);
            s.chooseFeldGroesse();
            s.erstelleFeld();
            s.ds.aktiverAlg = auswahl;
            s.ds.setVisible(true);
            switch (auswahl) {
                case 1:
                    s.bs = new BubbleSort(s.ds, s.bsWait);
                    s.messeZeit(auswahl);
                    s.ds.bs = s.bs;
                    s.ds.setTitle("Bubblesort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.bs.sc = s;
                    s.bs.a = (Object[]) s.zeichintarr;
                    s.bs.n = (int) s.zeichintarr.length;
                    break;
                case 2:
                    s.bks = new BucketSort(s.ds, s.bksWait);
                    s.messeZeit(auswahl);
                    s.ds.bks = s.bks;
                    s.ds.setTitle("Bucketsort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.bks.sc = s;
                    s.bks.a = (Object[]) s.zeichintarr;
                    s.bks.N = (int) s.zeichintarr.length;
                    break;
                case 3:
                    s.hs = new HeapSort(s.ds, s.hsWait);
                    s.messeZeit(auswahl);
                    s.ds.hs = s.hs;
                    s.ds.setTitle("Heapsort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.hs.sc = s;
                    s.hs.a = (Object[]) s.zeichintarr;
                    s.hs.anz = (int) s.zeichintarr.length;
                    break;
                case 4:
                    s.is = new InsertionSort(s.ds, s.isWait);
                    s.messeZeit(auswahl);
                    s.ds.is = s.is;
                    s.ds.setTitle("Insertionsort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.is.sc = s;
                    s.is.a = (Object[]) s.zeichintarr;
                    s.is.n = (int) s.zeichintarr.length;
                    break;
                case 5:
                    s.ms = new MergeSort(s.ds, s.msWait);
                    s.messeZeit(auswahl);
                    s.ds.ms = s.ms;
                    s.ds.setTitle("Mergesort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.ms.sc = s;
                    s.ms.a = (Object[]) s.zeichintarr;
                    s.ms.lo = 1;
                    s.ms.hi = (int) (s.zeichintarr.length - 1);
                    break;
                case 6:
                    s.qs = new QuickSort(s.ds, s.qsWait);
                    s.messeZeit(auswahl);
                    s.ds.qs = s.qs;
                    s.ds.setTitle("Quicksort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.qs.sc = s;
                    s.qs.a = (Object[]) s.zeichintarr;
                    s.qs.l = 0;
                    s.qs.r = (int) s.zeichintarr.length - 1;
                    break;
                case 7:
                    s.ses = new SelectionSort(s.ds, s.sesWait);
                    s.messeZeit(auswahl);
                    s.ds.ses = s.ses;
                    s.ds.setTitle("Selectionsort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.ses.sc = s;
                    s.ses.a = (Object[]) s.zeichintarr;
                    s.ses.n = (int) s.zeichintarr.length;
                    break;
                case 8:
                    s.shs = new ShellSort(s.ds, s.shsWait);
                    s.messeZeit(auswahl);
                    s.ds.shs = s.shs;
                    s.ds.setTitle("Shellsort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.shs.sc = s;
                    s.shs.a = (Object[]) s.zeichintarr;
                    s.shs.n = (int) s.zeichintarr.length;
                    break;
                case 9:
                    s.cs = new CocktailSort(s.ds, s.csWait);
                    s.messeZeit(auswahl);
                    s.ds.cs = s.cs;
                    s.ds.setTitle("Cocktailsort" + s.erstelleUeberschrift());
                    s.ds.repaint();
                    s.cs.sc = s;
                    s.cs.a = (Object[]) s.zeichintarr;
                    s.cs.n = (int) s.zeichintarr.length;
                    break;
            }
        }
    }
}