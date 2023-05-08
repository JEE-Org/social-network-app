import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};
  getData: boolean = true;

  constructor(private userservice: UserserviceService, private router: Router) {}

  ngOnInit() {}

  loginUser() {
    const { firstName, lastName, email, promo, field, password } = this.model;
  
    this.userservice.getUserData(firstName, lastName, email, promo, field, password)
      .subscribe({
        next: (res: boolean) => {
          if (res) {
            this.router.navigate(['/home']);
          } else {
            alert('Invalid user');
          }
        },
        error: (err) => {
          console.error(err);
          alert('An error occurred');
        }
      });
  }
  
}
