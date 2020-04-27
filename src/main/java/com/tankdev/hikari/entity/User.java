package com.tankdev.hikari.entity;

import lombok.Data;

/**
 * @author javadev.top
 */
@Data
public class User extends BaseEntity {

	private Integer id;
	private Integer age;
	private String email;
	private String name;
}


