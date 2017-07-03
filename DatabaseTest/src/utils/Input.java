package utils;
import java.util.Scanner;

public class Input {

		
		public static int scannInt(){
			Scanner reader = new Scanner(System.in);
			int var = reader.nextInt();
			//reader.close();
			return var;
		}
		
		public static float scannFloat(){
			Scanner reader = new Scanner(System.in);
			float var = reader.nextFloat();
			//reader.close();
			return var;
		}

		public static String scannLine(){
			Scanner reader = new Scanner(System.in);
			String var = reader.nextLine();
			//reader.close();
			return var;
		}
		
		public static double scannDouble(){
			Scanner reader = new Scanner(System.in);
			double var = reader.nextDouble();
			//reader.close();
			return var;
		}

}
