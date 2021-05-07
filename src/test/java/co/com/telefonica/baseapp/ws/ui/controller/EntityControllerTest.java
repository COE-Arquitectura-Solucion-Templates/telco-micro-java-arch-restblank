package co.com.telefonica.baseapp.ws.ui.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.com.telefonica.baseapp.ws.ui.model.request.EntityDetailsRequestModel;
import co.com.telefonica.baseapp.ws.ui.model.request.UpdateEntityDetailsRequestModel;
import co.com.telefonica.baseapp.ws.ui.model.response.RestEntity;



@ExtendWith(SpringExtension.class)
@WebMvcTest(EntityController.class)
class EntityControllerTest {
	
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@BeforeEach
	void setUp() throws Exception {		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetParameter() throws Exception {
		EntityDetailsRequestModel entityDetails = new EntityDetailsRequestModel();
		entityDetails.setParameter1("Test 0");
		entityDetails.setParameter2("test@test0.com");
		entityDetails.setParameter3("test@test0.com");
		entityDetails.setParameter4("test@test0.com");		
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/operacion")
		.content(new ObjectMapper().writeValueAsString(entityDetails))
		.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro1").value("Test 0"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro2").value("test@test0.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro3").value("test@test0.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro4").isNotEmpty());		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		RestEntity response = objectMapper.readValue(contentAsString, RestEntity.class);
		String entityId = response.getParametro4();		
		mvc.perform(MockMvcRequestBuilders.get("/operacion/"+entityId)
		.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro1").value("Test 0"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro2").value("test@test0.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro3").value("test@test0.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro4").value(entityId));	
	}

	@Test
	void testGetParametros() throws Exception {		
		mvc.perform(MockMvcRequestBuilders.get("/operacion?page=8&limit=6"))
		.andDo(print())
		.andExpect(content().string("get parametros called was called page=8 and limit=6 and sort=null"))
		.andExpect(status().isOk());
	}


	@Test
	void testGetEntity() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/operacion/all"))
		.andDo(print())
		.andExpect(content().string("get entity called was called"))
		.andExpect(status().isOk());
	}

	@Test
	void testCreateEntity() throws Exception{		
		
		EntityDetailsRequestModel entityDetails = new EntityDetailsRequestModel();
		entityDetails.setParameter1("Test 1");
		entityDetails.setParameter2("test@test1.com");
		entityDetails.setParameter3("test@test1.com");
		entityDetails.setParameter4("test@test1.com");		
		mvc.perform(MockMvcRequestBuilders.post("/operacion")
		.content(new ObjectMapper().writeValueAsString(entityDetails))
		.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro1").value("Test 1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro2").value("test@test1.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro3").value("test@test1.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro4").isNotEmpty());		
	}

	@Test
	void testUpdateEntity() throws Exception {
		UpdateEntityDetailsRequestModel entityDetailsUp = new UpdateEntityDetailsRequestModel();
		EntityDetailsRequestModel entityDetails = new EntityDetailsRequestModel();
		entityDetails.setParameter1("Test 2");
		entityDetails.setParameter2("test@test2.com");
		entityDetails.setParameter3("test@test2.com");
		entityDetails.setParameter4("test@test2.com");		
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/operacion")
		.content(new ObjectMapper().writeValueAsString(entityDetails))
		.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro1").value("Test 2"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro2").value("test@test2.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro3").value("test@test2.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro4").isNotEmpty());	
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		RestEntity response = objectMapper.readValue(contentAsString, RestEntity.class);
		String entityId = response.getParametro4();

		entityDetailsUp.setParametro1("Test 3");
		entityDetailsUp.setParametro2("test@test3.com");
		mvc.perform(MockMvcRequestBuilders.put("/operacion/"+entityId)
		.content(new ObjectMapper().writeValueAsString(entityDetailsUp))
		.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro1").value("Test 3"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro2").value("test@test3.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro3").value("test@test2.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro4").value(entityId));			
	}

	@Test
	void testDeleteParameter() throws Exception {
		EntityDetailsRequestModel entityDetails = new EntityDetailsRequestModel();
		entityDetails.setParameter1("Test 4");
		entityDetails.setParameter2("test@test4.com");
		entityDetails.setParameter3("test@test4.com");
		entityDetails.setParameter4("test@test4.com");	
		
		ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/operacion")
		.content(new ObjectMapper().writeValueAsString(entityDetails))
		.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro1").value("Test 4"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro2").value("test@test4.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro3").value("test@test4.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro4").isNotEmpty());	
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		RestEntity response = objectMapper.readValue(contentAsString, RestEntity.class);
		String entityId = response.getParametro4();
		mvc.perform(MockMvcRequestBuilders.delete("/operacion/"+entityId)
		.accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro1").value("Test 4"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro2").value("test@test4.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro3").value("test@test4.com"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.parametro4").value(entityId));		
	}

}
