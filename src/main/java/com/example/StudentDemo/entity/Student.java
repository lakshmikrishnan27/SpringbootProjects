package com.example.StudentDemo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="student")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{

	private static final long serialVersionUID = -9215533014970539608L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	@Min(0)
	@NonNull
	Integer age;
	@NonNull
	@Size( max=30,message = "First name must not exceed 30 characters")
	String firstName;
	@NonNull
	@Size(max=30, message = "Last name must not exceed 30 characters")
	String lastName;
}
