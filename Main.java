import Classes.Session;
import Classes.Config;
import Classes.Input;
import Classes.Log;
import Classes.Maze;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
    	Maze maze = new Maze();
        Session session = new Session();
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

                    System.out.print(Config.LOGGED_MENU);
                    option = Input.getInt();

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
                        Input.toContinue();
                    } else if (option == 6) {
                        session.logout();
                        maze.clearMaze();
                    } else if (option == 0) {
                        int comfirm = Input.getInt(
                            "\n¿Seguro que quieres salir del programa? Pulsa 0 para confirmar o 1 para cancelar: "
                        );

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
        } catch (FileNotFoundException e) {
            System.out.print("¡¡Ha surgido un problema con archivo de datos!!");
        }
    }
}