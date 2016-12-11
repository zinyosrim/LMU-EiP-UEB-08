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
		return ausgabe;
	}
}