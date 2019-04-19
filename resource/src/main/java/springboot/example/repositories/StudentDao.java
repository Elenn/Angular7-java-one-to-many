 package springboot.example.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;



import springboot.example.models.Student;

@Transactional
public interface StudentDao extends CrudRepository<Student, Long> {

	List<Student> findAllByCoursesList_Id(Iterable<Long> coursesIds);
}