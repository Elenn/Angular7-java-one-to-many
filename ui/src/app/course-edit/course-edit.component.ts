import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Course } from '../course'; 
import { CourseService } from '../course.service';

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {

  course: Course;

  constructor(
    private route: ActivatedRoute, 
    private courseService: CourseService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getCourse()
  }

  getCourse(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.courseService.getCourse(id)
      .subscribe(course => this.course = course);
  }

  updateCourse(course: Course): void {
    this.courseService.updateCourse(course)
      .subscribe(() => this.goBack());
  } 

  goBack(): void {
    this.location.back();
  }  

}
