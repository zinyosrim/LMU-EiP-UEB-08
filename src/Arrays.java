
public class Arrays {
	
	public static int[] intArray = {1,2,100,4,5,6,7,8,9,10};

	/**
	 * gibt das i-te Element eines Arrays zurück. Hierbei ist das erste Element array[0]  
	 * 
	 * @param array		Array von Integer Zahlen
	 * @param i			das i-te Element des Array wird abgefragt 
	 * @return der Wert des i-ten Element im Array
	 * @throws Exception
	 * 
	 */
	public static int arrayGet(int[] array, int i) throws Exception{
		try{
			return intArray[i-1];		
		}
		catch (ArrayIndexOutOfBoundsException e) {
		    throw new Exception("Fehler bei Aufruf. 2. Parameter i muss zwischen 1 und "+array.length+" liegen!");
		}	
	}
	
	/**
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
	 * gibt das arithmetische Mittel aller Zahlen in einem Integer Array zurück
	 * 
	 * @param 	array	Ein Array von Integer Zahlen	
	 * @return	Der Mittelwert, aller Zahlen im Array, also die Summe aller Zahlen durch die Anzahl
	 */
	public static int mean (int [] array){
			return (sum(array)/array.length); 
	}
	
	/**
	 * quadriert jedes Element eines int arrays
	 * 
	 * @param array
	 */
	public static void square (int[] array){
		for (int i=0; i < array.length; i++){
			array[i] = array[i] * array[i];
		}
	}
	
	/**
	 * gibt das grösste Element eines int Array zurück
	 * 
	 * @param  	array	ein int Array
	 * @return	das grösste element des Array
	 */
	public static int max(int[] array){
		int max = 0;
		for (int i=0; i < array.length; i++){
			if (array[i] > max) max = array[i];
		}
		return max;
	}
	
	public static void swap 

	// test der Methoden
	public static void main(String[] args) {

		try {
		    int i = arrayGet(intArray,-2);
		} catch (Exception e) {
		    String msg = e.getMessage();
		    System.out.println(msg);
		}
		System.out.println("Sum ist "+sum(intArray));
		System.out.println("Mittelwert ist "+mean(intArray));
		square(intArray);
		for(int i=0; i < intArray.length; i++) System.out.print(intArray[i]+" ");
		System.out.println();
		System.out.println("max ist : "+max(intArray));
	}

}
