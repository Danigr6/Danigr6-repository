package Classes;

public class UserProfileManager {
	private String dataMenu = "Selecciona campo a modificar\n\n1- Contraseña\n2- Nombre\n3- NIF\n4- Email\n5- Dirección\n6- Fecha de nacimiento\n7- Eliminar cuenta\n\nOpcion: ";

	public void userDataMod(int id, Session session) {
		int selection = Input.getInt("Pulsa 1 para modificar datos de usuario o 0 para volver al menu: ");

		if (selection == 1) {
			DAO conn = new DAO();
			int option = Input.getInt(dataMenu);

			if (option == 1) {
				String password = Input.getString("Nueva contraseña: ").trim();

				if (!Utils.validatePassword(password)) {
					System.out.println("La contraseña no es válida (1 letra, 1 numero y 8 carácteres como mínimo)");
					return;
				}

				String comfirmPass = Input.getString("Confirmar contraseña: ").trim();

				if (password.equalsIgnoreCase(comfirmPass)) {

					String passMd5 = Utils.encryptMd5(comfirmPass);
					conn.profileUpdates(id, "password", passMd5);
				} else {
					System.out.println("La contraseña no coincide!!!");
				}

			} else if (option == 2) {
				String newName = Input.getString("Nuevo nombre: ").trim();

				if (!Utils.validateName(newName)) {
					System.out.println("El nombre que has introducido no es válido");
					return;
				} else {
					conn.profileUpdates(id, "name", newName);
				}
				
			} else if (option == 3) {
				String newNif = Input.getString("Introduce el nuevo NIF/NIE: ").trim();

				if (!Utils.validateNif(newNif)) {
					System.out.println("El NIF/NIE introducido no es válido: ");
				} else {
					if (conn.checkUser("nif", newNif)) {
						System.out.println("El nif introducido ya existe en la BBDD");
					} else {
						conn.profileUpdates(id, "nif", newNif);
					}
					
				}
			} else if (option == 4) {
				String newEmail = Input.getString("Introduce el nuevo correo electrónico: ");
				
				if(!Utils.validateEmail(newEmail)) {
					System.out.println("Email no válido");
				} else {
					if (conn.checkUser("email", newEmail)) {
						System.out.println("El email introducido ya existe en la BBDD");
					} else {
						conn.profileUpdates(id, "email", newEmail);
					}
				}
			} else if (option == 5) {
				String newAddress = Input.getString("Introduce nueva dirección: ");
				conn.profileUpdates(id, "address", newAddress);
				
			} else if (option == 6) {
				String newBirthdate = Input.getString("Introduce tu fecha de nacimiento: ");
				
				if(!Utils.validateDate(newBirthdate)) {
					System.out.println("Fecha no válida");
				} else {
					String newBirthdateSql = Utils.formatDateSql(newBirthdate);
					conn.profileUpdates(id, "birthdate", newBirthdateSql);
				}
			} else if(option == 7) {
				int o = Input.getInt("\n¡¡Esta opción eliminará todos los datos de la cuenta y cerrará sesión automáticamente!!\n\nSi deseas continuar pulsa 1 o pulsa 0 para volver al menú: ");
				if(o == 1) {
					String password = Input.getString("\n----ELIMINAR CUENTA----\nIntroduce contraseña de la cuenta: ");
					String comfirmPass = Input.getString("Comfirmar contraseña: ");
					String passMD5 = Utils.encryptMd5(comfirmPass);
					if(!comfirmPass.equalsIgnoreCase(password)) {
						System.out.println("La contraseña no coincide!!!");
						return;
					} else {
						if(conn.checkUser(id, "password", passMD5)) {
							conn.removeUser(id);
							session.logout();
						} else {
							System.out.println("La contraseña no es correcta");
							return;
						}
					}
				} else {
					return;
				}
				
			} else {
				System.out.println("Opción no válida");
				return;
			}
		} else if (selection == 0) {
			return;
		} else {
			return;
		}
	}
}
