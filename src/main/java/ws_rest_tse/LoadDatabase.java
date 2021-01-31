package ws_rest_tse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	  
	@Bean
	CommandLineRunner initDatabase(JoueurRepository joueurRepository, EquipeRepository equipeRepository) {
		return args -> {
						
			log.info("Preloading " + joueurRepository.save(new Joueur("Stéphane Ruffier", "Gardien de but","Association sportive de Saint-Étienne")));
			log.info("Preloading " + joueurRepository.save(new Joueur("Wesley Fofana", "Défenseur","Association sportive de Saint-Étienne")));
			log.info("Preloading " + joueurRepository.save(new Joueur("Romain Hamouma", "Attaquant","Association sportive de Saint-Étienne")));
			log.info("Preloading " + joueurRepository.save(new Joueur("Miguel Trauco", "Défenseur","Association sportive de Saint-Étienne")));

			log.info("Preloading " + joueurRepository.save(new Joueur("Anthony Lopes", "Gardien de but","Olympique lyonnais")));
			
			log.info("Preloading " + equipeRepository.save(new Equipe("Association sportive de Saint-Étienne","Ligue 1")));
			log.info("Preloading " + equipeRepository.save(new Equipe("Olympique lyonnais","Ligue 1")));
		};
	}
}


