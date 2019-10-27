package br.edu.utfpr.contratedev.model.domain;

import java.util.Set;

import javax.persistence.*;
import br.edu.utfpr.contratedev.util.Sha256Generator;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private String name;
    
    private String description;
    
    @OneToOne
    private User manager;
    
    public Company() {
    }
    
	public Company(User manager, String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.manager = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Long getId() {
		return id;
	}

	@PrePersist
    @PreUpdate
    public void onSave() {

    }
}
