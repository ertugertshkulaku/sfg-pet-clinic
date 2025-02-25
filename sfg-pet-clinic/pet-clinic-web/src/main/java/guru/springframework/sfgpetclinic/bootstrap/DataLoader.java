package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialitesService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {
	
	
	private  final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialitesService specialitesService;
	private final VisitService visitService;
	
	
	
public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitesService specialitesService,VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService= petTypeService;
		this.specialitesService = specialitesService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		int count = petTypeService.findAll().size();
		
		if(count == 0) {
		loadData();
		
		}
		
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		Speciality savedRadiology = specialitesService.save(radiology);
		
		Speciality surgery = new Speciality();
		radiology.setDescription("Surgery");
		Speciality savedSurgery = specialitesService.save(surgery);
		
		Speciality dentistry = new Speciality();
		radiology.setDescription("Dentistry");
		Speciality savedDentistry = specialitesService.save(dentistry);
		
		
		Owner owner1= new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("123123123123");
		
		
		
		
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		owner1.getPets().add(mikesPet);
		ownerService.save(owner1);
		
		Owner owner2= new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		owner2.setAddress("123 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("123123123123");
		
		Pet jonasPet = new Pet();
		jonasPet.setPetType(savedCatPetType);
		jonasPet.setOwner(owner2);
		jonasPet.setBirthDate(LocalDate.now());
		jonasPet.setName("Bobi");
		owner2.getPets().add(jonasPet);
		ownerService.save(owner2);
		
		Visit catVisit = new Visit();
		catVisit.setPet(jonasPet);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("MACOK BUDALLA");
		
		visitService.save(catVisit);
		
		System.out.println("Loaded Owners...");
		System.out.println("Loaded OWNERS " + ownerService.findAll().size() );
		
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("AXE");
		vet1.getSpecialties().add(savedRadiology);
		
		vetService.save(vet1);
		
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		vet2.getSpecialties().add(savedSurgery);
		
		vetService.save(vet2);
		
	//	System.out.println("Loaded Vets..." +vetService.findAll().size());
	}
	
	

}
