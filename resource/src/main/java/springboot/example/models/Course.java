package springboot.example.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "courses")
public class Course
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	  
	private String name;
	private String description;	
	 
	@ManyToOne
	private Department assignedDepartment;   
	
	public Course(){
		
	}
	
	public Course(String name){
		this.name = name;
	}
	
	
	public Course(String name, String description){     
		this.name = name;
		this.description =  description;
		//this.assignedDepartment = new Department(departmentName);
	}
     
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
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	} 
			
	
	/**
	 * @return the courseDepartment
	 */
	public Department getAssignedDepartment()
	{
		return assignedDepartment;
	}

	/**
	 * @param courseDepartment the courseDepartment to set
	 */
	public void setAssignedDepartment(Department assignedDepartment)
	{
		this.assignedDepartment = assignedDepartment;
	}
	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        return !(id != 0 ? !(id == (course.id)) : course.id != 0);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result;
        return result;
    } 
}

