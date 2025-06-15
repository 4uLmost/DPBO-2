import java.util.Scanner;

public class Admin extends Profile {

	private String department;

	public Admin(int id, String name, String email, String password, String department) {
		super(id, name, email, password);
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
