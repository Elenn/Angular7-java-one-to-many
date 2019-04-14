package springboot.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import springboot.example.models.Department;
import springboot.example.services.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	//Getting all departments
    @CrossOrigin(origins = "*") 
    @RequestMapping(value="/departments", method=RequestMethod.GET)
    Iterable<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    //Getting an department by id
    @CrossOrigin(origins = "*") 
    @RequestMapping(value = "/departments/{idOrDepartmentName}", method=RequestMethod.GET)
    Department getDepartmentByIdOrDepartmentName(@PathVariable String idOrDepartmentName) {
        return departmentService.findByIdOrDepartmentName(idOrDepartmentName);
    }

    //Create a department
    @CrossOrigin(origins = "*") 
    @RequestMapping(value="/departments", method=RequestMethod.POST)
    Department createDepartment(@RequestBody Department newDepartment) {
        return departmentService.createDepartment(newDepartment.getDepartmentName());
    }

    //Updating a department
    @CrossOrigin(origins = "*") 
    @RequestMapping(value="/departments/{departmentId}", method=RequestMethod.PUT)
    Department updateDepartment(@PathVariable Long departmentId, @RequestBody Department updatedDepartment) throws Exception {
        return departmentService.updateDepartment(departmentId, updatedDepartment.getDepartmentName());
    } 
}
