import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Principal {
	public static void main(String [] args) {
		System.out.println("Hola mundo");
		System.out.println("Fecha y hora actual: " + fechaHoraActual());
	}

	public static String fechaHoraActual() {
		LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        return ahora.format(formato);
	}
}