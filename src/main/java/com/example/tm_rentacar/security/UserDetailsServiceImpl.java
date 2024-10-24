package com.example.tm_rentacar.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		try {
			//送信されたメールアドレスとと一致するユーザー情報をテーブルから取得
			User user = userRepository.findByEmail(email);
			//本来はrolesテーブル用のモデルを作り、以下のようにrolesテーブルからロール名を取得するのが理想
			String userRoleName = user.getRole().getName();
			//------------------------------------------------------
			//ロールのリスト用オブジェクトの生成
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			//ユーザーのロール名をリストに追加
			authorities.add(new SimpleGrantedAuthority(userRoleName));
			//ユーザー情報とロール名を元にUserDetailsImplを生成
			return new UserDetailsImpl(user, authorities);
		}catch(Exception e) {
			throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
		}
	}
}

