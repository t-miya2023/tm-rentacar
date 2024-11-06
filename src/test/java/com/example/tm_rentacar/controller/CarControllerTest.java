package com.example.tm_rentacar.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
//index---------------------------------------------------------------------------
	@Test
	public void 未ログイン状態でも車両一覧ページが正しく表示される() throws Exception{
		mockMvc.perform(get("/cars"))
				.andExpect(status().isOk())
				.andExpect(view().name("cars/index"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void ログイン済みでも車両一覧ページが正しく表示される() throws Exception{
		mockMvc.perform(get("/cars"))
				.andExpect(status().isOk())
				.andExpect(view().name("cars/index"));
	}
//show---------------------------------------------------------------------------
		@Test
		public void 未ログイン状態でも車両詳細ページが正しく表示される() throws Exception{
			mockMvc.perform(get("/cars/1"))
					.andExpect(status().isOk())
					.andExpect(view().name("cars/show"));
		}
		
		@Test
		@WithUserDetails("testuser@test.com")
		public void ログイン済みでも車両詳細ページが正しく表示される() throws Exception{
			mockMvc.perform(get("/cars/1"))
					.andExpect(status().isOk())
					.andExpect(view().name("cars/show"));
		}
}
