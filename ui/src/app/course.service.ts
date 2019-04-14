import { Injectable } from '@angular/core';
 
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

import { Course } from './course';
import { Department } from './department';


//const endpoint = 'https://jsonplaceholder.typicode.com/';
const endpoint = 'http://127.0.0.1:9000/courses/';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) { }  

  /** GET courses from the server */
  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(endpoint)
      .pipe(
      tap(_ => this.log('fetched course')),
      catchError(this.handleError<Course[]>('getCourses', []))
      );
  }

   /** GET course from the server */
  getCourse(id: number): Observable<Course> {
    return this.http.get<Course>(endpoint + id).pipe(
      tap(_ => this.log(`fetched course id=${id}`)),
      catchError(this.handleError<Course>(`getCourse id=${id}`))
    );
  } 

  /** POST: add a new course to the server */
  addCourse(course: Course, department: Department): Observable<Course> {
    const url = `${endpoint}add/departments/${department.id}`;
    return this.http.post<Course>(url, course, httpOptions).pipe(
      tap((newCourse: Course) => this.log(`added course w/ id=${newCourse.id}`)),
      catchError(this.handleError<Course>('addCourse'))
    );
  }

  /** PUP: update a hero to the server */
  updateCourse(course: Course): Observable<any> { 
    const url = `${endpoint}update/departments/${course.assignedDepartment.id}`;

    return this.http.put(url, course, httpOptions).pipe(
      tap(_ => this.log(`updated course id=${course.id}`))

    );
  } 

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for course consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}
