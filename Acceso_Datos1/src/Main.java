import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static Scanner teclado = new Scanner(System.in);
	public static boolean program = true;
	public static String directoryPath = "/home/sistemas";
	public static String menuInicio = "\n--------------------Menú--------------------\n\n"
			+ "1. Mostrar directorios de la carpeta actual\n"
			+ "2. Cambiar a un subdirectorio o al directorio padre\n"
			+ "3. Crear un nuevo archivo de texto en la carpeta actual\n"
			+ "4. Mostrar el contenido de un archivo de texto de la carpeta actual\n"
			+ "5. Añadir una nueva línea de texto a un archivo de la carpeta actual\n"
			+ "6. Eliminar un archivo de la carpeta actual\n" + "7. Salir\n";

	public static void main(String[] args) throws IOException {
		directoryTreatement directorios = new directoryTreatement();
		Input input = new Input();
		
		while (program == true) {
			showMenu();
			int option1 = input.getInt("\nElige una opción: ");
			if (option1 == 1) {
				System.out.println("\n----Directorios----");
				directorios.showDirectories(directoryPath);
				input.toContinue();
			} else if (option1 == 2) {
				directoryPath = directorios.navDirectories(directoryPath);
				input.toContinue();
			} else if (option1 == 3) {
				directorios.createFile();
			} else if (option1 == 4) {
				System.out.println("En proceso...");
			} else if (option1 == 5) {
				String linea = input.getString("Escribe linea de texto: ");
				directorios.writeOnFile(linea);
			} else if (option1 == 6) {
				System.out.println("En proceso...");
			} else if (option1 == 7) {
				program = false;
			} else {
				System.out.println("Introduce un valor válido");
			}
		}
		System.out.println("Fin del programa");
	}

	public static void showMenu() {
		System.out.print(menuInicio);

	}

}
