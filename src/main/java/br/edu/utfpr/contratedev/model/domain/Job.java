package br.edu.utfpr.contratedev.model.domain;

import java.util.Set;

import javax.persistence.*;
import br.edu.utfpr.contratedev.util.Sha256Generator;

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
    
    public Job() {
    }

    public Job(String name, Long salary, String description, Set<User> candidates) {
		super();
		this.name = name;
		this.salary = salary;
		this.description = description;
		this.candidates = candidates;
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

	@PrePersist
    @PreUpdate
    public void onSave() {

    }
}
