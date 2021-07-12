package test.java;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import main.java.com.repository.ClientRepo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class ClientControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClientRepo clientRepository;
	
	@Test
	public void test() throws Exception {
		Mockito.when(clientRepository.findAll()).thenReturn(Collections.emptyList());
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/clients")
			.accept(MediaType.APPLICATION_JSON)).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
		Mockito.verify(clientRepository).findAll();
	}

}
