package ws_rest_tse.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ws_rest_tse.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
public class JoueurControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JoueurRepository joueurRepository;

	@Test
	public void testAll() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/Joueurs")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.length()", is(5)));
		}
	
	@Test
	public void testOne() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/Joueurs/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.name", is("Stéphane Ruffier")))
			    .andExpect(jsonPath("$.role", is("Gardien de but")))
			    .andExpect(jsonPath("$.team", is("Association sportive de Saint-Étienne")));
	}
	
	@Test
	public void testNewJoueur() throws Exception {
		
		Joueur joueur = new Joueur("Rémy Girodon", "Coach", "Association sportive de Saint-Étienne");
		
		ObjectMapper mapper = new ObjectMapper();
        byte[] joueurAsBytes = mapper.writeValueAsBytes(joueur);
		
		mvc.perform(
				MockMvcRequestBuilders
				.post("/Joueurs")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(joueurAsBytes))
				.andExpect(status().isOk());
		
		assertEquals(6, joueurRepository.count());
		
		Collection<Joueur> Joueurs = joueurRepository.findAll();
		
		boolean found = false;
		
		for (Joueur currentJoueur : Joueurs) {
			
			if (currentJoueur.getName().equals("Rémy Girodon")) {
				
				found = true;
				
				joueurRepository.delete(currentJoueur);
			}
		}
		
		assertTrue(found);
	}
	
	@Test
	public void testDeleteJoueur() throws Exception {
		
		Joueur joueur = new Joueur("Rémy Girodon", "Coach", "Association sportive de Saint-Étienne");
		
		joueurRepository.save(joueur);
		
		Collection<Joueur> Joueurs = joueurRepository.findAll();
		
		Long id = 0L;
		
		for (Joueur currentEmployee : Joueurs) {
			
			if (currentEmployee.getName().equals("Rémy Girodon")) {
				
				id = currentEmployee.getId();
			}
		}
		
		mvc.perform(
				MockMvcRequestBuilders
				.delete("/Joueurs/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		assertEquals(5, joueurRepository.count());
	}
	
	@Test
	public void testReplaceJoueur() throws Exception {
		
		Joueur joueur = new Joueur("Wesley Fofana", "Milieu", "Association sportive de Saint-Étienne");
		
		ObjectMapper mapper = new ObjectMapper();
        byte[] employeeAsBytes = mapper.writeValueAsBytes(joueur);
        
        mvc.perform(
				MockMvcRequestBuilders
				.put("/Joueurs/2")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(employeeAsBytes))
				.andExpect(status().isOk());
        
        joueur = joueurRepository.findById(2L).orElse(null);
        
        if (joueur == null) {
        	
        	fail("Joueur not found");
        }
        
        assertEquals("Milieu", joueur.getRole());
	}
}
