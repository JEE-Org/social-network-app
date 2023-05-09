import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  firstName: string ="";
  lastName: string ="";
  email: string ="";
  promo: number =0;
  field: string ="";
  password: string ="";
 
 
  constructor(private http: HttpClient )
  {
 
  }

  save()
  {
  
    let bodyData = {
      "firstName" : this.firstName,
      "lastName" : this.lastName,
      "email" : this.email,
      "promo" : this.promo,
      "field" : this.field,
      "password" : this.password
    };
    this.http.post("http://localhost:8090/authentication/signup",bodyData,{responseType: 'text'}).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Ensiast Registered Successfully");
 
    });
  }

}
