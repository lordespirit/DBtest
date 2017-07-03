package utils;

public class Utils {
	
	public static void cleanScreen(){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public static String scan() {
		cleanScreen();
		System.out.print("|AÑADIR CONTACTO|\nIntroduce nombre del contacto : ");
		String name = Input.scannLine();
		System.out.print("Introduce apellido del contacto : ");
		String surname = Input.scannLine();
		System.out.print("Introduce teléfono del contacto : ");
		String phone = Input.scannLine();
		System.out.print("Introduce correo electrónico del contacto : ");
		String mail = Input.scannLine();
		System.out.print("Introduce dirección del contacto : ");
		String address = Input.scannLine();
		String data = name+" "+surname+";"+phone+";"+mail+";"+address;
		return data;
	}

}
