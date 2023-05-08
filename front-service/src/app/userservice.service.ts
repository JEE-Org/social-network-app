import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {

  constructor(private http: HttpClient) { }

  getUserData(firstName: string, lastName: string, email: string, promo: number, field: string, password: string): Observable<boolean> {
    const data = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      promo: promo,
      field: field,
      password: password
    };
    return this.http.post<boolean>('http://localhost:8090/signup', data);
  }
}
