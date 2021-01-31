package ws_rest_tse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Joueur {

	private @Id @GeneratedValue Long id;

	private String name;
  
	private String role;
	
	private String team;
  
	Joueur() {		
	}

	public Joueur(String name, String role, String team) {
		this.name = name;
		this.role = role;
		this.team = team;
	}

	public String getName() {
		return this.name;
	}

	public String getRole() {
		return this.role;
	}

	public void setName(String name2) {
		this.name = name2;
		
	}

	public void setRole(String role2) {
		this.role = role2;
	}
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}
