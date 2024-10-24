package com.example.tm_rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.entity.VerificationToken;
import com.example.tm_rentacar.event.SignupEventPublisher;
import com.example.tm_rentacar.form.SignupForm;
import com.example.tm_rentacar.service.UserService;
import com.example.tm_rentacar.service.VerificationTokenService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
	private final UserService userService;
	private final SignupEventPublisher signupEventPublisher;
	private final VerificationTokenService verificationTokenService;
	
	public AuthController(UserService userService, SignupEventPublisher signupEventPublisher, VerificationTokenService verificationTokenService) {
		this.userService = userService;
		this.signupEventPublisher = signupEventPublisher;
		this.verificationTokenService = verificationTokenService;
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
			HttpServletRequest httpServletRequest,
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
		
		User createdUser = userService.createUser(signupForm);
		String requestUrl = new String(httpServletRequest.getRequestURL());
		signupEventPublisher.publishSignupEvent(createdUser, requestUrl);
		redirectAttributes.addFlashAttribute("successMessage", "ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");
		
		return "redirect:/";
	}
	
	@GetMapping("/signup/verify")
	public String verify(@RequestParam(name = "token") String token, Model model) {
		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
		
		if(verificationToken != null) {
			User user = verificationToken.getUser();
			userService.enableUser(user);
			model.addAttribute("successMessage", "会員登録が完了しました。");
		} else {
			model.addAttribute("errorMessage", "トークンが無効です。");
		}
		
		return "auth/verify";
	}
}
