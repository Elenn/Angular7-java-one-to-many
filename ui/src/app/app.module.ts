import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser'; 
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
 
import { AppService } from './app.service';
import { AppComponent } from './app.component';
import { HomeComponent } from './home.component'; 
import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';

import { Observable } from 'rxjs';
 
import { RestService } from './rest.service';  
import { DepartmentService } from './department.service'; 
import { CourseService } from './course.service';

import { DepartmentsComponent } from './departments/departments.component';
import { DepartmentEditComponent } from './department-edit/department-edit.component';
import { CoursesComponent } from './courses/courses.component';
import { CourseEditComponent } from './course-edit/course-edit.component';

 
 

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'departments'},
  { path: 'home', component: HomeComponent},  
  { path: 'departments', component: DepartmentsComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'course/:id', component: CourseEditComponent },
  { path: 'department/:id', component: DepartmentEditComponent } 
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,  
    DepartmentsComponent,
    DepartmentEditComponent,
    CoursesComponent,
    CourseEditComponent 
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    FormsModule 
  ],
  providers: [AppService, RestService, CourseService, DepartmentService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
