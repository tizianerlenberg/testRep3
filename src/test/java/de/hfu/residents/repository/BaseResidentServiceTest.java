package de.hfu.residents.repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentRepositoryStub;
import de.hfu.residents.service.ResidentServiceException;

import java.util.Date;
import java.util.List;

public class BaseResidentServiceTest {
	private ResidentRepositoryStub testResidentRepository;
	private BaseResidentService testBaseResidentService;
	Resident resident1;
	Resident resident2;
	Resident resident3;
	Resident resident4;
	Resident resident5;
	Resident resident6;

	@Before
	public void initializeResidentRepository() {
		testResidentRepository = new ResidentRepositoryStub();
		testBaseResidentService = new BaseResidentService();
		testBaseResidentService.setResidentRepository(testResidentRepository);
		resident1 = new Resident("Tom", "Mueller", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident2 = new Resident("Tim", "Mueller", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident3 = new Resident("Tomas", "Mueller", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident4 = new Resident("Erika", "Musterfrau", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident5 = new Resident("Erika2", "Musterfrau", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		testResidentRepository.addResident(resident1);
		testResidentRepository.addResident(resident2);
		testResidentRepository.addResident(resident3);
		testResidentRepository.addResident(resident4);
		testResidentRepository.addResident(resident5);
	}
	
	@Test
	public void getFilteredResidentsListTest() {
		Resident filter1 = new Resident("Tom", "", "", "", new Date());
		List<Resident> result = testBaseResidentService.getFilteredResidentsList(filter1);
		
		assertTrue(result.contains(resident1));
		
		assertFalse(result.contains(resident2));
		assertFalse(result.contains(resident3));
		assertFalse(result.contains(resident4));
		assertFalse(result.contains(resident5));
	}
	
	@Test
	public void getFilteredResidentsListTest2() {
		Resident filter = new Resident("To*", "", "", "", new Date());
		List<Resident> result = testBaseResidentService.getFilteredResidentsList(filter);
		
		assertTrue(result.contains(resident1));
		assertTrue(result.contains(resident3));
		
		assertFalse(result.contains(resident2));
		assertFalse(result.contains(resident4));
		assertFalse(result.contains(resident5));
	}
	
	@Test
	public void getFilteredResidentsListTest3() {
		Resident filter = new Resident("*", "", "", "", new Date());
		List<Resident> result = testBaseResidentService.getFilteredResidentsList(filter);
		
		assertTrue(result.contains(resident1));
		assertTrue(result.contains(resident2));
		assertTrue(result.contains(resident3));
		assertTrue(result.contains(resident4));
		assertTrue(result.contains(resident5));
	}
	
	//@Test
	public void getFilteredResidentsListTest4() {
		Resident filter = new Resident("", "", "", "", new Date());
		List<Resident> result = testBaseResidentService.getFilteredResidentsList(filter);
		
		assertFalse(result.contains(resident1));
		assertFalse(result.contains(resident2));
		assertFalse(result.contains(resident3));
		assertFalse(result.contains(resident4));
		assertFalse(result.contains(resident5));
	}
	
	@Test
	public void getUniqueResidentTest() throws ResidentServiceException {
		Resident filter = new Resident("Tom", "", "", "", new Date());
		Resident result = testBaseResidentService.getUniqueResident(filter);
		
		assertEquals(result, resident1);
	}
	
	@Test
	public void getUniqueResidentTest2() throws ResidentServiceException {
		Resident filter = new Resident("Tim", "", "", "", new Date());
		Resident result = testBaseResidentService.getUniqueResident(filter);
		
		assertEquals(result, resident2);
	}

	@Test(expected=ResidentServiceException.class, timeout=1000)
	public void getUniqueResidentTest3() throws ResidentServiceException {
		Resident filter = new Resident("", "Mueller", "", "", new Date());
		testBaseResidentService.getUniqueResident(filter);
	}
}
