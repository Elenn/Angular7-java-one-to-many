package springboot.example.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;	 
	
    //https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/	
	@ManyToMany(cascade = { 
	    CascadeType.PERSIST, 
	    CascadeType.MERGE
	})
	@JoinTable(name = "course_student", 
	 	joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
	 	inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
	)
	private Set<Course> coursesList = new HashSet<>();
	
	public Student(){
		
	}

    public Student(String name){
		this.name = name;
	}

    public void addCourse(Course course) {
		if(course != null){
			coursesList.add(course);
	        course.getStudent().add(this);	
		}
    }
 
    //public void removeTask(Task task) {
    //	if(task != null){
    //    	tasksList.remove(task);
    //    	task.getTodoList().remove(this);	
    //	}
    //}



    /**
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

    /**
	 * @return the tasksList
	 */
	public Set<Course> getCoursesList()
	{
		return coursesList;
	}

	/**
	 * @param tasksList the tasksList to set
	 */
	public void setCoursesList(Set<Course> coursesList)
	{
		this.coursesList = coursesList;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        return !(id != 0 ? !(id == (student.id)) : student.id != 0);
    }

	@Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "Student[id=%d, name='%s']",
                id, name);
    }

}

