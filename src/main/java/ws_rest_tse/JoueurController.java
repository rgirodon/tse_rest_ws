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
public class JoueurController {

	private final JoueurRepository repository;

  
	JoueurController(JoueurRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/Joueurs")
	List<Joueur> all() {
		return repository.findAll();
	}
	
	@PostMapping("/Joueurs")
	Joueur newEmployee(@RequestBody Joueur newJoueur) {
	    return repository.save(newJoueur);
	}
	
	@GetMapping("/Joueurs/{id}")
	Joueur one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "joueur"));
	}
	
	@DeleteMapping("/Joueurs/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/Joueurs/{id}")
	Joueur replaceEmployee(@RequestBody Joueur newJoueur, @PathVariable Long id) {

		return repository.findById(id)
	      .map(joueur -> {
	        joueur.setName(newJoueur.getName());
	        joueur.setRole(newJoueur.getRole());
	        return repository.save(joueur);
	      })
	      .orElseThrow(() -> new ObjectNotFoundException(id, "joueur"));
	}
}
