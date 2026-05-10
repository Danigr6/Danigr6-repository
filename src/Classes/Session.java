package Classes;

import java.io.FileNotFoundException;

public class Session {

	public static boolean Program = true;
	private static User user;
	private boolean logged;
	private Log log = new Log();

	public Session() {
		logged = false;
	}

	public boolean isLogged() {
		return logged;
	}

	public boolean login() {
		DAO connection = new DAO();
		String username = Input.getString("Nombre de usuario: ");
		String password = Input.getString("Contraseña: ");
		String passMd5 = Utils.encryptMd5(password);
		try {
			User u = connection.checkLogin(username, passMd5);
			if (u != null) {
				logged = true;
				setUser(connection.info(username, passMd5));
				log.systemEvents("Login exitoso", username);

				return true;
			}
		} catch (Exception e) {
			System.out.println("Error: No se pudo procesar el login. " + e.getMessage());
		}

		System.out.print("El usuario y/o contraseña son incorrectos");
		log.systemEvents("Login fallido", username);
		return false;
	}

	public void logout() {
		logged = false;
		System.out.print("\n---Sesión cerrada---\n");
		log.systemEvents("Sesion cerrada", getUser().getUsername());
		setUser(null);
	}

	public void showUserInfo() {
		DAO conn = new DAO();
		User u = conn.info(getUser().getUsername());
		
		if (getUser() != null) {
			u.userInfo(Utils.getAge(Utils.formatDateSql(u.getBirthdate())));
			log.systemEvents("Mostrar usuario actual", getUser().getUsername(), getUser().getId());
			UserProfileManager m = new UserProfileManager();
			m.userDataMod(getUser().getId(), this);
			
		}
	}

	public boolean signup() throws FileNotFoundException {
		DAO connection = new DAO();
		String username = Input.getString("Introduce nombre de usuario: ").trim();
		if (!Utils.validateUsername(username)) {
			System.out.println("Nombre no válido!!(Debe empezar por letra, 3-16 caracteres)");
			log.systemEvents("Registro fallido", username);
			return false;
		}
		User u = new User(username);
		if (connection.checkUser(u)) {
			System.out.print("El nombre de usuario ya está en uso");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		String password = Input.getString("Contraseña: ").trim();

		if (!Utils.validatePassword(password)) {
			System.out.println("La contraseña no es válida (1 letra, 1 numero y 8 carácteres como mínimo)");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		String comfirmPass = Input.getString("Confirma la contraseña: ").trim();
		if (!password.equals(comfirmPass)) {
			System.out.println("La contraseña no coincide!!");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		String name = Input.getString("Introduce tu nombre completo: ").trim();
		if (!Utils.validateName(name)) {
			System.out.println("El nombre que has introducido no es válido!!");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		String nif = Input.getString("Introduce tu DNI/NIF: ").trim();
		if (!Utils.validateNif(nif)) {
			System.out.println("El Nif/Nie introducido no es válido!!");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		String email = Input.getString("Introduce tu correo electrónico: ").trim();
		if (!Utils.validateEmail(email)) {
			System.out.println("El correo electrónico introducido no es válido!!");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		String address = Input.getString("Introduce tu dirección: ").trim();
		String birthdate = Input.getString("Introduce tu fecha de nacimiento: ").trim();
		if (!Utils.validateDate(birthdate)) {
			System.out.println("La fecha introducida es incorrecta!!");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		String passMd5 = Utils.encryptMd5(password);
		String dateSql = Utils.formatDateSql(birthdate);

		if (connection.signupUser(username, passMd5, name, nif, email, address, dateSql)) {
			System.out.println("\n------Usuario creado con éxito------");
			log.systemEvents("Registro exitoso", username);
		} else {
			System.out.println("\n------Eror al registrar usuario------");
			log.systemEvents("Registro fallido", username);
			return false;
		}

		return true;
	}

	public void exitProgram() {
		Program = false;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Session.user = user;
	}
}