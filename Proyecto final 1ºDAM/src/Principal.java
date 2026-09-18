import java.util.Scanner;

public class Principal {
	public static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		int cantUsuarios = 3;
		Usuario[] usuarios = arrayUsuarios(cantUsuarios);
		
		System.out.println("\n---- LISTA DE USUARIOS ----");
		for (int i = 0; i < usuarios.length; i++) {
			System.out.println("Nombre: " + usuarios[i].nombre);
            System.out.println("Apellidos: " + usuarios[i].apellidos);
            System.out.println("Email: " + usuarios[i].email);
            System.out.println("------------------------");
		}

	}

	public static Usuario[] arrayUsuarios(int cantidad) {
		Usuario[] usuarios = new Usuario[cantidad];
		
		for (int i = 0; i < cantidad; i++) {
			usuarios[i] = new Usuario();
			System.out.println("\n" + (i + 1) + "º Usuario");

			System.out.print("Introduce nombre: ");
			usuarios[i].nombre = teclado.nextLine().trim();

			System.out.print("Apellidos: ");
			usuarios[i].apellidos = teclado.nextLine().trim();

			System.out.print("Email: ");
			usuarios[i].email = teclado.nextLine().trim();
		}

		return usuarios;
	}

}
