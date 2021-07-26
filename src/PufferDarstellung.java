import java.awt.*;

/**
 * Antiflimmern der Visualisierung durch Doppelpufferung
 *
 * @author Jan Rudert
 * @version 1.0 04.02.01
 */
class PufferDarstellung extends Frame {

    Graphics dbGraphics;
    Image dbImage;

    public void update(Graphics g) {
        //Double-Buffer initialisieren
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbGraphics = dbImage.getGraphics();
        }
        //Hintergrund löschen
        if (Sort.darkMode) {
            dbGraphics.setColor(Color.black);
        } else {
            dbGraphics.setColor(getBackground());
        }
        dbGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);

        //Vordergrund zeichnen
        dbGraphics.setColor(Color.blue);
        paint(dbGraphics);
        //Offscreen anzeigen
        g.drawImage(dbImage, 0, 0, this);
    }
}
