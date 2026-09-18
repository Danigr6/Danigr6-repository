package Classes;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {
	private static final DateTimeFormatter EU_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter SQL_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static boolean validateUsername(String username) {
		String regexUser = "^[a-zA-Z][a-zA-Z0-9._]{2,14}$"; //Empezar por una letra obligatoriamente, 3 caracteres como min y 16 max
		return username.matches(regexUser);
	}
	
	public static boolean validatePassword(String password) {
		String regexPass = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d\\W_]{8,}$"; //Al menos una letra, un numero, minimo de 8 caracteres y permite caracteres especiales
		return password.matches(regexPass);
	}
	
	public static boolean validateName(String name) {
		String regexName = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{2,}(?:\\s[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]{2,})+$";
		return name.matches(regexName);
	}
	
	public static boolean validateEmail(String email) {
		String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email.matches(regexEmail);
	}
	
	public static boolean validateNif(String nif) {
		String regexNifNie = "^([0-9]{8}|[XYZ][0-9]{7})[TRWAGMYFPDXBNJZSQVHLCKE]$";
		return nif.matches(regexNifNie);
	}
	
	public static boolean validateDate(String date) {
		String regexDate = "^(?:(?:(?:0[1-9]|[12]\\d|3[01])[/\\-](?:0[13578]|1[02])|(?:0[1-9]|[12]\\d|30)[/\\-](?:0[469]|11)|(?:0[1-9]|1\\d|2[0-8])/02)[/\\-]\\d{4}|29[/\\-]02[/\\-](?:(?:\\d{2}(?:0[48]|[2468][048]|[13579][26])|(?:[02468][048]|[13579][26])00)))$";
		return date.matches(regexDate);
	}
	
	public static String encryptMd5(String password) {
		try {
            // 1. Creamos la instancia de MessageDigest para MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2. Convertimos la contraseña en un array de bytes y generamos el hash
            byte[] messageDigest = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // 3. Convertimos el array de bytes a formato hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                // El 0xff y el formato %02x aseguran que siempre tengamos 2 caracteres por byte
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // Esto solo saltaría si el entorno de Java no soporta MD5 (muy raro)
            throw new RuntimeException("Error: No se encontró el algoritmo MD5", e);
        }
	}
	
	public static String formatDateEU(String dateSql) {
		if (dateSql == null) {
			return null;
		}
        try {
            LocalDate date = LocalDate.parse(dateSql, SQL_FORMATTER);
            return date.format(EU_FORMATTER);
        } catch (DateTimeParseException e) {
            return "Formato inválido";
        }
	}
	
	public static String formatDateSql(String dateEu) {
		if (dateEu == null) {
			return null;
		}
        try {
            String cleanDate = dateEu.replace("-", "/");
            LocalDate date = LocalDate.parse(cleanDate, EU_FORMATTER);
            return date.format(SQL_FORMATTER);
        } catch (DateTimeParseException e) {
            return "Formato inválido";
        }
	}
	
	public static int getAge(String dateSql) {
		if (dateSql == null) {
			return 0;
		}
	    
	    // 1. Convertimos el texto a fecha
	    LocalDate fechaNac = LocalDate.parse(dateSql);
	    
	    // 2. Calculamos la diferencia con "hoy" en años
	    return Period.between(fechaNac, LocalDate.now()).getYears();
	}
}
