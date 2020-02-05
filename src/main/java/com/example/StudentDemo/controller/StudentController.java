package com.example.StudentDemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.StudentDemo.entity.Student;
import com.example.StudentDemo.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController
public class StudentController {

	@Autowired
	public StudentService studentService;

	//http://localhost:8080/students?page=0&size=6&sort=id,DESC
	@RequestMapping(value = "/students", method=RequestMethod.GET)
	public ModelAndView findAll(@PageableDefault(value = 2, page = 0, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		ModelAndView model = new ModelAndView();
		log.info("Inside listEmployee");
		List<Student> studentList = studentService.findAll(pageable);
		studentList.forEach(a->System.out.println("Employee Id"+a.getId()));
		model.addObject("studentList", studentList);
		model.setViewName("StudentList");
		return model;	
	}

	@RequestMapping(value="/students/name", method=RequestMethod.GET)
	public ModelAndView  fetchStudentByName(@RequestParam(value = "firstName", required=false) String firstName, @RequestParam(value="lastName",required=false) String lastName) {
		log.info("Inside fetchStudentByName firstName "+firstName+"lastName "+lastName);
		ModelAndView model = new ModelAndView();
		List<Student> studentList = studentService.findByName(firstName, lastName);
		model.addObject("studentList", studentList);
		model.setViewName("StudentList");
		return model;
	}

	@RequestMapping(value="/students/add", method=RequestMethod.GET)
	public ModelAndView addStudent() {
		log.info("Inside addStudent");
		ModelAndView model = new ModelAndView();
		Student student = new Student(0, "", "");
		model.addObject("studentForm", student);
		model.setViewName("StudentForm");
		return model;
	}
	@RequestMapping(value="/students/save", method=RequestMethod.POST)
	public ModelAndView saveStudent(@Valid@ModelAttribute("studentForm") Student student) {
		log.info("Inside saveStudent");
		if(student.getId() != null) {
			studentService.updateStudent(student, student.getId());

		}else{
			studentService.addStudent(student);
		}
		return new ModelAndView("redirect:/students");

	}
	@RequestMapping(value="/students/update/{id}", method=RequestMethod.GET)
	public ModelAndView updateStudent(@PathVariable("id") int id) {
		log.info("Inside updateStudent");
		ModelAndView model = new ModelAndView();
		Student student = studentService.findById(id);
		model.addObject("studentForm", student);
		model.setViewName("StudentForm");
		return model;
	}
	@RequestMapping(value="/students/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable("id") int id) {
		log.info("Inside deleteStudent");
		studentService.deleteStudent(id);
		return new ModelAndView("redirect:/students");
	}

	@RequestMapping(value="/students/id/{id}", method=RequestMethod.GET)
	public Student fetchStudentById(@PathVariable("id") int id) {
		log.info("Inside fetchStudentById");
		return studentService.findById(id);
	}

	@RequestMapping(value="/students/firstName/{firstName}", method=RequestMethod.GET)
	public List<Student>  fetchStudentByFirstName(@PathVariable("firstName") String firstName) {
		log.info("Inside fetchStudentByFirstName"+firstName);
		return studentService.findByFirstName(firstName);
	}
	@RequestMapping(value="/students/lastName/{lastName}", method=RequestMethod.GET)
	public List<Student>  fetchStudentByLastName(@PathVariable("lastName") String lastName) {
		log.info("Inside fetchStudentByLastName");
		return studentService.findByLastName(lastName);
	}
}
