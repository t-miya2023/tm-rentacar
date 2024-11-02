package com.example.tm_rentacar.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tm_rentacar.entity.Role;
import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.form.SignupForm;
import com.example.tm_rentacar.repository.RoleRepository;
import com.example.tm_rentacar.repository.UserRepository;

@Service
public class UserService {
//	DI
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	//新規ユーザー登録用メソッド
	@Transactional
	public User createUser(SignupForm signupForm) {
		User user = new User();
		Role role = roleRepository.findByName("ROLE_GENERAL");
		
		user.setName(signupForm.getName());
		user.setFurigana(signupForm.getFurigana());
		user.setPostalCode(signupForm.getPostalCode());
		user.setAddress(signupForm.getAddress());
		user.setPhoneNumber(signupForm.getPhoneNumber());
		user.setEmail(signupForm.getEmail());
		user.setLicenseNumber(signupForm.getLicenseNumber());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.setRole(role);
		user.setEnabled(false);
		
		return userRepository.save(user);
		
	}
	//メールアドレスが登録済みか確認メソッド
	public boolean isEmailRegisterd(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}
	
	//パスワードと確認用パスワードが一致しているか確認するメソッド
	public boolean isSamePassword(String password, String passwordConfirmation) {
		return password.equals(passwordConfirmation);
	}
	
	//ユーザーを有効にする
	@Transactional
	public void enableUser(User user) {
		user.setEnabled(true);
		userRepository.save(user);
	}
}
