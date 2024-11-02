package com.example.tm_rentacar.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
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

import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserService userService;

//index-----------------------------------------------------------
	@Test
	public void 未ログインの場合は会員ページからログインページにリダイレクト() throws Exception {
		mockMvc.perform(get("/user"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void ログイン済みの場合は会員ページが正しく表示される() throws Exception {
		mockMvc.perform(get("/user"))
				.andExpect(status().isOk())
				.andExpect(view().name("user/index"));
		
	}

//edit-----------------------------------------------------------
	@Test
	public void 未ログインの場合は会員編集ページからログインページにリダイレクト() throws Exception {
		mockMvc.perform(get("/user/edit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void ログイン済みの場合は会員編集ページが正しく表示される() throws Exception {
		mockMvc.perform(get("/user/edit"))
				.andExpect(status().isOk())
				.andExpect(view().name("user/edit"));
		
	}
//update-----------------------------------------------------------
	@Test
	public void 未ログインの場合は会員情報を更新せずにログインページにリダイレクトする() throws Exception {
		mockMvc.perform(post("/user/update").with(csrf())
				.param("name", "テスト氏名")
				.param("furigana", "テストフリガナ")
				.param("postalCode", "000-0000")
				.param("address", "テスト住所")
				.param("phoneNumber", "000-000-000")
				.param("email", "test@example.com")
				.param("licenseNumber", "000000000000"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));

	}
	@Test
	@WithUserDetails("test@test.com")
	@Transactional
	public void ログイン済みの場合は会員情報更新後に会員詳細ページにリダイレクトする() throws Exception {
		User user = userService.findUserByEmail("test@test.com");
		
		mockMvc.perform(post("/user/update").with(csrf())
				.param("name", "テスト氏名")
				.param("furigana", "テストフリガナ")
				.param("postalCode", "000-0000")
				.param("address", "テスト住所")
				.param("phoneNumber", "000-000-000")
				.param("email", "test@example.com")
				.param("licenseNumber", "000000000000"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/user"));
		
		assertThat(user.getName()).isEqualTo("テスト氏名");
		assertThat(user.getFurigana()).isEqualTo("テストフリガナ");
		assertThat(user.getPostalCode()).isEqualTo("000-0000");
		assertThat(user.getAddress()).isEqualTo("テスト住所");
		assertThat(user.getPhoneNumber()).isEqualTo("000-000-000");
		assertThat(user.getEmail()).isEqualTo("test@example.com");
		assertThat(user.getLicenseNumber()).isEqualTo("000000000000");
	}
	
}
