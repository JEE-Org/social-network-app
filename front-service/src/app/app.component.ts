import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // Define the API endpoint URL
  private apiUrl = 'http://localhost:8095/ENSIASts/signup';

  // Define the default values for the registration form fields
  registrationRequest = {
    firstName: '',
    lastName: '',
    email: '',
    promo: 0,
    field: '',
    password: ''
  };

  // Inject the HttpClient service
  constructor(private http: HttpClient) {}

  // Define the method to handle form submission
  onSubmit() {

    // Set the request headers
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    // Send the POST request to the API endpoint
    this.http.post<any>(this.apiUrl, this.registrationRequest, { headers })
    .subscribe(
        response => {
            console.log('API response:', response);
            alert('Registration successful!');
        },
        error => {
            console.error('API error:', error);
            alert('Registration failed!');
        },
        () => {
            console.log('API request completed.');
        }
    );

      
  }

}
