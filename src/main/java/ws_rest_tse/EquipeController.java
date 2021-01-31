package ws_rest_tse;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipeController {

	private final EquipeRepository repository;

  
	EquipeController(EquipeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/Equipes")
	List<Equipe> all() {
		return repository.findAll();
	}
	
	@PostMapping("/Equipes")
	Equipe newEmployee(@RequestBody Equipe newEquipe) {
	    return repository.save(newEquipe);
	}
	
	@GetMapping("/Equipes/{id}")
	Equipe one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Equipe"));
	}
	
	@DeleteMapping("/Equipes/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/Equipes/{id}")
	Equipe replaceEmployee(@RequestBody Equipe newEquipe, @PathVariable Long id) {

		return repository.findById(id)
	      .map(equipe -> {
	        equipe.setName(newEquipe.getName());
	        equipe.setCompetition(newEquipe.getCompetition());
	        return repository.save(equipe);
	      })
	      .orElseThrow(() -> new ObjectNotFoundException(id, "Equipe"));
	}
}
