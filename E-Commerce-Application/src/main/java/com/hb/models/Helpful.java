package com.hb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Helpful {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	Integer count;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonIgnore
	List<Customer> customers = new ArrayList<>();

}
