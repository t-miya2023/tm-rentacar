package com.example.tm_rentacar.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.entity.CarImage;
import com.example.tm_rentacar.repository.CarImageRepository;

@Service
public class CarImageService {
	private final CarImageRepository carImageRepository;
	
	public CarImageService(CarImageRepository carImageRepository) {
		this.carImageRepository = carImageRepository;
	}
	
	//画像登録メソッド
	public void saveCarImages(Car car, List<MultipartFile> imageFiles){
		if(!imageFiles.isEmpty()) {
			
			List<String> existImageUrls = carImageRepository.findImageUrlsByCarId(car.getId()); 
			
			for(MultipartFile imageFile : imageFiles) {
				String imageName = imageFile.getOriginalFilename();
				String hashedImageName = generateNewFileName(imageName);
				Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
				
				//既に同じ画像が登録されていなければ
				if(!existImageUrls.contains(hashedImageName) && !hashedImageName.isEmpty()) {
					copyImageFile(imageFile, filePath);
					
					CarImage carImage = new CarImage();
					carImage.setCar(car);
					carImage.setImageUrl(hashedImageName);
					
					carImageRepository.save(carImage);
				}
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
	
	//画像をIDから削除するメソッド
	public void deleteCarImageById(Integer id) {
		carImageRepository.deleteById(id);
	}
	
	//画像をIDから検索するメソッド
	public Optional<CarImage> findCarImageById(Integer id) {
		return carImageRepository.findById(id);
	}
}
