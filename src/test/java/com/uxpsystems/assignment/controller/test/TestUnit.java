package com.uxpsystems.assignment.controller.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignment.controller.AssignmentController;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(AssignmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUnit {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	UserService service;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before()
	public void setup() { 
		// Init MockMvc Object and build mockMvc 
		MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Before
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void firstpostUser() throws Exception {

		service = Mockito.spy(service);
		// Mockito.doAnswer(service.postUser(Mockito.any(User.class))).thenReturn("saved");
		Mockito.doNothing().when(service).postUser(Mockito.any(User.class));
		String requestBody = "{\"id\":\"1\",\"username\":\"mayank\",\"password\":\"upes\",\"status\":\"Activated\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/assignement/postUser").characterEncoding("utf-8")

				.content(requestBody).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isCreated());

	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void secondgetUser() throws Exception {
		// Mockito.when(service.getUser(1)).thenReturn(postData());
		// service = Mockito.spy(service);
		Mockito.when(service.getUser(1)).thenReturn(postData());
		// Mockito.when(service).getUser(Mockito.eq(1));
		String requestBody = "{\"id\":\"1\",\"username\":\"mayank\",\"password\":\"upes\",\"status\":\"Activated\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/assignement/getUser/{id}", 1);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void thirdputUser() throws Exception {

		Mockito.when(service.putUser(Mockito.any(User.class), Mockito.eq(1))).thenReturn("saved");

		String requestBody = "{\"id\":\"1\",\"username\":\"mayank\",\"password\":\"upes\",\"status\":\"Activated\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/assignement/putUser/{id}", 1).characterEncoding("utf-8")

				.content(requestBody).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void zenithdeleteUser() throws Exception {

		Mockito.when(service.deleteUser(Mockito.eq(1))).thenReturn("Y");

		String requestBody = "{\"id\":\"1\",\"username\":\"mayank\",\"password\":\"upes\",\"status\":\"Activated\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/assignement/deleteUser/{id}", 1);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	User postData()

	{
		User u = new User();
		u.setId(1);
		u.setUsername("demo");
		u.setPassword("password");
		u.setStatus("Activated");
		return u;
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void secondNegativegetUser() throws Exception {
		Mockito.when(service.getUser(1)).thenReturn(postData());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/assignement/getUser/{id}", 2);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());

	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void thirdNegativeputUser() throws Exception {

		Mockito.when(service.putUser(Mockito.any(User.class), Mockito.eq(2))).thenReturn("fail");

		String requestBody = "{\"id\":\"2\",\"username\":\"mayank\",\"password\":\"upes\",\"status\":\"Activated\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/assignement/putUser/{id}", 2).characterEncoding("utf-8")

				.content(requestBody).contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());

	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void zenithNegativedeleteUser() throws Exception {

		Mockito.when(service.deleteUser(Mockito.eq(1))).thenReturn("N");

		String requestBody = "{\"id\":\"2\",\"username\":\"mayank\",\"password\":\"upes\",\"status\":\"Activated\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/assignement/deleteUser/{id}", 2);
		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());

	}

	
	
	
	
}
