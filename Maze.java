package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Maze {
	public static Scanner teclado = new Scanner(System.in);
	public char[][] map;
	private String filename;
	private String mapFile;
	private boolean loaded = false;
	private boolean entryExit = false;
	private int rows, cols, startI, startJ, endI, endJ;
	private int steps;
	private Log log = new Log();

	public Maze() {
		this.startI = -1;
		this.startJ = -1;
		this.endI = -1;
		this.endJ = -1;
	}

	public void loadMaze() {
		String [] filePath = selectLab();
		mapFile = filePath[1];
		filename = filePath[0] + filePath[1];
		if (filename != null) {
			readFile(filename);
			loaded = true;
			entryExit = false;
			System.out.print("\nMapa cargado correctamente!!\n");
			log.systemEvents("Mapa cargado", filePath[1], loaded);
		}
	}

	public void clearMaze() {
		map = null;
		loaded = false;
		filename = null;
		entryExit = false;
	}

	private String[] selectLab() {
		String [] filePath = new String[2];
		String path = "./assets/mazes/";
		File folder = new File(path);
		filePath[0] = path;

		try {
			if (!folder.exists() || !folder.isDirectory()) {
				System.out.println("La carpeta no existe.");
				return null;
			}

			File[] labs = folder.listFiles();
			if (labs == null || labs.length == 0) {
				System.out.println("No hay laberintos disponibles.");
				return null;
			}

			System.out.println("\n--- Seleccione un laberinto ---");
			for (int i = 0; i < labs.length; i++) {
				if (labs[i].isFile()) {
					System.out.println((1 + i) + "º " + labs[i].getName());
				}
			}

			while (true) {
				try {
					int selected = Input.getInt("\nSelecciona un laberinto: ");
					if (selected > 0 && selected <= labs.length) {
						File lab = labs[selected - 1];
						if (lab.isFile()) {
							filePath[1] = lab.getName();
							return filePath;
						}
					}
					System.out.println("ERROR: Opción no válida");
				} catch (Exception e) {
					System.out.println("ERROR: entrada no válida.");
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR inesperado.");
		}
		return null;
	}

	private void readFile(String filename) {
		ArrayList<String> lines = new ArrayList<>();
		File myObj = new File(filename);

		try (Scanner myReader = new Scanner(myObj)) {
			while (myReader.hasNextLine()) {
				lines.add(myReader.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha podido cargar el mapa");
		}
		buildMap(lines);
	}

	private void buildMap(ArrayList<String> lines) {
		rows = lines.size();
		cols = lines.get(0).length();
		map = new char[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				map[i][j] = lines.get(i).charAt(j);
			}
		}
	}

	public void showMaze() {
		if (!loaded) {
			System.out.print("\nNo se ha cargado ningún laberinto");
			log.systemEvents("Error al mostrar laberinto (No se ha cargado laberinto)");
			return;
		}

		String[] numerosE = { "0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣", "1️⃣0️⃣", "1️⃣1️⃣",
				"1️⃣2️⃣", "1️⃣3️⃣", "1️⃣4️⃣", "1️⃣5️⃣", "1️⃣6️⃣", "1️⃣7️⃣", "1️⃣8️⃣", "1️⃣9️⃣", "2️⃣0️⃣", "2️⃣1️⃣",
				"2️⃣2️⃣", "2️⃣3️⃣", "2️⃣4️⃣", "2️⃣5️⃣", "2️⃣6️⃣", "2️⃣7️⃣", "2️⃣8️⃣", "2️⃣9️⃣", "3️⃣0️⃣", "3️⃣1️⃣",
				"3️⃣2️⃣", "3️⃣3️⃣", "3️⃣4️⃣", "3️⃣5️⃣", "3️⃣6️⃣", "3️⃣7️⃣", "3️⃣8️⃣", "3️⃣9️⃣", "4️⃣0️⃣", "4️⃣1️⃣",
				"4️⃣2️⃣", "4️⃣3️⃣", "4️⃣4️⃣", "4️⃣5️⃣", "4️⃣6️⃣", "4️⃣7️⃣", "4️⃣8️⃣", "4️⃣9️⃣", "5️⃣0️⃣" };

		System.out.print("▫️▫️");
		for (int j = 0; j < cols; j++) {
			if (j >= 10) {
				System.out.print(numerosE[j / 10]);
			} else {
				System.out.print("▫️");
			}
		}
		System.out.println();
		System.out.print("▫️▫️");
		for (int j = 0; j < cols; j++) {
			System.out.print(numerosE[j % 10]);
		}
		System.out.println();

		for (int i = 0; i < rows; i++) {
			if (i < 10) {
				System.out.print("▫️" + numerosE[i]);
			} else {
				System.out.print(numerosE[i]);
			}
			for (int j = 0; j < cols; j++) {
				if (map[i][j] == '#') {
					System.out.print("🧱️");
				} else if (map[i][j] == 'I') {
					System.out.print("🐭️");
				} else if (map[i][j] == 'F') {
					System.out.print("🧀️");
				} else {
					System.out.print("⬜️");
				}
			}
			System.out.println();
		}
		log.systemEvents("Laberinto mostrado", mapFile, loaded);
	}

	public void setStartEnd() {
		if (!loaded) {
			System.out.print("\nNo se ha cargado ningún laberinto");
			log.systemEvents("Error establecer casillas (No se ha cargado mapa)");
			return;
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (map[i][j] == 'I' || map[i][j] == 'F') {
					map[i][j] = ' ';
				}
			}
		}

		System.out.print("\nPara establecer la entrada indica las coordenadas\n");
		do {
			startI = Input.getXY("Valor de I: ");
			startJ = Input.getXY("Valor de J: ");
			if (startI >= rows || startJ >= cols || startI < 0 || startJ < 0) {
				System.out.print("\nValor no válido!!!\n");
				log.systemEvents("Error establecer casilla entrada con éxito (Valor no válido: " + startI + "," + startJ + ")");
			} else if (map[startI][startJ] == '#') {
				System.out.print("\nLas coordenadas coinciden con un muro\n");
				log.systemEvents("Error establecer casilla entrada con éxito (Coordenadas coinciden con un muro: " + startI + "," + startJ + ")");
			} else {
				map[startI][startJ] = 'I';
				break;
			}
		} while (true);

		System.out.print("\nPara establecer la salida indica las coordenadas\n");
		do {
			endI = Input.getXY("Valor de I: ");
			endJ = Input.getXY("Valor de J: ");
			if (endI >= rows || endJ >= cols || endI < 0 || endJ < 0 || (endI == startI && endJ == startJ)) {
				System.out.print("\nValor no válido!!!\n");
				log.systemEvents("Error establecer casilla salida con éxito (Valor no válido: " + endI + "," + endJ + ")");
			} else if (map[endI][endJ] == '#') {
				System.out.print("Las coordenadas coinciden con un muro\n");
				log.systemEvents("Error establecer casilla salida con éxito (Coordenadas coinciden con un muro: " + endI + "," + endJ + ")");
			} else {
				map[endI][endJ] = 'F';
				break;
			}
		} while (true);

		entryExit = true;
		System.out.print("\nEntrada y salida integrada correctamente!!!\n");
		log.systemEvents("Establecer casillas con éxito (" + startI + "," + startJ + " - " + endI + "," + endJ + ")");
	}

	public void seleccionarCamino() {
		if (!loaded) {
			log.systemEvents("Error acceder a la selección buscar camino (No se ha cargado laberinto)");
			System.out.println("NO se ha cargado ningún mapa");
			return;
		}

		if (entryExit) {
			log.systemEvents("Selección buscar camino");
			System.out.println("--- SELECCIONA UNA OPCIÓN ---");
			int opcion = Input
					.getInt("\n1º Encontrar cualquier camino\n2º Camino más corto\n0 Cancelar\n\nSelección: ");

			if (opcion == 1) {
				longPath(map, startI, startJ);
			} else if (opcion == 2) {
				shortPath(map, startI, startJ);
			}
		} else {
			log.systemEvents("Error acceder a la selección buscar camino (No hay casillas)");
			System.out.print("Entrada y salida NO implementadas!!!");
		}
	}

	public void longPath(char[][] map, int startY, int startX) {
		limpiarMapa(map);
		Coordinate[][] aux = new Coordinate[rows][cols];
		boolean[][] visitado = new boolean[rows][cols];
		ArrayList<Coordinate> lista = new ArrayList<>();

		Coordinate start = new Coordinate(startX, startY);
		lista.add(start);
		visitado[startY][startX] = true;

		Coordinate end = null;
		int[] movementsX = { 0, 0, -1, 1 };
		int[] movementsY = { 1, -1, 0, 0 };

		while (!lista.isEmpty()) {
			Coordinate currentPos = lista.remove(lista.size() - 1);
			if (map[currentPos.y][currentPos.x] == 'F') {
				end = currentPos;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nextX = currentPos.x + movementsX[i];
				int nextY = currentPos.y + movementsY[i];

				if (nextY >= 0 && nextY < rows && nextX >= 0 && nextX < cols) {
					if (map[nextY][nextX] != '#' && !visitado[nextY][nextX]) {
						visitado[nextY][nextX] = true;
						aux[nextY][nextX] = currentPos;
						lista.add(new Coordinate(nextX, nextY));
					}
				}
			}
		}

		if (end != null) {
			createPath(map, start, end, aux);
			log.systemEvents("Resolver primer camino (Éxito: " + steps + " pasos)");
		} else {
			System.out.println("No se ha encontrado ningún camino");
			log.systemEvents("Resolver primer camino (Sin solución)");
		}
	}

	public void shortPath(char[][] map, int startY, int startX) {
		limpiarMapa(map);
		Coordinate[][] aux = new Coordinate[rows][cols];
		boolean[][] visitado = new boolean[rows][cols];
		Queue<Coordinate> cola = new LinkedList<>();

		Coordinate start = new Coordinate(startX, startY);
		cola.add(start);
		visitado[startY][startX] = true;

		Coordinate end = null;
		int[] movementsX = { 1, -1, 0, 0 };
		int[] movementsY = { 0, 0, 1, -1 };

		while (!cola.isEmpty()) {
			Coordinate currentPos = cola.poll();
			if (map[currentPos.y][currentPos.x] == 'F') {
				end = currentPos;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nextX = currentPos.x + movementsX[i];
				int nextY = currentPos.y + movementsY[i];

				if (nextY >= 0 && nextY < rows && nextX >= 0 && nextX < cols) {
					if (map[nextY][nextX] != '#' && !visitado[nextY][nextX]) {
						visitado[nextY][nextX] = true;
						aux[nextY][nextX] = currentPos;
						cola.add(new Coordinate(nextX, nextY));
					}
				}
			}
		}

		if (end != null) {
			createPath(map, start, end, aux);
			log.systemEvents("Resolver camino más corto (Éxito: " + steps + " pasos)");
		} else {
			System.out.println("No se ha encontrado ningún camino");
			log.systemEvents("Resolver camino más corto (Sin solución)");
		}
	}

	private void createPath(char[][] pathMap, Coordinate entry, Coordinate exit, Coordinate[][] aux) {
		Coordinate currentPos = exit;
		while (!(currentPos.x == entry.x && currentPos.y == entry.y)) {
			Coordinate previous = aux[currentPos.y][currentPos.x];
			if (pathMap[previous.y][previous.x] != 'I') {
				if (currentPos.x > previous.x) {
					pathMap[previous.y][previous.x] = '>';
				} else if (currentPos.x < previous.x) {
					pathMap[previous.y][previous.x] = '<';
				} else if (currentPos.y > previous.y) {
					pathMap[previous.y][previous.x] = 'V';
				} else if (currentPos.y < previous.y) {
					pathMap[previous.y][previous.x] = '^';
				}
			}
			currentPos = previous;
		}

		showPath(pathMap);
		int steps = printSteps(entry, exit, aux);
		System.out.println("----- Fin -----\nPasos totales: " + steps);
	}

	public void showPath(char[][] pathMap) {
		String[] numerosE = { "0️⃣", "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣", "1️⃣0️⃣", "1️⃣1️⃣",
				"1️⃣2️⃣", "1️⃣3️⃣", "1️⃣4️⃣", "1️⃣5️⃣", "1️⃣6️⃣", "1️⃣7️⃣", "1️⃣8️⃣", "1️⃣9️⃣", "2️⃣0️⃣", "2️⃣1️⃣",
				"2️⃣2️⃣", "2️⃣3️⃣", "2️⃣4️⃣", "2️⃣5️⃣", "2️⃣6️⃣", "2️⃣7️⃣", "2️⃣8️⃣", "2️⃣9️⃣", "3️⃣0️⃣", "3️⃣1️⃣",
				"3️⃣2️⃣", "3️⃣3️⃣", "3️⃣4️⃣", "3️⃣5️⃣", "3️⃣6️⃣", "3️⃣7️⃣", "3️⃣8️⃣", "3️⃣9️⃣", "4️⃣0️⃣", "4️⃣1️⃣",
				"4️⃣2️⃣", "4️⃣3️⃣", "4️⃣4️⃣", "4️⃣5️⃣", "4️⃣6️⃣", "4️⃣7️⃣", "4️⃣8️⃣", "4️⃣9️⃣", "5️⃣0️⃣" };

		System.out.print("▫️▫️");
		for (int j = 0; j < cols; j++) {
			if (j >= 10) {
				System.out.print(numerosE[j / 10]);
			} else {
				System.out.print("▫️");
			}
		}
		System.out.println();
		System.out.print("▫️▫️");
		for (int j = 0; j < cols; j++) {
			System.out.print(numerosE[j % 10]);
		}
		System.out.println();

		for (int i = 0; i < rows; i++) {
			if (i < 10) {
				System.out.print("▫️" + numerosE[i]);
			} else {
				System.out.print(numerosE[i]);
			}
			for (int j = 0; j < cols; j++) {
				char c = map[i][j];
				if (c == '#') {
					System.out.print("🧱️");
				} else if (c == 'I') {
					System.out.print("🐭️");
				} else if (c == 'F') {
					System.out.print("🧀️");
				} else if (c == '<') {
					System.out.print("◀️");
				} else if (c == '>') {
					System.out.print("▶️");
				} else if (c == '^') {
					System.out.print("🔼️");
				} else if (c == 'V') {
					System.out.print("🔽️");
				} else {
					System.out.print("⬜️");
				}
			}
			System.out.println();
		}
	}

	private int printSteps(Coordinate entry, Coordinate currentPos, Coordinate[][] aux) {
		if (currentPos.x == entry.x && currentPos.y == entry.y) {
			System.out.println("(" + currentPos.y + "," + currentPos.x + ") ----- INICIO -----");
			return 0;
		}

		Coordinate previous = aux[currentPos.y][currentPos.x];
		steps = printSteps(entry, previous, aux) + 1;

		String direction = "";
		if (currentPos.y > previous.y) {
			direction = "Abajo";
		} else if (currentPos.y < previous.y) {
			direction = "Arriba";
		} else if (currentPos.x > previous.x) {
			direction = "Derecha";
		} else if (currentPos.x < previous.x) {
			direction = "Izquierda";
		}

		System.out.println("(" + currentPos.y + "," + currentPos.x + ") " + direction);
		return steps;
	}

	public void limpiarMapa(char[][] map) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				char c = map[i][j];
				if (c == '<' || c == '>' || c == '^' || c == 'V') {
					map[i][j] = ' ';
				}
			}
		}
	}
}