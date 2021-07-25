/**
 * Klasse beinhaltet zwei verschiedene Bubblesort-Implementierungen.
 *
 * @version 1.0 04.02.01
 * @author	Jan Rudert
 */
import java.lang.*;
class BubbleSort extends Ablauf{

 int n;
 private long wait;
 /**
  * @param ds - Referenz zu Darstellung fuer die Zeichnung
  * @param wait - Verzoegerungszeit beim Zeichnen
  */
 BubbleSort(Darstellung ds,long wait){
  this.ds = ds;
  this.wait = wait;
 }


/**
 * der "pure" Alghorithmus, aufgerufen um seine Laufzeit zu messen
 *
 * @param sc - Schnittstelle SortCondition zum Vergleich von Elementen
 * @param a - zu sortierendes Feld
 * @param n - Anzahl der Elemente
 */
 public void totalBubbleSort(SortCondition sc, Object a[], int n)    {
      int i,j;
      Object temp;

      for (i = n - 1; i >= 0; i--)	{// AND unstable
        for (j = 0; j < i; j++) {		// unstable = false

	  if (sc.isGreaterThan(a[j+1],a[j])) {
	    temp = a[j+1];			// unstable = true
      	    a[j+1] = a[j];
	    a[j] = temp;
	  } // if

        } // for
      } // for

 } // totalBubbleSort


/**
 * Alghorithmus als run-Methode, um ihn steuerbar zu machen und um
 * zu zeichnen
 */
 public void run(){
      int i,j;
      Object temp;
       for (i = n - 1; i >= 0; i--){
        if (stopp == true)break;
        zeichne();
        try{
           this.sleep(wait);
        }catch(InterruptedException e){
           System.out.println(e.toString());
           setzeZurueck();
         }
        for (j = 0; j < i; j++){
	        if (sc.isGreaterThan(a[j+1],a[j])) {
	          temp = a[j+1];
      	    a[j+1] = a[j];
	          a[j] = temp;
          }
        }
	    }
      setzeZurueck();
      zeichne();
 }
}