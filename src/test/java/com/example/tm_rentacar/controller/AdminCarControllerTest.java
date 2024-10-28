package com.example.tm_rentacar.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.tm_rentacar.service.CarService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AdminCarControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CarService carService;
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
//show---------------------------------------------------------------------------
	@Test
	public void 未ログインの場合は管理者用車両詳細ページからログインページにリダイレクトする() throws Exception{
		mockMvc.perform(get("/admin/cars/1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void 一般ユーザーが管理者用車両詳細ページにアクセスした場合は403エラーが発生する() throws Exception{
		mockMvc.perform(get("/admin/cars/1"))
				.andExpect(status().isForbidden());
	}
	
	@Test
	@Transactional
	@WithUserDetails("test@test.com")
	public void 管理者が管理者用車両詳細ページにアクセスした場合は正しく表示される() throws Exception{
		mockMvc.perform(get("/admin/cars/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("admin/cars/show"));
	}
	
//register---------------------------------------------------------------------------
		@Test
		public void 未ログインの場合は管理者用車両登録ページからログインページにリダイレクトする() throws Exception{
			mockMvc.perform(get("/admin/cars/register"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("http://localhost/login"));
		}
		
		@Test
		@WithUserDetails("testuser@test.com")
		public void 一般ユーザーが管理者用車両登録ページにアクセスした場合は403エラーが発生する() throws Exception{
			mockMvc.perform(get("/admin/cars/register"))
					.andExpect(status().isForbidden());
		}
		
		@Test
		@Transactional
		@WithUserDetails("test@test.com")
		public void 管理者が管理者用車両登録ページにアクセスした場合は正しく表示される() throws Exception{
			mockMvc.perform(get("/admin/cars/register"))
					.andExpect(status().isOk())
					.andExpect(view().name("admin/cars/register"));
		}
		
//create---------------------------------------------------------------------------

		@Test
		@Transactional
		public void 未ログインの場合は登録はせずにログインページへリダイレクト() throws Exception {
			//テスト前のデータ数
			long countBefore = carService.countCars();
			
			//テスト用の画像ファイルデータ
			Path filePath = Paths.get("src/main/resources/static/storage/test.jpg");
			String fileName = filePath.getFileName().toString();
			String fileType = Files.probeContentType(filePath);
			byte[] fileBytes = Files.readAllBytes(filePath);
			
			MockMultipartFile imageFile = new MockMultipartFile(
					"imageFile",
					fileName, 
					fileType,
					fileBytes
				);
			
			mockMvc.perform(MockMvcRequestBuilders.multipart("admin/cars/create").file(imageFile)
					.with(csrf())
					.param("make", "テストメーカー")
					.param("model", "テストカー")
					.param("year", "2024")
					.param("licensePlate", "品川 あ 12-34")
					.param("type", "セダン")
					.param("rentalRate", "1000")
					.param("status", "利用可"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
			
			//テスト後のレコード数
			long countAfter = carService.countCars();
			//レコード数が変わらないことを検証
			assertThat(countAfter).isEqualTo(countBefore);
		}

}
