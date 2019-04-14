import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators'; 
 
import { Department } from './department';

//const endpoint = 'https://jsonplaceholder.typicode.com/';
const endpoint = 'http://127.0.0.1:9000/departments/';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class DepartmentService {

  constructor(private http: HttpClient) { }

  private extractData(res: Response) {
    let body = res;
    return body || {};
  }

  /** GET departments from the server */
  getDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(endpoint)
      .pipe(
      tap(_ => this.log('fetched departments')),
      catchError(this.handleError<Department[]>('getDepartments', []))
      );
  }

  getDepartment(id: number): Observable<Department> { 
    return this.http.get<Department>(endpoint + id).pipe(
      tap(_ => this.log(`fetched department id=${id}`)),
      catchError(this.handleError<Department>(`getDepartment id=${id}`))
    );
  } 

  addDepartment(department: Department): Observable<Department> {
    return this.http.post<Department>(endpoint, department, httpOptions).pipe(
      tap((newDepartment: Department) => this.log(`added Department w/ id=${newDepartment.id}`))
    );
  }

  updateDepartment(department: Department): Observable<any> {
    const id = typeof department === 'number' ? department : department.id;
    const url = `${endpoint}/${id}`;

    return this.http.put(url, department, httpOptions).pipe(
      tap(_ => this.log(`updated department id=${department.id}`))

    );
  } 

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for department consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}
