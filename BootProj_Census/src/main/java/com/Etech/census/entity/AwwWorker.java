package com.Etech.census.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwwWorker {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
private String username;

@Column(nullable = false)
private String name;

@Column(nullable = false, unique = true, length = 10)
private String mobile;

@Column(nullable = false)
private String password;

@Transient
private String confirmPassword;

@Column(nullable = false)
private String state;

@Column(name = "aww_centre_code", nullable = false) 
private String awwCentreCode;

@Column(nullable = false)
private String district;


}
