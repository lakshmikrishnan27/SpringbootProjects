package com.example.StudentDemo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.StudentDemo.entity.Student;

public interface StudentService {

	public Student findById(int id);
	public List<Student> findAll();
	public List<Student> findAll(Pageable pageable);
	public List<Student> findByName(String firstName, String lastName);
	public List<Student> findByFirstName(String name);
	public List<Student> findByLastName(String name);
	public void updateStudent(Student student,int id);
	public void addStudent(Student student);
	public void deleteStudent(int id);

}
