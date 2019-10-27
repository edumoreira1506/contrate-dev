package br.edu.utfpr.contratedev.dto;

public class CompanyDTO {
	private UserDTO manager;
	private Long id;
	private String name;
	private String description;
	
	public CompanyDTO(UserDTO manager, String name, String description) {
		super();
		this.manager = manager;
		this.name = name;
		this.description = description;
	}
	
	public CompanyDTO(UserDTO manager, Long id, String name, String description) {
		super();
		this.manager = manager;
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public UserDTO getManager() {
		return manager;
	}
	
	public void setManager(UserDTO manager) {
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

	public Long getId() {
		return id;
	}
}
