package guru.springframework.sfgpetclinic.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
@ExtendWith(MockitoExtension.class)

class OwnerSDJpaServiceTest {
	private static final String LAST_NAME = null;
	@Mock
	OwnerRepository ownerRepo;
	@Mock
	PetRepository petRepo;
	@Mock
	PetTypeRepository petTypeRepo;
	@InjectMocks
	OwnerSDJpaService service;
	Owner returnOwner;

	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
	}

	@Test
	void testOwnerSDJpaService() {
	}

	@Test
	void testFindAll() {
		Set<Owner> returnOwnerSet = new HashSet<>();
		returnOwnerSet.add(Owner.builder().id(1L).build());
		returnOwnerSet.add(Owner.builder().id(2L).build());
		when(ownerRepo.findAll()).thenReturn(returnOwnerSet);
		Set<Owner> owners = service.findAll();
		assertNotNull(owners);
		assertEquals(2, owners.size());
		
	}

	@Test
	void testFindById() {
		when(ownerRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(returnOwner));
		Owner owner = service.findById(1L);
		assertNotNull(owner);
	}
	@Test
	void testFindByIdNotFound() {
		when(ownerRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		Owner owner = service.findById(1L);
		assertNull(owner);
	}

	@Test
	void testSave() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepo.save(ArgumentMatchers.any())).thenReturn(returnOwner);
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);
		verify(ownerRepo).save(ArgumentMatchers.any());
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);
		verify(ownerRepo, times(1)).delete(ArgumentMatchers.any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);
		verify(ownerRepo).deleteById(ArgumentMatchers.anyLong());
	}

	@Test
	void testFindByLastName() {
		Owner returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
		
		when(ownerRepo.findByLastName(ArgumentMatchers.any())).thenReturn(returnOwner);
		
		
		Owner smith = service.findByLastName(LAST_NAME);
		assertEquals(LAST_NAME, smith.getLastName());
		verify(ownerRepo).findByLastName(ArgumentMatchers.any());
	}

}
