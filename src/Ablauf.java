import java.lang.Thread.*;

/**
 * Stellt einfache interne Steuerfunktionen, die fuer jeden Algorithmus
 * gleichermassen gelten zur Verfuegung. Alle Sortierklassen implementieren
 * run()-Methoden, die das Zeichnen der Sorieralgorithmen steuern.
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
class Ablauf extends Thread {

    Darstellung ds;
    SortCondition sc;
    Object a[];
    boolean stopp = false;

    /**
     * uebergibt das zu zeichnende Array an die Fensterklasse und ruft dort paint() auf
     */
    protected void zeichne() {
        ds.zeichintarr = (Integer[]) a;
        ds.repaint();
    }

    /**
     * Nach Beendigung oder Abbruch des Sortierverfahrens aufgerufen, um eventuellen
     * Neustart zu ermoeglichen. (Zuruecksetzen verschiedener Variablen)
     */
    protected void setzeZurueck() {
        ds.fertig = true;
        ds.gestartet = false;
        ds.laeuft = false;
        stopp = false;
    }
}