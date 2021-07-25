//import SortCondition;
import java.lang.*;
/**
 * Klasse beinhaltet zwei verschiedene Shellsort-Implementierungen.
 *
 * @version 1.0 04.02.01
 * @author	Jan Rudert
 */
public class ShellSort extends Ablauf{

 int n;
 private long wait;
 /**
  * @param ds - Referenz zu Darstellung fuer die Zeichnung
  * @param wait - Verzoegerungszeit beim Zeichnen
  */
 ShellSort(Darstellung ds,long wait){
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
  public void totalShellSort(SortCondition sc, Object a[],int n){
    int i,j,delta;
    Object elem;
    delta=1;
    do {
      delta=delta*3+1;
    }while (n >=delta);
    do {
      delta =delta /3;
      for(i=delta;i<n;i++){
	    elem=a[i];
        j=i;
        while(sc.isGreaterThan(elem,a[j-delta])){
          a[j]=a[j-delta];
          j=j-delta;
          if(j<delta) break;
        }
        a[j]=elem;
      }
    } while (delta >1);
  }

  private static int prepare(int n){
    int delta;
    delta=1;
    do {
      delta=delta*3+1;
    }while (n >=delta);
    return delta;
  }
/**
 * Alghorithmus als run-Methode, um ihn steuerbar zu machen und um
 * zu zeichnen
 */
  public void run(){
    int i,j,delta;
    Object elem;
    delta=1;
    do {
      delta=delta*3+1;
    }while (n >=delta);
    do {
      if (stopp == true)break;
      delta =delta /3;
      for(i=delta;i<n;i++){
        if (stopp == true)break;
        zeichne();
        try{
          this.sleep(wait);
        }catch(InterruptedException e){
           System.out.println(e.toString());
           setzeZurueck();
         }
	      elem=a[i];
        j=i;
        while(sc.isGreaterThan(elem,a[j-delta])){
          a[j]=a[j-delta];
          j=j-delta;
          if(j<delta) break;
        }
      a[j]=elem;
      }
    }while (delta >1);
    setzeZurueck();
    zeichne();
 }
}