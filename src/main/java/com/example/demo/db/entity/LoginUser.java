package com.example.demo.db.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class LoginUser extends org.springframework.security.core.userdetails.User {

  private com.example.demo.db.entity.User user;

  public com.example.demo.db.entity.User getUser() {
    return this.user;
  }

  public LoginUser(com.example.demo.db.entity.User user) {
    super(user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    this.user = user;
  }
}
