package com.example.tm_rentacar.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.form.UserEditForm;
import com.example.tm_rentacar.security.UserDetailsImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userDetailsImpl.getUser();
		
		model.addAttribute("user", user);
		
		return "user/index";
	}
	
	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userDetailsImpl.getUser();
		UserEditForm userEditForm = new UserEditForm(user.getName(), user.getFurigana(), user.getPostalCode(), user.getAddress(), user.getPhoneNumber(), user.getEmail(), user.getLicenseNumber());
		
		model.addAttribute("userEditForm", userEditForm);
		
		return "user/edit";
	}
}
