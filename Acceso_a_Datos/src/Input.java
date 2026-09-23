import java.util.Scanner;

public class Input {
	public static Scanner teclado = new Scanner(System.in);
	private static final String CONTINUE = "\nPulse 'enter' para continuar";

	public Input() {

	}

	public static int getInt(String enunciado) {
		int num = 0;
		try {
			System.out.print(enunciado);
			num = Integer.parseInt(teclado.nextLine().trim());
		} catch (Exception e) {
			System.out.println("Valor no válido");
		}

		return num;
	}

	public static String getString(String enunciado) {
		String element = "";
		try {
			System.out.print(enunciado);
			element = teclado.nextLine().trim();
		} catch (Exception e) {
			System.out.println("Valor no válido");
		}

		return element;
	}

	public static void toContinue() {
		System.out.print(CONTINUE);
		try {
			teclado.nextLine();

		} catch (Exception e) {

		}
	}
}
