import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;
import java.util.Arrays;

/**
 * Die Klasse Bildbearbeitung laedt eine Bilddatei und fuehrt 
 * abhaengig von gewaehlten Optionen eine Reihe von 
 * Bildmanipulationen aus. 
 */
public class Bildbearbeitung {
	private int[][] pixels;
	
	/**
	 * Konstruktor fuer die Klasse Bildbearbeitung, die eine Bilddatei einliest
	 * und das zweidimensionale Pixel-Array pixels befuellt.
	 * @param file Einzulesende Bilddatei
	 */
	private Bildbearbeitung(String file) {
		try {
			BufferedImage img = ImageIO.read(new File(file));
			pixels = new int[img.getWidth()][img.getHeight()];
			for(int i = 0; i < dimX(); i++)
				for(int j = 0; j < dimY(); j++)
					pixels[i][j] = img.getRGB(i, j);
		} catch (IOException e) {}
	}	
	
	private int dimX(){
		return pixels.length;
	}
	
	private int dimY(){
		if(dimX() == 0)
			return 0;
		return pixels[0].length;
	}

	/** 
	 * Diese Funktion schreibt den Inhalt des Pixelarrays in die
	 * Datei 'ausgabe.png'. Falls diese Datei nicht vorhanden ist, 
	 * wird sie angelegt. Eine vorhandene Datei wird eventuell ueberschrieben!
	 */
	private void save(String option){
		BufferedImage img = new BufferedImage(dimX(), dimY(), 3);
		for(int i = 0; i < dimX(); i++)
			for(int j = 0; j < dimY(); j++)
				img.setRGB(i, j, pixels[i][j]);
		
		try {
			File outputfile = new File("ausgabe-" + option + ".png");
			ImageIO.write(img, "png", outputfile);
		} catch (Exception e) {}
	}

	/**
	 * Diese Funktion nimmt einen ARGB-Wert und wandelt ihn in 
	 * ein vierelementiges Array um, dass die einzelnen Bytes 
	 * als int-Komponenten enthaelt. 
	 * @param ARGB Integre Darstellung eines Pixels mit 4 Byte
	 * Information: alpha-rot-gruen-blau
	 * @return 4-elementiges Array [alpha, red, green, blue]
	 */
	private static int[] getColors(int ARGB) {
		// TODO: 8-4-a
		int[] array = new int[4];
		
		for (int i=3; i>-1; i--){
			array[i] = ARGB % 256 +255 ;
			ARGB = ARGB / 256;
		}
		return array;
	}
	

	/**
	 * Ein vierelementiges Array mit kleinen (< 1 byte) int-Werten 
	 * wird zu einem einzigen 4 byte Integer zusammengesetzt. 
	 * @param array 4-elementiges Array
	 * @return Eine Integerdarstellung einer Farbe in ARGB-Format.
	 */
	private int setColors(int[] array) {
		int alpha = array[0] << 24;
		int red = array[1] << 16;
		int green = array[2] << 8;
		int blue = array[3];
		return alpha | red | green | blue;
	}
	
	/**
	 * Das Bild wird hier rotiert und n*90 Grad.
	 * @param n Anzahl der Vierteldrehungen.
	 */
	private void rotate(int n) {
		// TODO 8-4-c
		int counter = 0;
		while (counter < n){
			int[][] tempImg = new int[dimY()][dimX()];
			for (int i = 0; i< dimY(); i++ ){
				for (int j = 0; j < dimX(); j++){
					tempImg[i][j] = pixels[j][dimY()-1-i]; 
				}
			}
			pixels = tempImg;
			counter++;
		}
	}
			
	
	/**
	 * Die Farben werden invertiert: Farbe = (255-Farbe)
	 */
	
	private void invert() {
		// TODO	8-4-d
		int[] tempArray = new int[4];
		for (int y = 0; y< dimY(); y++ ){
			for (int x = 0; x < dimX(); x++){
				// get colors
				tempArray = getColors(pixels[x][y]);
				// invert 
				tempArray[1] = 255-(int)tempArray[1];
				tempArray[2] = 255-(int)tempArray[2];
				tempArray[3] = 255-(int)tempArray[3];
				// save in pixels
				pixels[x][y] = setColors(tempArray);
			}
		}
	}
	
	
	
	
	/**
	 * Das Bild wird vertikal gespiegelt
	 */
	private void mirror() {
		int tempPixel;
		for (int i = 0; i< dimY(); i++ ){
			for (int j = 0; j < dimX()/2; j++){
				tempPixel = pixels[j][i];
				pixels[j][i] = pixels[dimX()-j-1][i];
				pixels[dimX()-j-1][i] = tempPixel;
			}
		}
	}

	/**
	 * Hilfsfunktion zum Zugriff, die Randpunkten gueltige 
	 * nullwertige Nachbarn zuweist.
	 */	
	private int getPixel(int i, int j) {
		// TODO 8-4-e optional
		return 0;
	}
	
	/**
	 * Diese Funktion betrachtet zu jedem Pixel seine Nachbarschaft, 
	 * summiert gewichtet diese Menge auf und skaliert sie
	 * @param filter 3x3 Umgebungsgewichte
	 */	
	private void meanFilter(double[] filter, double factor) {
		// TODO 8-4-e
	}
	
	
	/** 
	 * Gaussfilter
	 */	
	private void gauss(){
		double[] filter = {1,2,1,2,4,2,1,2,1};
		meanFilter(filter, 1.0/16.0);
	}
	
	/** 
	 * Blurfilter/ Lowpassfilter
	 */
	private void lpf(){
		double[] filter = {1,1,1,1,1,1,1,1,1};
		meanFilter(filter,1.0/9.0);
	}
	
