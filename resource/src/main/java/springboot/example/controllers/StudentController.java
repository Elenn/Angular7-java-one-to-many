package springboot.example.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.models.Student;
import springboot.example.services.StudentService;

@RestController
public class StudentController
{
	public static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
    //Creating a student
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/students", method=RequestMethod.POST)
    Student createStudent(@RequestBody Student newStudent) {
        return studentService.saveStudent(newStudent);
    }

    //Adding student to a course
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/students/{id}/addcourse", method=RequestMethod.POST)
    Student AddCourse(@PathVariable Long id, @RequestBody List<String> courseIds) throws Exception {
    	List<Long> coursesIds = courseIds.stream().map(Long::parseLong).collect(Collectors.toList());
        return studentService.addCourses(id, coursesIds);
    }
    
   

	 
     
    
  	 
  	
}
