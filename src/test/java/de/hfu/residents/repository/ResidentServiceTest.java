package de.hfu.residents.repository;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResidentServiceTest {
	private ResidentRepository mockResidentRepository;
	private BaseResidentService testBaseResidentService;
	List<Resident> residentList;
	Resident resident1;
	Resident resident2;
	Resident resident3;
	Resident resident4;
	Resident resident5;
	Resident resident6;

	@Before
	public void initializeResidentRepository() {
		mockResidentRepository = createMock(ResidentRepository.class);
		resident1 = new Resident("Tom", "Mueller", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident2 = new Resident("Tim", "Mueller", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident3 = new Resident("Tomas", "Mueller", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident4 = new Resident("Erika", "Musterfrau", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		resident5 = new Resident("Erika2", "Musterfrau", "Bahnhofsstrasse 8", "Furtwangen", new Date());
		residentList = new ArrayList<Resident>();
		residentList.add(resident1);
		residentList.add(resident2);
		residentList.add(resident3);
		residentList.add(resident4);
		residentList.add(resident5);
		expect(mockResidentRepository.getResidents()).andStubReturn(residentList);
		replay(mockResidentRepository);
		
		testBaseResidentService = new BaseResidentService();
		testBaseResidentService.setResidentRepository(mockResidentRepository);
	}
	
	@Test
	public void getFilteredResidentsListTest() {
		Resident filter1 = new Resident("Tom", "", "", "", new Date());
		List<Resident> result = testBaseResidentService.getFilteredResidentsList(filter1);
		
		result = testBaseResidentService.getFilteredResidentsList(filter1);
		verify(mockResidentRepository);
		
		assertThat(result, hasItem(resident1));
		
		assertThat(result, not(hasItem(resident2)));
		assertThat(result, not(hasItem(resident3)));
		assertThat(result, not(hasItem(resident4)));
		assertThat(result, not(hasItem(resident5)));
	}
	
	
	@Test
	public void getFilteredResidentsListTest2() {
		Resident filter1 = new Resident("", "Muster*", "", "", new Date());
		List<Resident> result = testBaseResidentService.getFilteredResidentsList(filter1);
		
		result = testBaseResidentService.getFilteredResidentsList(filter1);
		verify(mockResidentRepository);
		
		
		assertThat(result, hasItem(resident4));
		assertThat(result, hasItem(resident5));
		
		assertThat(result, not(hasItem(resident1)));
		assertThat(result, not(hasItem(resident2)));
		assertThat(result, not(hasItem(resident3)));
	}

}