	/** 
	 * Hochpassfilter 1
	 */
	private void hpf1(){
		double[] filter = {0,-1,0,-1,4,-1,0,-1,0};
		meanFilter(filter,1.0);
	}
	
	/** 
	 * Hochpassfilter 2
	 */
	private void hpf2(){
		double[] filter = {-1,-1,-1,-1,9,-1,-1,-1,-1};
		meanFilter(filter,1.0);
	}
	
	/** 
	 * Medianfilter
	 */	
	private void medianFilter(){
		//TODO 8-4-f
		
		
		// Lese 4 Nachbarn, bestimme Farbwerte, schreibe sie und die des Mittelpunktes in ein eigenes Array 
		
		// Deklaration eigener Arrays für die Farben
		int[]	alphaArray	= new int[5];
		int[] 	rotArray 	= new int[5];
		int[] 	gruenArray 	= new int[5];
		int[] 	blauArray 	= new int[5];
		int		counter		= 1;
		int[]	tmpColArr	= new int[4];
		int[][]	tmpImg		= new int[dimX()+2][dimX()+2];
		
		// gehe in einer 3x3 Matrix, beginnend links oben alle 8 Nachbarn des Mittelelements durch und
		// nehme nur die unmittelbar Angrenzenden
		for (int j=1; j<=3; j++){
			for (int i=1; i<=3; i++){
				// wenn counter gerade, dann schreibe die Werte in ein temp Array in 0..3
				if (counter%2 == 0){
					tmpColArr 					= getColors(pixels[i][j]); 
					alphaArray[(counter-2)/2] 	= tmpColArr[0]; 
					rotArray[(counter-2)/2] 	= tmpColArr[1];
					gruenArray[(counter-2)/2] 	= tmpColArr[2]; 
					blauArray[(counter-2)/2] 	= tmpColArr[3];	
				}
				// schreibe die Werte des Mittelpunktes an das Ende = 4
				else if (counter==5){
					alphaArray[4] 	= tmpColArr[0]; 
					rotArray[4] 	= tmpColArr[1];
					gruenArray[4] 	= tmpColArr[2]; 
					blauArray[4] 	= tmpColArr[3];	
				}
				counter++;
			}
		}
		
		// setze die neuen Werte für einen Bildpunkt
		tmpColArr[3] 	= median(alphaArray);
		tmpColArr[2] 	= median(rotArray);
		tmpColArr[1] 	= median(gruenArray); 
		tmpColArr[0] 	= median(blauArray);
		pixels[x][y] 	= setColors(tmpColArr);
		
		
		
		
	}

	/** 
	 * Fuegt auf n Pixeln Rauschen hinzu.
	 */	
	private void jitter(int n) {
		Random random = new Random();
		for(int i = 0; i < n; i++) {
			int x = random.nextInt(dimX());
			int y = random.nextInt(dimY());
			int[] colors = getColors(getPixel(x,y));
			colors[1] = random.nextInt(256);
			colors[2] = random.nextInt(256);
			colors[3] = random.nextInt(256);
			pixels[x][y] = setColors(colors);
		}
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
					// tausche elemente
					int temp = tempArray[i];
					tempArray[i]=tempArray[j];
					tempArray[j]=temp;
				}
			}
		}
		array = tempArray;
	}
 
    public static void main(String[] args) {

		int argCount = args.length;
		
		
		if(argCount == 0) {
			System.out.println("Verwendung: java Bildbearbeitung <FILE> [-options]");
			System.out.println("wobei options Folgendes umfasst:");
			System.out.println("\t -rot90 90 Grad rotieren");
			System.out.println("\t -rot180 180 Grad rotieren");
			System.out.println("\t -rot270 270 Grad rotieren");
			System.out.println("\t -i Farben invertieren");
			System.out.println("\t -m Horizontal spiegeln");
			System.out.println("\t -lpf Verwaschen");
			System.out.println("\t -median Median-Filter");
			System.out.println("\t -gauss Gauss-Filter");
			System.out.println("\t -hpf1 Hochpassfilter1");
			System.out.println("\t -hpf2 Hochpassfilter2");
			System.out.println("\t -jitter Verrauscht das Bild");
			return;
		}

		// load image as specified in first argument args[0]
		Bildbearbeitung bild = new Bildbearbeitung(args[0]);
		
		for(int i = 1; i < argCount; i++) {
			String option = args[i];
			System.out.println("Processing: " + option);
			if(option.equals("-rot90"))
				bild.rotate(1);
			else if(option.equals("-rot180"))
				bild.rotate(2);
			else if(option.equals("-rot270"))
				bild.rotate(3);
			else if(option.equals("-i"))
				bild.invert();
			else if(option.equals("-m"))
				bild.mirror();	
			else if(option.equals("-gauss"))
				bild.gauss();
			else if(option.equals("-median"))
				bild.medianFilter();
			else if(option.equals("-lpf"))
				bild.lpf();
			else if(option.equals("-jitter"))
				bild.jitter(50000);
			else if(option.equals("-hpf1"))
				bild.hpf1();
			else if(option.equals("-hpf2"))
				bild.hpf2();
		
			bild.save(""+i);
		}

		
		bild.save("done");
		
		int[] tarray=new int[4];
		  //   System.out.println(pixels[3][3]);
		     tarray=getColors(-6386301);
		     for(int i=0;i<4;i++)
		     {
		      switch(i)
		      {
		      case 0: System.out.print("Alpha: ");break;
		      case 1: System.out.print("R: ");break;
		      case 2: System.out.print("G: ");break;
		      case 3: System.out.print("B: ");break;
		      }
		      System.out.print(tarray[i]+"\n");
		     }
	}
}