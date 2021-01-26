package com.example.demo.service.user;

import com.example.demo.db.repository.UserRepository;
import com.example.demo.db.entity.LoginUser;
import com.example.demo.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("closetUserDetailService")
public class ClosetUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  public ClosetUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String email) {
    User user = userRepository.findByEmail(email);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("user not found");
    }

    return new LoginUser(user);
  }
}
