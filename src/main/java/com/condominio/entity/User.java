package com.condominio.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="login_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private String userId;
	private String password;
}
