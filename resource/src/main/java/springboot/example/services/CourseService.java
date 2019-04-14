package springboot.example.services;

import java.util.List; 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.example.exceptions.ResourceNotFoundException;
import springboot.example.models.Course; 
import springboot.example.models.Department;
import springboot.example.repositories.CourseDao;
import springboot.example.repositories.DepartmentDao;  

@Service
public class CourseService
{
	@Autowired
    private CourseDao courseDao;
	
	@Autowired
    private DepartmentDao departmentDao;

	@Transactional
    public Iterable<Course> findAll() {
    	return courseDao.findAll();
    }

    @Transactional
	public Course findByIdOrName(String idOrName) {
		Course course;

        try {
            Long id = new Long(idOrName);
            course = courseDao.findOne(id);
        } catch (Exception e) {
            course = null;
        }

        if (course == null) {
            course = courseDao.findByName(idOrName);
        }

        return course;
	}	

    @Transactional
	public Course createOrUpdateCourse(Long departmentId, Course course) throws Exception {
		Department department = departmentDao.findOne(departmentId);
		if(department == null){
			throw new ResourceNotFoundException("DepartmentId not found: " + departmentId);
		}
		course.setAssignedDepartment(department);
		return courseDao.save(course);
	}
 
	
	//@Transactional
	//public Course CreateOrUpdateCourse(Long departmentId, Course course) throws Exception {
	//	Department department = departmentDao.findOne(departmentId);
	//	if(department == null){
	//		throw new ResourceNotFoundException("DepartmentId not found: " + departmentId);
	//	}
	//	course.setAssignedDepartment(department);
	//	return courseDao.save(course);
    //    //return course;
	//} 
    
    //@Transactional
  	//public List<Course> findByAssignedDepartmentId(long departmentId) { 
    //	return  courseDao.findByAssignedDepartmentId(departmentId);	
  	//} 

}