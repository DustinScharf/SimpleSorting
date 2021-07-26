import java.lang.Thread.*;

/**
 * Stellt einfache interne Steuerfunktionen, die für jeden Algorithmus
 * gleichermaßen gelten zur Verfügung. Alle Sortierklassen implementieren
 * run()-Methoden, die das Zeichnen der Sortieralgorithmen steuern.
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
     * übergibt das zu zeichnende Array an die Fensterklasse und ruft dort paint() auf
     */
    protected void zeichne() {
        ds.zeichintarr = (Integer[]) a;
        ds.repaint();
    }

    /**
     * Nach Beendigung oder Abbruch des Sortierverfahrens aufgerufen, um eventuellen
     * Neustart zu ermöglichen. (Zurücksetzen verschiedener Variablen)
     */
    protected void setzeZurueck() {
        ds.fertig = true;
        ds.gestartet = false;
        ds.laeuft = false;
        stopp = false;
    }
}