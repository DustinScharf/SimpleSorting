//import SortCondition;
import java.lang.*;
/**
 * Klasse beinhaltet zwei verschiedene Selectionsort-Implementierungen.
 *
 * @version 1.0 04.02.01
 * @author	Jan Rudert
 */

public class SelectionSort extends Ablauf {
 
 int n;
 private long wait;
 /**
  * @param ds - Referenz zu Darstellung fuer die Zeichnung
  * @param wait - Verzoegerungszeit beim Zeichnen
  */
 SelectionSort (Darstellung ds,long wait) {
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
 public void totalSelectionSort (SortCondition sc, Object a[],int n) {
    int i,j,min;
    Object elem;

    for (i=0;i<n-1;i++) {        // search local minimum for a[i]

      min=i;		
      for(j=i+1;j<a.length;j++) {
        if (sc.isGreaterThan(a[j],a[min])) {
          min=j;
	} // if
      } // for

      elem=a[min];		// switch a[i] with a[min]
      a[min]=a[i];
      a[i]=elem;

    } //for

 } // totalSelectionSort


/**
 * Alghorithmus als run-Methode, um ihn steuerbar zu machen und um
 * zu zeichnen
 */ 
 public void run() {
    int i,j,min;
    Object elem;
    for (i=0;i<n-1;i++){
      if (stopp == true) break;
      zeichne();
      try {
        this.sleep(wait);
      } catch(InterruptedException e) {
           System.out.println(e.toString());
           setzeZurueck();
      } // catch
      min=i;
      for(j=i+1;j<a.length;j++){
        if(sc.isGreaterThan(a[j],a[min])) min=j;
      } // for
      elem=a[min];
      a[min]=a[i];
      a[i]=elem;
    } // for
    setzeZurueck();
    zeichne();
  } // run()

} // 