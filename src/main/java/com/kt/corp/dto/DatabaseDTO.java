package com.kt.corp.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("database")
@Getter
@Setter
@ToString
public class DatabaseDTO {

	private String database;

	public DatabaseDTO() {
	}
	
	public DatabaseDTO(String database) {
		super();
		this.database = database;
	}
	
	
}
