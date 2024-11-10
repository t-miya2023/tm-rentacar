package com.example.tm_rentacar.controller;

//import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tm_rentacar.dto.ReservationDto;
//import com.example.tm_rentacar.entity.Reservation;
//import com.example.tm_rentacar.service.ReservationService;
//
//import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReservationControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
//	@Autowired
//	private ReservationService reservationService;
	
	@Test
	public void 未ログインの場合は会員用の予約一覧ページからログインページにリダイレクトされる() throws Exception{
		mockMvc.perform(get("/reservations"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void ログイン済みの場合は会員用の予約一覧ページが正しく表示される() throws Exception{
		mockMvc.perform(get("/reservations"))
				.andExpect(status().isOk())
				.andExpect(view().name("reservations/index"));
	}
	
//input------------------------------------------------------------
	
	@Test
	public void 未ログインの場合は予約フォームを送信せずにログインページにリダイレクト() throws Exception{
		mockMvc.perform(post("/cars/1/reservations/input").with(csrf())
					.param("startDate", "2024-01-01T00:00")
					.param("endDate","2024-01-02T00:00"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void ログイン済みの場合は予約フォーム送信後に確認ページへリダイレクト() throws Exception{
		mockMvc.perform(post("/cars/1/reservations/input").with(csrf())
				.param("startDate", "2024-01-01T00:00")
				.param("endDate","2024-01-02T00:00"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/reservations/confirm"));
	}
	
//confirm-----------------------------------------------------------
	
	@Test
	public void 未ログインの場合は予約内容確認ページからログインページにリダイレクトする() throws Exception{
		//セッションを作成しReservationDtoオブジェクトを保存する
		MockHttpSession mockHttpSession = new MockHttpSession();
		ReservationDto reservationDto = new ReservationDto(1, LocalDateTime.parse("2024-01-01T00:00"), LocalDateTime.parse("2024-01-02T00:00"), 10000);
		mockHttpSession.setAttribute("reservationDto", reservationDto);
		//セッションを利用してHTTPリクエストを送信する
		mockMvc.perform(get("/reservations/confirm").session(mockHttpSession))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("testuser@test.com")
	public void ログイン済みの場合は予約内容の確認ページが正しく表示される() throws Exception{
		//セッションを作成しReservationDtoオブジェクトを保存する
		MockHttpSession mockHttpSession = new MockHttpSession();
		ReservationDto reservationDto = new ReservationDto(1, LocalDateTime.parse("2024-01-01T00:00"), LocalDateTime.parse("2024-01-02T00:00"), 10000);
		mockHttpSession.setAttribute("reservationDto", reservationDto);
		//セッションを利用してHTTPリクエストを送信する
		mockMvc.perform(get("/reservations/confirm").session(mockHttpSession))
				.andExpect(status().isOk())
				.andExpect(view().name("reservations/confirm"));
	}
	
//create-----------------------------------------------------------
//	
//	@Test
//	@Transactional
//	public void  未ログインの場合は予約をせずにログインページへリダイレクト() throws Exception{
//		long countBefore = reservationService.countReservations();
//		
//		MockHttpSession mockHttpSession = new MockHttpSession();
//		ReservationDto reservationDto = new ReservationDto(1, LocalDateTime.parse("2024-01-01T00:00"), LocalDateTime.parse("2024-01-02T00:00"), 10000);
//		mockHttpSession.setAttribute("reservationDto", reservationDto);
//		
//		mockMvc.perform(post("/reservations/create").session(mockHttpSession).with(csrf()))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(redirectedUrl("http://localhost/login"));
//		
//		long countAfter = reservationService.countReservations();
//		//レコード数に変化がないことをチェック
//		assertThat(countAfter).isEqualTo(countBefore);
//		
//	}
//	
//	@Test
//	@WithUserDetails("testuser@test.com")
//	@Transactional
//	public void ログイン済みであれば予約後に予約一覧ページへリダイレクト() throws Exception{
//		long countBefore = reservationService.countReservations();
//		
//		MockHttpSession mockHttpSession = new MockHttpSession();
//		ReservationDto reservationDto = new ReservationDto(1, LocalDateTime.parse("2024-01-01T00:00"), LocalDateTime.parse("2024-01-02T00:00"), 10000);
//		mockHttpSession.setAttribute("reservationDto", reservationDto);
//		
//		mockMvc.perform(post("/reservations/create").session(mockHttpSession).with(csrf()))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(redirectedUrl("/reservations?reserved"));
//		
//		long countAfter = reservationService.countReservations();
//		//レコード数が１増えていることをチェック
//		assertThat(countAfter).isEqualTo(countBefore + 1);
//		
//		//レコード内容をチェック
//		Reservation reservation = reservationService.findFirstReservationByOrderByIdDesc();
//	
//		assertThat(reservation.getCar().getId()).isEqualTo(1);
//		assertThat(reservation.getUser().getEmail()).isEqualTo("testuser@test.com");
//		assertThat(reservation.getStartDate().isEqual(LocalDateTime.parse("2024-01-01T00:00")));
//		assertThat(reservation.getEndDate().isEqual(LocalDateTime.parse("2024-01-02T00:00")));
//		assertThat(reservation.getAmount()).isEqualTo(10000);
//	}
//	
	
}
