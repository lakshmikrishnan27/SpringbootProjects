package com.example.StudentDemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.StudentDemo.entity.Student;
import com.example.StudentDemo.repository.StudentRepository;

@Service
public class StudentServiceImpn implements StudentService{

	@Autowired
	public StudentRepository repository;

	@Override
	public Student findById(int id) {
		Student student = repository.findById(id).orElseThrow(EntityNotFoundException::new);
		return student;
	}

	@Override
	public List<Student> findAll() {
		List<Student> studentList = new ArrayList<Student>();
		repository.findAll().forEach(studentList::add);
		return studentList;
	}
	@Override
	public List<Student> findAll(Pageable pageable) {
		List<Student> studentList = new ArrayList<Student>();
		Page<Student> pagedResult = repository.findAll(pageable);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}
		return studentList;
	}

	@Override
	public List<Student> findByFirstName(String name) {
		return repository.findByFirstName(name);
	}

	@Override
	public List<Student> findByLastName(String name) {
		return repository.findByLastName(name);
	}

	@Override
	public List<Student> findByName(String firstName, String lastName) {
		if(firstName != null && !firstName.trim().isEmpty() && lastName !=null && !lastName.trim().isEmpty())
			return repository.findByName(firstName, lastName);
		else if((firstName != null && !firstName.trim().isEmpty()) && (lastName ==null || lastName.trim().isEmpty()))
			return repository.findByFirstName(firstName);
		else if((firstName == null || firstName.trim().isEmpty()) && (lastName !=null && !lastName.trim().isEmpty()))
			return repository.findByLastName(lastName);
		return null;
	}

	@Override
	public void updateStudent(Student student, int id) {
		Optional<Student> studentOptional = repository.findById(id);

		if (!studentOptional.isPresent())
		{
			throw new EntityNotFoundException();
		}else {
			student.setId(id);
			repository.save(student);
		}
	}

	@Override
	public void addStudent(Student student) {
		repository.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		repository.deleteById(id);
	}



}
