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
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AdminCarControllerTest {
	@Autowired
	private MockMvc mockMvc;
//index---------------------------------------------------------------------------
	@Test
	public void 未ログインの場合はログインページにリダイレクトする() throws Exception{
		mockMvc.perform(get("/admin/cars"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void 一般ユーザーが管理者用車両一覧ページにアクセスした場合は403エラーが発生する() throws Exception{
		mockMvc.perform(get("/admin/cars"))
				.andExpect(status().isForbidden());
	}
	
	@Test
	@Transactional
	@WithUserDetails("test@test.com")
	public void 管理者が管理者用車両一覧ページにアクセスした場合は正しく表示される() throws Exception {
		mockMvc.perform(get("/admin/cars"))
				.andExpect(status().isOk())
				.andExpect(view().name("admin/cars/index"));

	}
	
}
