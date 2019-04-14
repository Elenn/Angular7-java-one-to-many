import { Component, OnInit, Input } from '@angular/core'; 
import { Department } from '../department';
import { Course } from '../course';
import { DepartmentService } from '../department.service';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-departments',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent implements OnInit { 
 
  showedit: boolean = false;

  departments: Department[];

  constructor( 
    private departmentService: DepartmentService
   
  ) { }

  ngOnInit() {
    this.getDepartments();
  }

  getDepartments(): void {
    this.departmentService.getDepartments()
      .subscribe(departments => this.departments = departments);
  }

  addDepartment(departmentName: string): void {
    departmentName = departmentName.trim();
    if (!departmentName) { return; }
    this.departmentService.addDepartment({ departmentName } as Department)
      .subscribe(department => {
        this.departments.push(department);
      });
  }

  

  showEditForm(): void {
    this.showedit = !this.showedit;
  }

}
