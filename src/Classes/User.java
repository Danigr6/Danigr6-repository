package Classes;

public class User {

	private int id;
	private String username;
	private String name;
	private String nif;
	private String email;
	private String address;
	private String birthdate;
	private String role;
	
	public User(int id, String username, String name, String nif, String email, String address, String birthdate, String role) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
		this.role = role;

	}
	
	public User(String username) {
		this.username = username;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getBirthdate() {
		return this.birthdate;
	}


	@Override
	public String toString() {
		return "ID: " + id + ", Username: " + username + "\n";
	}

	public void userInfo(int edad) {
		System.out.print("\n---USUARIO ACTUAL---\n" + "Usuario: " + this.username + "\nId de usuario: " + this.id + "\nNombre completo: " + this.name
				+ "\nNif/DNI: " + this.nif + "\nCorreo electrónico: " + this.email + "\nDireccion: " + this.address
				+ "\nFecha de nacimiento: " + this.birthdate + " --> Edad: " + edad + " años" + "\nRol de la cuenta: " + this.role + "\n----------------------\n");
	}

}
