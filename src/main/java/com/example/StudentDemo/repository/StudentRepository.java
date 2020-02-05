package com.example.StudentDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.StudentDemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{

	@Query("SELECT em FROM Student em WHERE lower(em.lastName)  LIKE lower(CONCAT('%', :lastName, '%'))") 
	List<Student> findByLastName(@Param("lastName") String lastName);
	
	@Query("SELECT em FROM Student em WHERE lower(em.firstName) LIKE lower(CONCAT('%', :firstName, '%'))") 
	List<Student> findByFirstName(@Param("firstName") String firstName);

	@Query("SELECT em FROM Student em WHERE lower(em.firstName) LIKE lower(CONCAT('%', :firstName, '%')) AND lower(em.lastName) LIKE lower(CONCAT('%', :lastName, '%'))")
	List<Student> findByName(@Param("firstName") String firstName,@Param("lastName") String lastName);

}