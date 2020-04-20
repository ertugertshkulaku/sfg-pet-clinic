package guru.springframework.sfgpetclinic.model;

import java.util.Set;

public class Vet  extends Person{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<Speciality> speciality;

	public Set<Speciality> getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Set<Speciality> speciality) {
		this.speciality = speciality;
	}
	
	
	
}
