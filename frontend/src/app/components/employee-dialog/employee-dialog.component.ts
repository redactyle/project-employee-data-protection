import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employee-dialog',
  templateUrl: './employee-dialog.component.html',
  styleUrls: ['./employee-dialog.component.css']
})
export class EmployeeDialogComponent implements OnInit {

  employeeForm: FormGroup;

  constructor(private _formBuilder: FormBuilder, private _employeeService: EmployeeService, private _matDialogRef: MatDialogRef<EmployeeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private router: Router) {
    this.employeeForm = this._formBuilder.group({
      name: '',
      designation: '',
      salary: ''
    })
  }

  ngOnInit(): void {
    this.employeeForm.patchValue(this.data);
  }

  onFormSubmit() {
    if(this.employeeForm.valid) {
      if (this.data) {
        this._employeeService.updateEmployee(this.data.id, this.employeeForm.value).subscribe({
          next: (value: any) => {
            console.log(value);
            this._matDialogRef.close(true);
            alert('Employee Updated Successfully');
            this.redirectTo('');
          },
          error: (err: any) =>  {
            console.log(err);
          }
        })
      } else {
        this._employeeService.addEmployee(this.employeeForm.value).subscribe({
          next: (value: any) => {
            console.log(value);
            this._matDialogRef.close(true);
            alert('Employee Added Successfully');
            this.redirectTo('');         
          },
          error: (err: any) =>  {
            console.log(err);
          }
        })
        
      }
    }
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
  }
}