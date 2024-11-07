package com.example.tm_rentacar.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotNull(message = "利用開始日時を選択してください。")
	@DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
	private LocalDateTime startDate;
	
	@NotNull(message = "利用終了日時を選択してください。")
	@DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm")
	private LocalDateTime endDate;
}
