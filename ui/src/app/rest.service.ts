import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

//const endpoint = 'http://localhost:3000/api/v1/';
//const endpoint = 'https://jsonplaceholder.typicode.com/';
const endpoint = 'http://127.0.0.1:9000/';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable()
export class RestService {

  constructor(private http: HttpClient) { }

  private extractData(res: Response) {
    let body = res;
    return body || {};
  }

  getProducts(): Observable<any> {
    //return this.http.get(endpoint + 'users').pipe(
    return this.http.get(endpoint).pipe( 
      map(this.extractData));
  }

  getUsers(): Observable<any> {
    return this.http.get(endpoint + 'users').pipe(
    //return this.http.get(endpoint).pipe(
      map(this.extractData));
  }

  getProduct(id): Observable<any> {
    return this.http.get(endpoint + 'users/' + id).pipe(
      map(this.extractData));
  }  

  
}
