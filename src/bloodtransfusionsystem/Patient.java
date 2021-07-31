/**
 * @(#)Patient.java
 *
 *
 * @author
 * @version 1.00
 *
 * Sample to create a Patient where the user has entered a name & bloodtype to variables name & blood
 * 		Patient p = new Patient(name, blood);
 */

public class Patient {
	private String name;
	private String bloodType;

	public Patient(String name, String bloodType) {
		this.name = name;
		this.bloodType = bloodType;
	}

	public String getName() {
		return name;
	}

	public String getBloodType() {
		return bloodType;
	}

	public String toString() {
		return "Name : "+name+"      BloodType: "+bloodType;
	}
}