package springboot.example.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.models.Course; 
import springboot.example.services.CourseService;

@RestController
public class CourseController {
 
    @Autowired
	private CourseService courseService;
	
	//Fetch all Courses
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/courses", method=RequestMethod.GET)
    Iterable<Course> getAllCourses() {
        return courseService.findAll();
    } 

    //Getting an course by id
    @CrossOrigin(origins = "*") 
    @RequestMapping(value = "/courses/{idOrName}", method=RequestMethod.GET)
    Course getCoursesByIdOrName(@PathVariable String idOrName) {
        return courseService.findByIdOrName(idOrName);
    }

    //Creating a Course
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/courses/add/departments/{departmentId}", method=RequestMethod.POST)
    Course createCourse(@RequestBody Course newCourse, @PathVariable Long departmentId) throws Exception {
    	return courseService.createOrUpdateCourse(departmentId, newCourse); 
    } 

    //Updating a Course
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/courses/update/departments/{departmentId}", method=RequestMethod.PUT)
    Course updateCourse(@RequestBody Course updatedCourse, @PathVariable Long departmentId) throws Exception {
        return courseService.createOrUpdateCourse(departmentId, updatedCourse);
    	 
    }	
}
 