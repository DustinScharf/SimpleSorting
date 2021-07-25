import java.awt.*;
/**
 * Entflimmerung der Visualisierung durch Doppelpufferung
 *
 * @version 1.0 04.02.01
 * @author	Jan Rudert
 */
class PufferDarstellung extends Frame {

  Graphics dbGraphics;
  Image dbImage;

  public void update(Graphics g){
      //Double-Buffer initialisieren
       if (dbImage == null) {
          dbImage = createImage(this.getSize().width,this.getSize().height);
          dbGraphics = dbImage.getGraphics();
       }
       //Hintergrund loeschen
       dbGraphics.setColor(getBackground());
       dbGraphics.fillRect(0,0,this.getSize().width,this.getSize().height);

       //Vordergrund zeichnen
       dbGraphics.setColor(getForeground());
       paint(dbGraphics);
       //Offscreen anzeigen
       g.drawImage(dbImage,0,0,this);
    }                                
}
