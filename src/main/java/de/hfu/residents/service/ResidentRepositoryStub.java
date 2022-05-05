package de.hfu.residents.service;

import java.util.ArrayList;
import java.util.List;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository {
	
	private List<Resident> residents;
	
	public ResidentRepositoryStub() {
		this.residents = new ArrayList<Resident>();
	}
	
	public void addResident(Resident resident) {
		this.residents.add(resident);
	}

	@Override
	public List<Resident> getResidents() {
		return residents;
	}
}
