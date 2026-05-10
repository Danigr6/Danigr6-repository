import Classes.Session;
import Classes.User;
import Classes.AdminUserManagement;
import Classes.Config;
import Classes.DAO;
import Classes.Input;
import Classes.Log;
import Classes.Maze;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) {
		Maze maze = new Maze();
		Session session = new Session();
		DAO conn = new DAO();
		Log log = new Log();
		int option;
		log.systemEvents("Programa iniciado");
		System.out.print(Config.WELCOME);
		System.out.println(Config.VERSION);

		try {
			while (Session.Program) {
				if (!session.isLogged()) {

					System.out.print(Config.UNLOGGED_MENU);
					option = Input.getInt();

					if (option == 1) {
						session.login();
						Input.toContinue();
					} else if (option == 2) {
						session.signup();
						Input.toContinue();
					} else if (option == 0) {
						System.out.print(Config.GOODBYE);
						log.systemEvents("Programa finalizado");
						session.exitProgram();
					} else {
						System.out.print("Introduce una opción válida (1, 2 o 0)");
					}
				}

				else {
					User user = Session.getUser();
					String role = conn.checkRole(user.getId());
					if (role.equalsIgnoreCase("user")) {
						userMenu(maze, session, log);
					} else if (role.equalsIgnoreCase("admin")) {
						adminMenu(maze, session, log);

					} else {
						System.out.println("ERROR: No ha sido posible identificar tipo de usuario");
					}
				}
			}
		} catch (FileNotFoundException e) {

			System.out.print("¡¡Ha surgido un problema con archivo de datos!!");
		}
	}

	public static void adminMenu(Maze maze, Session session, Log log) throws FileNotFoundException {
		System.out.print(Config.ADMIN_LOGGED_MENU);
		int option = Input.getInt();

		if (option == 1) {
			maze.loadMaze();
			Input.toContinue();
		} else if (option == 2) {
			maze.showMaze();
			Input.toContinue();
		} else if (option == 3) {
			maze.setStartEnd();
			Input.toContinue();
		} else if (option == 4) {
			maze.seleccionarCamino();
			Input.toContinue();
		} else if (option == 5) {
			session.showUserInfo();
		} else if (option == 6) {
			AdminUserManagement admin = new AdminUserManagement();
			admin.showUserManagementOptions();
		} else if (option == 7) {
			session.logout();
			maze.clearMaze();
		} else {
			if (option == 0) {
				int comfirm = Input
						.getInt("\n¿Seguro que quieres salir del programa? Pulsa 0 para confirmar o 1 para cancelar: ");

				if (comfirm == 0) {
					System.out.print(Config.GOODBYE);
					log.systemEvents("Programa finalizado");
					session.exitProgram();
				}

			} else {
				System.out.print("Introduce una opción válida");
			}
		}
	}

	public static void userMenu(Maze maze, Session session, Log log) {
		System.out.print(Config.USER_LOGGED_MENU);
		int option = Input.getInt();

		if (option == 1) {
			maze.loadMaze();
			Input.toContinue();
		} else if (option == 2) {
			maze.showMaze();
			Input.toContinue();
		} else if (option == 3) {
			maze.setStartEnd();
			Input.toContinue();
		} else if (option == 4) {
			maze.seleccionarCamino();
			Input.toContinue();
		} else if (option == 5) {
			session.showUserInfo();
		} else if (option == 6) {
			session.logout();
			maze.clearMaze();
		} else {
			if (option == 0) {
				int comfirm = Input
						.getInt("\n¿Seguro que quieres salir del programa? Pulsa 0 para confirmar o 1 para cancelar: ");

				if (comfirm == 0) {
					System.out.print(Config.GOODBYE);
					log.systemEvents("Programa finalizado");
					session.exitProgram();
				}

			} else {
				System.out.print("Introduce una opción válida");
			}
		}

	}
}
