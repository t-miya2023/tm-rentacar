package com.example.tm_rentacar.form;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.tm_rentacar.enums.CarStatus;
import com.example.tm_rentacar.enums.CarType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarUpdateForm {
	@NotBlank(message = "メーカーを入力してください。")
	private String make;
	
	@NotBlank(message = "車種名を入力してください。")
	private String model;
	
	private String year;
	
	@NotBlank(message = "ナンバープレート番号を入力してください。")
	private String licensePlate;
	
	@NotNull(message = "ボディタイプが正しく取得できませんでした。")
	private CarType type;
	
	@NotNull(message = "金額を入力してください。")
	@Min(value = 1, message = "金額は1円以上に設定してください。")
	private BigDecimal rentalRate;
	
	@NotNull(message = "ステータスが正しく取得できませんでした。")
	private CarStatus status;
	
	private List<MultipartFile> imageFiles;
}
