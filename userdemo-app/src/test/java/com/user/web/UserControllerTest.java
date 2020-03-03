package com.user.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.user.UserDemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserDemoApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void getAllUsersList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}

	@Test
	public void getUserUIById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/user/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.age").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.login").value("DemoUser@test.com"))
		.andExpect(jsonPath("$.password").value("spring"))
		.andDo(print());
	}
	
	@Test
	public void testInvalidValueForCall() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/user/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("Error my be due to malformed request."))
			.andDo(print());
	}

	
	@Test
	public void verifyInvalidUserUIId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/user/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("- An error has occur while trying to get user by id:0"))
		.andDo(print());
	}
	
	@Test
	public void verifyUserUIOfNullValue() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/user/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("- An error has occur while trying to get user by id:6"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteUserUI() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/user/2?applicationId=Secret").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()); 
	}

	@Test
	public void testSaveUserUI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/registrateuser")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("{\"name\":\"DemoUser\",\"age\":23,\"country\":\"France\",\"email\":\"DemoUser@test.com\",\"login\":\"DemoUser@test.com\",\"password\":\"spring\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andDo(print());
	}

	@Test
	public void testAnWrongSave() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/registrateuser")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"DemoUser\",\"age\":4,\"country\":\"France\",\"email\":\"DemoUser@test.com\",\"login\":\"DemoUser@test.com\",\"password\":\"spring\"}")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(400))
		.andExpect(jsonPath("$.message").value("Error my be due to malformed request."))
		.andDo(print());
	}
	

}
