import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RegistrationFormComponent } from './registration-form.component';

describe('RegistrationFormComponent', () => {
  let component: RegistrationFormComponent;
  let fixture: ComponentFixture<RegistrationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should set the form values on submit', () => {
    const firstName = 'John';
    const lastName = 'Doe';
    const email = 'john.doe@example.com';
    const promo = 2021;
    const field = 'Computer Science';
    const password = 'password123';
    component.registrationForm.setValue({
      firstName,
      lastName,
      email,
      promo,
      field,
      password,
    });
    component.onSubmit();
    expect(component.submitted).toBeTrue();
    expect(component.registrationRequest).toEqual({
      firstName,
      lastName,
      email,
      promo,
      field,
      password,
    });
  });
});
