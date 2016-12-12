public class Passwort {	
	public static void main(String[] args){		
		String eingabe = "";
		int index = 0;
		
		if(args.length > 0)
			eingabe = args[0];
		else 
			return;
		
		if(args.length > 1)
			index = Integer.parseInt(args[1]);
		
		if(index == 0)
			for(int i = 0; i < 26; i++) 
				System.out.println(code(eingabe, i));
		else
			System.out.println(code(eingabe, index));
		

		
		
	}	

	public static String code(String eingabe, int index){
		String ausgabe = "";
		// TODO
		
		// durchlaufe den Eingabe-String char by char
		for (int i=0;i<eingabe.length();i++){
			
			if (!((int)eingabe.charAt(i) < 123 && (int)eingabe.charAt(i) > 96 )){
				// wenn Zeichen ausser a..z enthalten sind, transformiere diese nicht
				ausgabe = ausgabe + eingabe.charAt(i);
			}
			else if ((char)(int)eingabe.charAt(i)+index < 123) {
					// wenn bei der Verschiebung kein Überlauf stattfindet, nehme das i+index te Element
					ausgabe = ausgabe + (char)((int)(eingabe.charAt(i) + index));
				}
				else{
					// Überlauf!Mache nach z bei a weiter 
					ausgabe = ausgabe + (char)((int)(eingabe.charAt(i)+index-26));
				}

		}
		return ausgabe;
	}
}