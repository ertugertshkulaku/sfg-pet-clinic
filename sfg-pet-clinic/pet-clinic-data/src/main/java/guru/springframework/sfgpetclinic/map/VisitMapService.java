package guru.springframework.sfgpetclinic.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long>implements VisitService{

	@Override
	public Set<Visit> findAll() {
		return super.findAll();
	}

	@Override
	public Visit findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Visit save(Visit object) {
		if(object.getPet() ==null ||
				object.getPet().getOwner() == null || 
				object.getPet().getId() == null ||
				object.getPet().getId() == null) {
			throw new RuntimeException("Invalid Visit");
		}
		return super.save(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Visit object) {
		super.delete(object);
	}
	
	
	

}
