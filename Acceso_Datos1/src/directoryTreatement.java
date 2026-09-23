import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class directoryTreatement {
	public static Input input = new Input();

	public directoryTreatement() {

	}

	public void showDirectories(String directoryPath) throws IOException {
		Path directory = Paths.get(directoryPath);

		DirectoryStream<Path> items = null;
		try {
			items = Files.newDirectoryStream(directory);
			System.out.println("Path Actual: " + directoryPath);

			for (Path file : items) {
				if (Files.isDirectory(file)) {
					BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
					System.out.println("Nombre: " + file.getFileName() + " [Última modificación: "
							+ attrs.lastModifiedTime() + "]" + " [Tamaño: " + attrs.size() + " bytes]");
				}
			}
		} catch (IOException e) {
			System.out.println("Path Actual: " + directoryPath);
			System.out.println("No hay resultados dentro del directorio actual");
		}

		finally {
			if (items != null) {
				items.close();
			}
		}
	}

	public String navDirectories(String directoryPath) {
		String directory = input.getString("Introduce nombre de directorio: ");
		try {
			
			if (directory.equalsIgnoreCase("..")) {
				Path rutaActual = Paths.get(directoryPath);
				Path rutaPadre = rutaActual.getParent();

				if (rutaPadre != null) {
					System.out.println("Moviendo a Directorio Padre...");
					return rutaPadre.toString();
				} else {
					System.out.println("Ya te encuentras en el directorio raíz.");
					return directoryPath;
				}
			} else {
				Path nuevoPath = Paths.get(directoryPath, directory);
				if(Files.exists(nuevoPath) && Files.isDirectory(nuevoPath)) {
					System.out.println("Acceso a subdirectorio [" + directory + "] con éxito");
					return directoryPath + "/" + directory;
				} else {
					System.out.println("El directorio no existe");
					return directoryPath;
				}
				
			}

		} catch (Exception e) {
			System.out.println("Directorio no válido");
			return directoryPath;
		}
	}
	
	public void createFile() {
		String path = "./Files/";
		String fileName ="DaniFile.txt";
		try {
		      FileWriter myWriter = new FileWriter(path + fileName, true);
		      System.out.println("Archivo creado con éxito!!");
		    } catch (IOException e) {
		      System.out.println("Error al procesar archivo");
		      e.printStackTrace();
		    }
	}
	
	public void writeOnFile(String texto) {
		String path = "./Files/";
		String fileName ="DaniFile.txt";
		try {
		      FileWriter myWriter = new FileWriter(path + fileName, true);
		      myWriter.write("\n" + texto);
		      myWriter.close();
		      System.out.println("Escritura realizada con éxito!!!");
		    } catch (IOException e) {
		      System.out.println("Error al procesar archivo");
		      e.printStackTrace();
		    }
	}

}
