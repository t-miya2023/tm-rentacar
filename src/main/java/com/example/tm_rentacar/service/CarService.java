package com.example.tm_rentacar.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.entity.CarImage;
import com.example.tm_rentacar.form.CarRegisterForm;
import com.example.tm_rentacar.repository.CarImageRepository;
import com.example.tm_rentacar.repository.CarRepository;

@Service
public class CarService {
	private final CarRepository carRepository;
	private final CarImageRepository carImageRepository;
	
	public CarService(CarRepository carRepository, CarImageRepository carImageRepository) {
		this.carRepository = carRepository;
		this.carImageRepository = carImageRepository;
	}
	
	//全ての車を取得する(ページネーションありで)
	public Page<Car> findAllCars(Pageable pageable) {
		return carRepository.findAll(pageable);
	}
	
	//指定されたキーワードの車種名を取得するメソッド
	public Page<Car> findCarByModelLike(String keyword, Pageable pageable) {
		return carRepository.findByModelLike("%" + keyword + "%", pageable);
	}
	
	//指定したIDを持つ車を取得するメソッド
	public Optional<Car> findCarById(Integer id){
		return carRepository.findById(id);
	}
	
	//Carのレコード数を取得する
	public long countCars() {
		return carRepository.count();
	}
	
	//idの大きなCarを取得する
	public Car findFirstCarByOrderByIdDesc() {
		return carRepository.findFirstByOrderByIdDesc();
	}
	
	
	//Createメソッド
	@Transactional
	public void createCar(CarRegisterForm carRegisterForm) {
		Car car = new Car();

		
		car.setMake(carRegisterForm.getMake());
		car.setModel(carRegisterForm.getModel());
		car.setYear(carRegisterForm.getYear());
		car.setLicensePlate(carRegisterForm.getLicensePlate());
		car.setType(carRegisterForm.getType());
		car.setRentalRate(carRegisterForm.getRentalRate());
		car.setStatus(carRegisterForm.getStatus());
		
		carRepository.save(car);
	
	//画像ファイルがある場合は追加で処理	
		
		List<MultipartFile> imageFiles = carRegisterForm.getImageFiles();
	
		if(!imageFiles.isEmpty()) {
			for(MultipartFile imageFile : imageFiles) {
				String imageName = imageFile.getOriginalFilename();
				String hashedImageName = generateNewFileName(imageName);
				Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
				copyImageFile(imageFile, filePath);
				
				CarImage carImage = new CarImage();
				carImage.setCar(car);
				carImage.setImageUrl(hashedImageName);
				
				carImageRepository.save(carImage);
			}
		}
	}
	
	//UUIDを使って生成したファイル名を返す(同一ファイルの上書きを防ぐ)
	public String generateNewFileName(String fileName) {
		String[] fileNames = fileName.split("\\.");
		
		for(int i = 0; i < fileNames.length - 1; i++) {
			fileNames[i] = UUID.randomUUID().toString();
		}
		String hashedFileName = String.join(".", fileNames);
		
		return hashedFileName;
	}
	
	//画像ファイルを指定したファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		try {
			Files.copy(imageFile.getInputStream(), filePath);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
