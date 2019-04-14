package springboot.example.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.example.exceptions.ResourceNotFoundException; 
import springboot.example.models.Department; 
import springboot.example.repositories.DepartmentDao; 

@Service
public class DepartmentService
{
	@Autowired
    private DepartmentDao departmentDao; 

	@Transactional
    public Iterable<Department> findAll() {
    	return departmentDao.findAll();
    }

    @Transactional
	public Department findByIdOrDepartmentName(String idOrDepartmentName) {
		Department department;

        try {
            Long id = new Long(idOrDepartmentName);
            department = departmentDao.findOne(id);
        } catch (Exception e) {
            department = null;
        }

        if (department == null) {
            department = departmentDao.findByDepartmentName(idOrDepartmentName);
        }

        return department;
	}

	@Transactional
	public Department createDepartment(String departmentName) {
		Department department = new Department(departmentName);
        return departmentDao.save(department);
	}

	@Transactional
	public Department updateDepartment(Long departmentId, String newDepartmentName) throws Exception {

        Department department = departmentDao.findOne(departmentId);
        if(department == null){
        	throw new ResourceNotFoundException ("DepartmentId not found: " + departmentId);
        }
        department.setDepartmentName(newDepartmentName);
        return departmentDao.save(department);
	} 
    
}
