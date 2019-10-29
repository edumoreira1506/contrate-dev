package br.edu.utfpr.contratedev.dto;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.edu.utfpr.contratedev.model.domain.Company;
import br.edu.utfpr.contratedev.model.domain.User;

public class JobDTO {
	private Long id;
    private String name;
    private Long salary;
    private String description;
    private Set<User> candidates;
    private Company company;
    
    public JobDTO(String name, Long salary, String description, Set<User> candidates, Company company) {
		this.name = name;
		this.salary = salary;
		this.description = description;
		this.candidates = candidates;
		this.company = company;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSalary() {
		return salary;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<User> candidates) {
		this.candidates = candidates;
	}

	public Long getId() {
		return id;
	}
}
