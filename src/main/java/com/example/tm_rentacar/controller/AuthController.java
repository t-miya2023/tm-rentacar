package com.example.tm_rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tm_rentacar.form.SignupForm;
import com.example.tm_rentacar.service.UserService;

@Controller
public class AuthController {
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		//Viewにフォームクラスを渡す。フォームの各入力項目とフォームクラスの各フィールドをバインドする
		model.addAttribute("signupForm", new SignupForm());
		
		return "auth/signup";
	}
	@PostMapping("/signup")
	public String signup(@ModelAttribute @Validated SignupForm signupForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model)
	{
		//メールアドレスが登録済みならBindingResultオブジェクトにエラーを追加する
		if(userService.isEmailRegisterd(signupForm.getEmail())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
			bindingResult.addError(fieldError);
		}
		//パスワードとパスワード（確認用）の入力値が一致しなければ、エラーに追加
		if(!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
			bindingResult.addError(fieldError);
		}
		//もしエラーがあれば登録フォームに戻す。
		if(bindingResult.hasErrors()) {
			model.addAttribute("signupForm", signupForm);
			return "auth/signup";
		}
		
		userService.createUser(signupForm);
		redirectAttributes.addFlashAttribute("successMessage", "会員登録が完了しました。");
		
		return "redirect:/";
	}
}
