/**
 * EiP ÜB Lösung 
 * @author zin yosrim
 *
 */

public class Arrays {
	
	//test arrays
	public static int[] intArray = {1,2,100,4,8,65,7,18,3,10};
	public static int[] intArray2 = {1,2,100,4,8,65,7,18,3,10};	

	/**
	 * EiP Übungsblatt 8, Aufgabe 8-1 (a) 
	 * gibt das i-te Element eines Arrays zurück. Hierbei ist das erste Element array[0]  
	 * 
	 * @param array			Array von Integer Zahlen
	 * @param i				das i-te Element des Array wird abgefragt 
	 * @return 				der Wert des i-ten Element im Array
	 * @throws Exception	Wird in Main abgefangen, s.u. 
	 * 
	 */
	public static int arrayGet(int[] array, int i) throws Exception{
		try{
			return intArray[i-1];		
		}
		catch (ArrayIndexOutOfBoundsException e) {
		    throw new Exception("Fehler bei Aufruf in arrayGet. 2. Parameter i muss zwischen 1 und "+array.length+" liegen!");
		}	
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-1 (b) 
	 * gibt die Summe aller Zahlen in einem Integer Array zurück
	 * 
	 * @param 	array	Ein Array von Integer Zahlen
	 * @return	Summe der Zahlen im Integer Array
	 * 
	 */
	public static int sum (int[] array){
		int sum = 0;
		for (int i=0; i<array.length; i++){
			sum = sum + array[i];
		}
		return sum;
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-1 (c) 
	 * gibt das arithmetische Mittel aller Zahlen in einem Integer Array zurück
	 * 
	 * @param 	array	Ein Array von Integer Zahlen	
	 * @return	Der Mittelwert, aller Zahlen im Array, also die Summe aller Zahlen durch die Anzahl
	 * 
	 */
	public static int mean (int [] array){
			return (sum(array)/array.length); 
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-1 (d) 
	 * quadriert jedes Element eines int arrays
	 * 
	 * @param array
	 * 
	 */
	public static void square (int[] array){
		for (int i=0; i < array.length; i++){
			array[i] = array[i] * array[i];
		}
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-1 (e) 
	 * gibt das grösste Element eines int Array zurück
	 * 
	 * @param  	array	ein int Array
	 * @return	das grösste element des Array
	 * 
	 */
	public static int max(int[] array){
		int max = 0;
		for (int i=0; i < array.length; i++){
			if (array[i] > max) max = array[i];
		}
		return max;
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-2 (a) 
	 * tauscht die Plätze des i-ten und j-ten Elements eines int Arrays
	 * 
	 * @param array	int Array
	 * @param i		i-tes Element
	 * @param j		j-tes Element
	 * 
	 */
	public static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-2 (b) 
	 * sortiert ein int Array
	 * 
	 * @param array	vom Typ Integer
	 */
	public static void sort(int[] array){
		int[] tempArray = array;
		for (int i=0;i<tempArray.length;i++){
			for (int j=i+1;j<tempArray.length;j++){
				if (tempArray[j]<tempArray[i]){
					int temp = tempArray[i];
					tempArray[i]=tempArray[j];
					tempArray[j]=temp;
				}
			}
		}
		array = tempArray;
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-2 (c) 
	 * Bestimmt den Median eines Arrays.
	 * Zunächst wir das Array sortiert, dann bei ungerader Länge das mittlere Element,
	 * bei gerader Länge das größere der beiden mittleren Elemente herausgegeben.
	 * 
	 * @param 	array vom Typ int
	 * @return	der Median
	 * 
	 */
	public static int median(int[] array){
		sort(array);
		int l = array.length;
		if (l%2==1){ // Länge ist ungerade, dann das Mittlere
			return array[l/2];
		}
		else if (array[l/2] >= array[l/2+1]){ //Länge ist gerade -> das Grössere der beiden Mittleren
			return array[l/2-1];
		}
		else return array[l/2];
	}
	
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-2 (d) 
	 * Resize ein Array
	 * @param array		Eingabearray
	 * @param length	neue Größe
	 * @return			Array in neuer Größe, entweder gekürzt auf die neue Länge
	 * 					oder verlängert und aufgefüllt mit Nullen 
	 * 
	 */
	public static int[] resize (int[] array, int length){
		int[] tempArray = new int[length];
		if (length > array.length){
			for (int i = 0; i<array.length; i++) tempArray[i] = array[i];
			for (int i = array.length; i<length; i++) tempArray[i] = 0;
		}
		else {
			for (int i = 0; i<length-1; i++) tempArray[i] = array[i];
		}
		return tempArray;
	}
		
	/**
	 * EiP Übungsblatt 8, Aufgabe 8-2 (e) 
	 * Ausgabe der Array Elemente in einen String umwandelt
	 * @param 	array
	 * @return	in ein String der Form [el1 el2 el3 el4 ] umgewandeltes Array
	 * 
	 */
	public static String show(int[] array){
		String str = "[";
		for (int i=0; i<array.length; i++){
			str = str + array[i]+" ";
		}
		return str+"]";
	}
	
	
	// test der Methoden
	public static void main(String[] args) {

		// arrayGet
		try {
		    int i = arrayGet(intArray,-2);
		} catch (Exception e) {
		    String msg = e.getMessage();
		    System.out.println(msg);
		}
		System.out.println("********");
		// sum
		System.out.println("Sum ist "+sum(intArray));
		System.out.println("********");
		// mean
		System.out.println("Mittelwert ist "+mean(intArray));
		System.out.println("********");
		// square
		square(intArray);
		System.out.print("neues quadriertes Array ist: ");
		for(int i=0; i < intArray.length; i++) System.out.print(intArray[i]+" ");
		System.out.println();
		System.out.println("********");
		// max
		System.out.println("max ist : "+max(intArray));
		System.out.println("********");
		//swap
		swap(intArray, 4, 5);
		System.out.print("swapped elements 4 und 5 : ");
		for(int i=0; i < intArray.length; i++) System.out.print(intArray[i]+" ");
		System.out.println();
		System.out.println("********");
		// sort
		sort(intArray);
		System.out.print("neues sortiertes Array ist: ");
		for(int i=0; i < intArray.length; i++) System.out.print(intArray[i]+" ");
		System.out.println();		
		System.out.println("********");
		//median
		System.out.println("median ist : "+median(intArray));
		System.out.println("********");
		// resize
		for(int i=0; i < resize(intArray2,12).length; i++) System.out.print(resize(intArray2,12)[i]+" ");
		System.out.println();		
		System.out.println("********");
		System.out.println(show(intArray));
		
	}

}
