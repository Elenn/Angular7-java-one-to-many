import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
 
import { Department } from '../department';
import { Course } from '../course';
import { DepartmentService } from '../department.service';
import { CourseService } from '../course.service';

@Component({
  selector: 'app-department-edit',
  templateUrl: './department-edit.component.html',
  styleUrls: ['./department-edit.component.css']
})
export class DepartmentEditComponent implements OnInit {
  courseData = { name: '', description: '' }; 
  department: Department; 

  constructor(
    private route: ActivatedRoute,
    private departmentService: DepartmentService,
    private courseService: CourseService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getDepartment()
  }

  getDepartment(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.departmentService.getDepartment(id)
      .subscribe(department => this.department = department);
  }

  updateDepartment(department: Department): void {
    this.departmentService.updateDepartment(department)
      .subscribe(() => this.goBack());
  }

  addCourse(department): void {
    this.courseService.addCourse(this.courseData as Course, department)
      .subscribe();
    window.location.href = '/courses';
    //location.reload();
  } 

  goBack(): void {
    this.location.back();
  }  
}
