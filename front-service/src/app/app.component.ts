import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistrationRequest } from 'src/app/models/registration-request';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private apiService: ApiService) { }

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      promo: ['', Validators.required],
      field: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onSubmit(): void {
    const registrationRequest: RegistrationRequest = this.registrationForm.value;
    this.apiService.registerENSIASt(registrationRequest).subscribe(
      () => {
        console.log('Registration successful!');
        // Add any success message or redirect to a success page here
      },
      (error) => {
        console.log(`Registration failed: ${error.message}`);
        // Add any error message or handle the error here
      }
    );
  }

}
