package Classes;

import java.util.Scanner;

public class Input {
	private static Scanner keyboard = new Scanner(System.in);
	private static final String INT_ERROR = "\nEl valor introducido no es un numero";
	private static final String CONTINUE = "\nPulse 'enter' para continuar";
	
	// Sin enunciado
	public static int getInt() {
		int number = -1;
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}

	// Con enunciado
	public static int getInt(String text) {
		int number = -1;
		System.out.print(text);
		try {
			number = Integer.parseInt(keyboard.nextLine().trim());
		} catch (Exception e) {
			System.out.println(INT_ERROR);
		}
		return number;
	}
	
	// Con enunciado, recibe un boleano para repetir o no hasta que sea valido
	public static int getInt(String text, boolean repeat) {
		int number = -1;
		boolean success = false;
		do {
			System.out.print(text);
			try {
				number = Integer.parseInt(keyboard.nextLine().trim());
				success = true;
			} catch (Exception e) {
				System.out.println(INT_ERROR);
				if(!repeat) {
					return -1;
				}
			}
		} while(!success);
		return number;
	}

	// Sin enunciado
	public static String getString() {
		String value = keyboard.nextLine().trim();
		return value;
	}

	// Con enunciado
	public static String getString(String text) {
		System.out.print(text);
		return keyboard.nextLine().trim();
	}

	// Metodo para que pida una accion para continuar
	public static void toContinue() {
		System.out.print(CONTINUE);
		try {
			keyboard.nextLine();
			//keyboard.nextLine().trim();
		} catch (Exception e) {
			// Excepcion
		}
	}
	
	//Este método lo he creado para que a la hora de pedir las coordenadas y exista un error, el programa no salte al menú y permita repetir la introducción de las coordenadas.
	public static int getXY(String enunciado) {
		do {
			System.out.print(enunciado);
			try {
				int num = Integer.parseInt(keyboard.nextLine().trim());
				return num;
			}catch (Exception e) {
				System.out.print("ERROR: Introduce un valor numérico\n");
			}
		}while(true);
	}
}

