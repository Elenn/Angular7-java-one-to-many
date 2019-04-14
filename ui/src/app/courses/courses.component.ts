import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Course } from '../course';
import { CourseService } from '../course.service';
  
@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  @Input() taskData = { name: '', description: '' };

  showedit: boolean = false;

  courses: Course[];

  constructor(
    private courseService: CourseService
  ) { }

  ngOnInit() {
    this.getDepartments();
  }

  getDepartments(): void {
    this.courseService.getCourses()
      .subscribe(courses => this.courses = courses);
  } 

  showEditForm(): void {
    this.showedit = !this.showedit;
  }

}

