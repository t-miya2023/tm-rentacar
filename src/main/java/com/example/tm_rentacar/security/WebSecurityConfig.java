package com.example.tm_rentacar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration //設定用のクラスとして機能させる
@EnableWebSecurity //Spring Securityによるセキュリティ機能を有効にし、認証・認可のルールやログイン・ログアウト処理といった各種設定を行えるようにする。
@EnableMethodSecurity //メソッドレベルでのセキュリティ機能を有効にする。@PreAuthorize("hasRole('ADMIN')")とつけると、管理者のみにメソッドへのアクセスを許可できる。
public class WebSecurityConfig {
//セキュリティ設定用メソッドの定義----------------------------------------
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{ 
		http
			// ----------- アクセス許可に関する設定-----------------
			.authorizeHttpRequests((requests) -> requests 
					.requestMatchers("/", "/css/**", "/images/**", "/js/**", "/storage/**", "/signup/**", "/cars", "/cars/{id}", "/stripe/webhook").permitAll()//全ユーザーにアクセスを許可するURL 
					.requestMatchers("/admin/**").hasRole("ADMIN") // 管理者にのみアクセスを許可するURL
					.anyRequest().authenticated()//上記以外のURLはログインが必要（ロール問わず）
			)
			// ------------ログインに関する設定-------------------
			.formLogin((form) -> form
					.loginPage("/login")//ログインページのURL
					.loginProcessingUrl("/login")//ログインフォームの送信先URL
					.defaultSuccessUrl("/?loggedIn")//ログイン成功時のリダイレクト先URL
					.failureUrl("/login?error")//ログイン失敗時のリダイレクト先URL
					.permitAll()
			)
			// --------------ログアウトに関する設定----------------
			.logout((logout) -> logout
					.logoutSuccessUrl("/?loggedOut") //ログアウト時のリダイレクト先URL
					.permitAll()
			)
			//外部からのPost通信はCSRF対策で拒否される→"/stripe/webhook"はCSRFチェックを無効化
			.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/stripe/webhook")));
		return http.build(); //設定後build()メソッドを実行し戻り値を返す
	}
//パスワード処理用メソッドの定義	-------------------------------------------
	@Bean 
	PasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
}

