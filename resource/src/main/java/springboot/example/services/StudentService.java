 package springboot.example.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.example.exceptions.ResourceNotFoundException;
import springboot.example.models.Course;
import springboot.example.models.Student;
import springboot.example.repositories.CourseDao;
import springboot.example.repositories.StudentDao;

@Service
public class StudentService
{
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private CourseDao courseDao;

    @Transactional
	public Student addCourses(long studentId, Iterable<Long> courseIds) throws Exception{
		
		Student student = this.findStudentById(studentId);
		if(student == null){
			throw new ResourceNotFoundException ("Student Id not found: " + studentId);
		}
		
		Iterable<Course> courses = courseDao.findAll(courseIds);
		if(courses == null){
			throw new ResourceNotFoundException ("courses not found");
		}
		
		courses.forEach(course-> student.addCourse(course));
		
		return this.saveStudent(student);	
	}

    @Transactional
	public Student findStudentById(Long studentId)
	{
		return studentDao.findOne(studentId);
	}

    @Transactional
	public Student saveStudent(Student student)
	{
		return studentDao.save(student);
	}
	
    @Transactional
	public void deleteStudentById(Long studentId)
	{
		studentDao.delete(studentId);
	}
 

}

