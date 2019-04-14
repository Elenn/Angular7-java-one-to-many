 package springboot.example.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    private String departmentName;

    //@OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Task> tasks = new HashSet<>();

    protected Department() {}

    public Department(long id){
    	this.id = id;
    }
    
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

     @Override
    public String toString() {
        return String.format(
                "Department[id=%d, departmentName='%s']",
                id, departmentName);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department department = (Department) o;

        if (id != department.id) return false;
        return !(departmentName != null ? !departmentName.equals(department.departmentName) : department.departmentName != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    } 
     
}