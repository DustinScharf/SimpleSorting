import java.awt.*;
import java.awt.event.*;

/**
 * Fenster - Klasse, die für die Visualisierung der Sortieralgorithmen zuständig ist.
 * Außerdem nimmt sie Nutzerbefehle in Form von Tasteneingaben oder Mausklicks
 * entgegen, die die Visualisierung starten, unterbrechen oder beenden.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
class Darstellung extends PufferDarstellung implements KeyListener {

    /**
     * zu zeichnendes Array, vom Sortierverfahrens aktualisiert
     */
    Integer[] zeichintarr;
    /**
     * zweites Array bei Bucket Sort
     */
    Integer[] bucketintarr;
    int i, j;
    /**
     * Dauer des Sortierverfahrens
     */
    long zeit;
    /**
     * Statusvariable
     */
    boolean fertig;
    /**
     * Statusvariable
     */
    boolean laeuft = false;
    /**
     * Statusvariable
     */
    boolean gestartet = false;
    /**
     * Nummer des im Moment laufenden Sortierverfahrens
     */
    byte aktiverAlg;

    BubbleSort bs;
    BucketSort bks;
    InsertionSort is;
    SelectionSort ses;
    ShellSort shs;
    HeapSort hs;
    QuickSort qs;
    MergeSort ms;
    Sort s;

    Darstellung() {
        setLayout(new FlowLayout());
        resize(1024, 768);
        setResizable(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                beendeSortierung();
                setVisible(false);
            }
        });
        addKeyListener(this);
    }

    /**
     * startet nach Tastendruck die Visualisierung
     */
    private void starteSortierung() {
        switch (aktiverAlg) {
            case 1:
                bs.start();
                break;
            case 2:
                bks.start();
                break;
            case 3:
                hs.start();
                break;
            case 4:
                is.start();
                break;
            case 5:
                ms.start();
                break;
            case 6:
                qs.start();
                break;
            case 7:
                ses.start();
                break;
            case 8:
                shs.start();
                break;
        }
    }

    /**
     * unterbricht nach Tastendruck die Visualisierung
     */
    private void halteSortierungAn() {
        switch (aktiverAlg) {
            case 1:
                bs.suspend();
                break;
            case 2:
                bks.suspend();
                break;
            case 3:
                hs.suspend();
                break;
            case 4:
                is.suspend();
                break;
            case 5:
                ms.suspend();
                break;
            case 6:
                qs.suspend();
                break;
            case 7:
                ses.suspend();
                break;
            case 8:
                shs.suspend();
                break;
        }
    }

    /**
     * setzt nach Tastendruck die Visualisierung fort
     */
    private void setzeSortierungFort() {
        switch (aktiverAlg) {
            case 1:
                bs.resume();
                break;
            case 2:
                bks.resume();
                break;
            case 3:
                hs.resume();
                break;
            case 4:
                is.resume();
                break;
            case 5:
                ms.resume();
                break;
            case 6:
                qs.resume();
                break;
            case 7:
                ses.resume();
                break;
            case 8:
                shs.resume();
                break;
        }
    }

    /**
     * beendet nach Tastendruck die Visualisierung
     */
    public void beendeSortierung() {
        switch (aktiverAlg) {
            case 1:
                bs.resume();
                bs.stopp = true;
                break;
            case 2:
                bks.resume();
                bks.stopp = true;
                break;
            case 3:
                hs.resume();
                hs.stopp = true;
                break;
            case 4:
                is.resume();
                is.stopp = true;
                break;
            case 5:
                ms.resume();
                ms.stopp = true;
                break;
            case 6:
                qs.resume();
                qs.stopp = true;
                break;
            case 7:
                ses.resume();
                ses.stopp = true;
                break;
            case 8:
                shs.resume();
                shs.stopp = true;
                break;
        }
    }

    /**
     * ruft je nach Status der Visualisierung Steuerfunktionen auf
     */
    public void keyTyped(KeyEvent e) {

        if (laeuft == true) {
            halteSortierungAn();
            laeuft = false;
            //System.out.println("Sortierung angehalten...");
        } else {
            if (gestartet == false) {
                starteSortierung();
                gestartet = true;
                laeuft = true;
                //System.out.println("Sortierung wird gestartet...");
            } else {
                setzeSortierungFort();
                laeuft = true;
                //System.out.println("Sortierung laeuft...");
            }
        }

    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    /**
     * Zeichnet das gesamte Array als schwarze senkrechte Striche auf weissem Grund.
     * Das Array wird vor aufrufen dieser Funktion in dieser Klasse aktualisiert.
     * Berechnet je nach Anzahl der Elemente die Zeichendimensionen.
     * Sonderfall beim Zeichnen -> Bucket Sort. Dort müssen zwei Arrays gleichzeitig
     * dargestellt werden.
     */
    public void paint(Graphics g) {
        double skalenEinheitY = 768 / (double) zeichintarr.length;
        double skalenEinheitX = 1024 / (double) zeichintarr.length;
        g.setColor(Color.black);
        for (j = 0; j < zeichintarr.length; j++) {
            if (zeichintarr[j].intValue() != -1) {
                g.drawLine((int) (j * skalenEinheitX),
                        (int) ((skalenEinheitY * zeichintarr.length) - zeichintarr[j].intValue() * skalenEinheitY),
                        (int) (j * skalenEinheitX),
                        (int) ((skalenEinheitY * zeichintarr.length) - zeichintarr[j].intValue() * skalenEinheitY + 2));
            }
        }
        if ((aktiverAlg == 2) && (laeuft == true)) {
            g.setColor(Color.blue);
            for (j = 0; j < zeichintarr.length; j++) {
                if (bucketintarr[j].intValue() != -1) {
                    g.drawLine((int) (j * skalenEinheitX),
                            (int) ((skalenEinheitY * zeichintarr.length) - bucketintarr[j].intValue() * skalenEinheitY),
                            (int) (j * skalenEinheitX),
                            (int) ((skalenEinheitY * zeichintarr.length) - bucketintarr[j].intValue() * skalenEinheitY + 2));
                }
            }
        }
    }


}