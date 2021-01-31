package ws_rest_tse.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ws_rest_tse.Equipe;
import ws_rest_tse.EquipeRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
public class EquipeControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private EquipeRepository equipeRepository;
	
	@Test
	public void testAll() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/Equipes")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.length()", is(2)));
		}
	
	@Test
	public void testOne() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/Equipes/7")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.name", is("Olympique lyonnais")))
			    .andExpect(jsonPath("$.competition", is("Ligue 1")));
	}
	
	@Test
	public void testNewEquipe() throws Exception {
		
		Equipe equipe = new Equipe("Paris Saint Germain", "Ligue 1");
		
		ObjectMapper mapper = new ObjectMapper();
        byte[] equipeAsBytes = mapper.writeValueAsBytes(equipe);
		
		mvc.perform(
				MockMvcRequestBuilders
				.post("/Equipes")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(equipeAsBytes))
				.andExpect(status().isOk());
		
		assertEquals(3, equipeRepository.count());
		
		Collection<Equipe> equipes = equipeRepository.findAll();
		
		boolean found = false;
		
		for (Equipe currentEquipe : equipes) {
			
			if (currentEquipe.getName().equals("Paris Saint Germain")) {
				
				found = true;
				
				equipeRepository.delete(currentEquipe);
			}
		}
		
		assertTrue(found);
	}
	
	@Test
	public void testDeleteEquipe() throws Exception {
		
		Equipe equipe = new Equipe("Paris Saint Germain", "Ligue 1");
		
		equipeRepository.save(equipe);
		
		Collection<Equipe> equipes = equipeRepository.findAll();
		
		Long id = 0L;
		
		for (Equipe currentEquipe : equipes) {
			
			if (currentEquipe.getName().equals("Paris Saint Germain")) {
				
				id = currentEquipe.getId();
			}
		}
		
		mvc.perform(
				MockMvcRequestBuilders
				.delete("/Equipes/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		assertEquals(2, equipeRepository.count());
	}
	
	@Test
	public void testReplaceEquipe() throws Exception {
		
		Equipe equipe = new Equipe("Association sportive de Saint-Étienne","Ligue 2");	
		ObjectMapper mapper = new ObjectMapper();
        byte[] employeeAsBytes = mapper.writeValueAsBytes(equipe);
        
        mvc.perform(
				MockMvcRequestBuilders
				.put("/Equipes/6")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(employeeAsBytes))
				.andExpect(status().isOk());
        
        equipe = equipeRepository.findById(6L).orElse(null);
        
        if (equipe == null) {
        	
        	fail("Equipe not found");
        }
        
        assertEquals("Ligue 2", equipe.getCompetition());
        
	}
}
