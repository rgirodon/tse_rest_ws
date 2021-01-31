package ws_rest_tse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Equipe {
	private @Id @GeneratedValue Long id;



	private String name;

	private String competition;

	Equipe() {
		
	}
	
	Equipe(String name, String competition){
		this.name = name;
		this.competition = competition;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
