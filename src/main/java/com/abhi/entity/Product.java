package com.abhi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue
	private Integer pid;
	
	@NotBlank(message="Name is manditory")
	@Size(min=3,max=15,message="name should be 3 to 15 character")
	private String name;
	
	@NotEmpty(message="price is mandatory")
	@Positive(message="price should be positive")
	private String price;
	
	@NotNull(message="Quantity is manditory")
	@Positive(message="quantity should be positive")
	private Integer qty;

}
