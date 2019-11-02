package br.edu.utfpr.contratedev.model.domain;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private String name;
    
    private Long salary;
    
    private String description;
    
    @ManyToMany
    private Set<User> candidates;
    
    @ManyToOne
    private Company company;
    
    public Job() {
    }

    public Job(String name, String salary, String description, Set<User> candidates, Company company, Long id) {
		super();
		this.name = name;
		this.salary = this.realSalary(salary);
		this.description = description;
		this.candidates = candidates;
		this.company = company;
		this.id = id;
	}

    public Long realSalary(String salary) {
    	salary = salary.replace(".", "");
    	salary = salary.replace(",", ".");
    	return 100 * Long.parseLong(salary.substring(0, salary.length() - 3));
    }
    
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
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
	
	public void addCandidate(User user) {
    	this.candidates.add(user);
	}
	
	public void removeCandidate(User user) {
		this.candidates.remove(user);
	}

	@PrePersist
    @PreUpdate
    public void onSave() {

    }
}
