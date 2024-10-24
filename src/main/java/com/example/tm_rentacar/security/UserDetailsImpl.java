package com.example.tm_rentacar.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.tm_rentacar.entity.User;

public class UserDetailsImpl implements UserDetails{
//	ユーザー情報管理用フィールド
	private final User user;
	private final Collection<GrantedAuthority> authorities;
//	コンストラクタ
	public UserDetailsImpl(User user, Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}
	//ログイン中のユーザー名取得のために定義
	public User getUser() {
		return user;
	}
	
	// ハッシュ化済みのパスワードを返す
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	//ログイン時に利用するユーザー名を返す
	@Override
	public String getUsername() {
		return user.getEmail();
	}
	//ロールのコレクションを返す
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
					//「GrantedAuthorityまたはそのサブタイプすべて」という意味
		return authorities;
	}
	//アカウントが期限切れでなければTRUEを返す
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	//ユーザーがロックされていなければTRUEを返す
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//ユーザーのパスワードが期限切れでなければtrueを返す
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	//ユーザーが有効であればTrueを返す
	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}
}
