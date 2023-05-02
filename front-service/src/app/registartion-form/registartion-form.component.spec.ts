import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistartionFormComponent } from './registartion-form.component';

describe('RegistartionFormComponent', () => {
  let component: RegistartionFormComponent;
  let fixture: ComponentFixture<RegistartionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistartionFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistartionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
