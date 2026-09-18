package Classes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
	private String PATH = "./assets/files/";
	private String FILE = "syslog.txt";
	
	public void systemEvents(String event, String username) {
		LocalDateTime time =  LocalDateTime.now();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String date = time.format(formatDate);
		try {
		      FileWriter myWriter = new FileWriter(PATH + FILE, true);
		      myWriter.write(date + " - " + event + " - Usuario: " + username + "\n");
		      myWriter.close();  
		      System.out.println("Evento registrado");
		    } catch (IOException e) {
		      System.out.println("ERROR AL REGISTRAR EVENTO DEL PROGRAMA");
		      e.printStackTrace();
		    }
	}
	
	public void systemEvents(String event, String username, int id) {
		LocalDateTime time =  LocalDateTime.now();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String date = time.format(formatDate);
		try {
		      FileWriter myWriter = new FileWriter(PATH + FILE, true);
		      myWriter.write(date + " - " + event + " - Usuario: " + username + ", ID USUARIO: " + id + "\n");
		      myWriter.close();  
		      System.out.println("Evento registrado");
		    } catch (IOException e) {
		      System.out.println("ERROR AL REGISTRAR EVENTO DEL PROGRAMA");
		      e.printStackTrace();
		    }
	}
	
	public void systemEvents(String event) {
		LocalDateTime time =  LocalDateTime.now();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String date = time.format(formatDate);
		try {
		      FileWriter myWriter = new FileWriter(PATH + FILE, true);
		      myWriter.write(date + " - " + event + "\n");
		      myWriter.close();  
		      System.out.println("Evento registrado");
		    } catch (IOException e) {
		      System.out.println("ERROR AL REGISTRAR EVENTO DEL PROGRAMA");
		      e.printStackTrace();
		    }
	}
	
	public void systemEvents(String event, String filename, boolean loaded) {
		if(loaded) {
			LocalDateTime time =  LocalDateTime.now();
			DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String date = time.format(formatDate);
			try {
			      FileWriter myWriter = new FileWriter(PATH + FILE, true);
			      myWriter.write(date + " - " + event + " - Archivo: " + filename + "\n");
			      myWriter.close();  
			      System.out.println("Evento registrado");
			    } catch (IOException e) {
			      System.out.println("ERROR AL REGISTRAR EVENTO DEL PROGRAMA");
			      e.printStackTrace();
			    }
		}
	}
}
