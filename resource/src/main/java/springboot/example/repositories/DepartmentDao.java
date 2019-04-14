package springboot.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import springboot.example.models.Department;

@Transactional
public interface DepartmentDao extends CrudRepository<Department, Long> {

  Department findByDepartmentName(String departmentName);

} 