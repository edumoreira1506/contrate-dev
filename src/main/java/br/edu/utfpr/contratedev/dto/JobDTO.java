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
    private String salary;
    private String description;
    private Set<User> candidates;
    private Company company;
    
    public JobDTO(String name, String salary, String description, Set<User> candidates, Company company) {
		this.name = name;
		this.salary = salary;
		this.description = description;
		this.candidates = candidates;
		this.company = company;
	}    
    
    public JobDTO(String name, String salary, String description, Set<User> candidates, Company company, Long id) {
		this.name = name;
		this.salary = salary;
		this.description = description;
		this.candidates = candidates;
		this.company = company;
		this.id = id;
	}
    
    public JobDTO(String name, Long salary, String description, Set<User> candidates, Company company, Long id) {
		this.name = name;
		this.salary = brazilianSalary(salary);
		this.description = description;
		this.candidates = candidates;
		this.company = company;
		this.id = id;
	}
    
    public String brazilianSalary(Long salary) {
    	salary = salary / 100;
    	String stringSalary = salary.toString();
    	if (stringSalary.contains(".")) {
    		String[] arraySalary = stringSalary.split(".");
        	return "R$" + arraySalary[0] + "," + arraySalary[1];	
    	}
    	
    	return "R$" + stringSalary + ",00";
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return company;
	}

	public void setSalary(String salary) {
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
	
	public void setId(Long id) {
		this.id = id;
	}
}
