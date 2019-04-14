 package springboot.example.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import springboot.example.models.Course; 

@Transactional
public interface CourseDao extends CrudRepository<Course, Long> {
  
  //public List<Course> findByAssignedDepartmentId(Long courseId);
  
  Course findByName(String courseName);
}