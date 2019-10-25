package br.edu.utfpr.contratedev.model.domain;

import br.edu.utfpr.contratedev.util.Sha256Generator;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "email", length = 200)
    private String email;
    
    private String role;
    public Role() {
    }

    public Role(String email, String role) {
        this.email = email;
        this.role = role;
    }    
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
