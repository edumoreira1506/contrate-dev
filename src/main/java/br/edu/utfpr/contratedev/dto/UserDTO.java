package br.edu.utfpr.contratedev.dto;

public class UserDTO {
	private String name;
	private String language;
	private String description;
	private String email;
	private String cellphone;
	private String password;
	private char gender;

	public UserDTO() {
	}

	public UserDTO(
		String name,
		String language,
		String description,
		String email,
		String cellphone,
		String password,
		char gender
	) {
		super();
		this.name = name;
		this.language = language;
		this.description = description;
		this.email = email;
		this.cellphone = cellphone;
		this.password = password;
		this.gender = gender;
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
}
