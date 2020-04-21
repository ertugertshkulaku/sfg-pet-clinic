package guru.springframework.sfgpetclinic.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialitesService;
import guru.springframework.sfgpetclinic.services.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService{

	private final SpecialitesService specialitiesService;
	
	
	
	
	public VetServiceMap(SpecialitesService specialitiesService) {
	this.specialitiesService = specialitiesService;
}
@Override
	public Vet save(Vet object) {
		if(object.getSpeciality().size() > 0) {
			object.getSpeciality().forEach(speciality ->{
				if(speciality.getId() == null) {
					Speciality savedSpeciality = specialitiesService.save(speciality);
					speciality.setId(savedSpeciality.getId());
				}
			});
		}
		
		
		
		return super.save( object);
	}

	@Override
	public Set<Vet> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		// TODO Auto-generated method stub
		super.delete(object);
	}
	
	

}
