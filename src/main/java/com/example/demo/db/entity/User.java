package com.example.demo.db.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

  @Id
  private String id;

  private String password;

  private String email;

  private String sex;

  private String age;
}
