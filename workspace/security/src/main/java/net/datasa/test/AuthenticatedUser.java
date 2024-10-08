package net.datasa.test;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthenticatedUser implements UserDetails {
	
	private static final long serialVersionUID = 1050567301951305L;
	
	String id;
	String password;
	String roleName;
	boolean enabled;
	String name;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// "ROLE_USER", "ROLE_ADMIN"
		return Collections.singletonList(new SimpleGrantedAuthority(roleName));
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return id;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		// 
		return enabled;
	}

}
