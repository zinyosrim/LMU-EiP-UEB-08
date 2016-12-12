
public class Shift {

	public static int setColors(int[] array) {
		int alpha = array[0] << 24;
		int red = array[1] << 16;
		int green = array[2] << 8;
		int blue = array[3];
		return alpha | red | green | blue;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {0,0,255,255};
		System.out.println(setColors(array));

	}

}
