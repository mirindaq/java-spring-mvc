package vn.hoidanit.laptopshop.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.hoidanit.laptopshop.domain.User;

public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  public CustomUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    User user = this.userService.getUserByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())));
  }

}
