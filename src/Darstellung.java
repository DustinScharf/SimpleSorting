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
    CocktailSort cs;
    RadixSort rs;

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
                cs.start();
                break;
            case 4:
                hs.start();
                break;
            case 5:
                is.start();
                break;
            case 6:
                ms.start();
                break;
            case 7:
                qs.start();
                break;
            case 8:
                rs.start();
                break;
            case 9:
                ses.start();
                break;
            case 10:
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
                cs.suspend();
                break;
            case 4:
                hs.suspend();
                break;
            case 5:
                is.suspend();
                break;
            case 66:
                ms.suspend();
                break;
            case 7:
                qs.suspend();
                break;
            case 8:
                rs.suspend();
                break;
            case 9:
                ses.suspend();
                break;
            case 10:
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
                cs.resume();
                break;
            case 4:
                hs.resume();
                break;
            case 5:
                is.resume();
                break;
            case 6:
                ms.resume();
                break;
            case 7:
                qs.resume();
                break;
            case 8:
                rs.resume();
                break;
            case 9:
                ses.resume();
                break;
            case 10:
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
                cs.resume();
                cs.stopp = true;
                break;
            case 4:
                hs.resume();
                hs.stopp = true;
                break;
            case 5:
                is.resume();
                is.stopp = true;
                break;
            case 6:
                ms.resume();
                ms.stopp = true;
                break;
            case 7:
                qs.resume();
                qs.stopp = true;
                break;
            case 8:
                rs.resume();
                rs.stopp = true;
                break;
            case 9:
                ses.resume();
                ses.stopp = true;
                break;
            case 10:
                shs.resume();
                shs.stopp = true;
                break;
        }
    }

    /**
     * ruft je nach Status der Visualisierung Steuerfunktionen auf
     */
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            Sort.globalWait = 2 * (Sort.globalWait + 1);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            Sort.globalWait = (long) Math.max(1, Sort.globalWait / 1.2);
        } else {
            startStop();
        }
    }

    protected void startStop() {
        if (laeuft) {
            halteSortierungAn();
            laeuft = false;
            //System.out.println("Sortierung angehalten...");
        } else {
            if (!gestartet) {
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
        double skalenColor = 255 / (double) zeichintarr.length;
        if (Sort.darkMode) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }
        for (j = 0; j < zeichintarr.length; j++) {
            if (zeichintarr[j] != -1) {
                if (Sort.colorMode) {
                    int b = (int) ((skalenColor * zeichintarr.length) - zeichintarr[j] * skalenColor);
                    int r = 255 - b;
                    g.setColor(new Color(r, 0, b));
                }
                g.drawLine((int) (j * skalenEinheitX),
                        (int) ((skalenEinheitY * zeichintarr.length) - zeichintarr[j] * skalenEinheitY),
                        (int) (j * skalenEinheitX),
                        (int) ((skalenEinheitY * zeichintarr.length) - zeichintarr[j] * skalenEinheitY + 2));
            }
        }
        if ((aktiverAlg == 2) && (laeuft)) {
            if (Sort.colorMode) {
                g.setColor(Color.orange);
            }
            for (j = 0; j < zeichintarr.length; j++) {
                if (bucketintarr[j] != -1) {
                    g.drawLine((int) (j * skalenEinheitX),
                            (int) ((skalenEinheitY * zeichintarr.length) - bucketintarr[j] * skalenEinheitY),
                            (int) (j * skalenEinheitX),
                            (int) ((skalenEinheitY * zeichintarr.length) - bucketintarr[j] * skalenEinheitY + 2));
                }
            }
        }
        if (fertig) {
            if (Sort.colorMode) {
                g.setColor(new Color(200,0,180));
            } else {
                if (Sort.darkMode) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.black);
                }
            }
            g.setFont(new Font("Helvetica", Font.PLAIN, 18));
            g.drawString("Done",12,48);
            g.drawRect(0,0,60,55);
        }
    }


}