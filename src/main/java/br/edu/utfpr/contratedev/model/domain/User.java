package br.edu.utfpr.contratedev.model.domain;

import javax.persistence.*;
import br.edu.utfpr.contratedev.util.Sha256Generator;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email")
	private String email;
    
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;
    
    @Column(name = "language")
	private String language;
    
    @Column(name = "description")
	private String description;
    
    @Column(name = "cellphone")
	private String cellphone;
    
    @Column(name = "gender")
	private char gender;
    
    @Column(name = "github")
    private  String github;
    
    public User() {
    }

    public User(
    	String name,
    	String language,
    	String description,
    	String email,
    	String cellphone,
    	String password,    	
    	char gender,
    	String github
    ) {
    	super();
    	this.name = name;
    	this.language = language;
    	this.description = description;
    	this.email = email;
    	this.cellphone = cellphone;
   		this.password = password;
    	this.gender = gender;
    	this.github = github;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

    @PrePersist
    @PreUpdate
    public void onSave() {

    }
}
