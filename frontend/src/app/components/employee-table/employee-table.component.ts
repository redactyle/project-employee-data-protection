import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Employee } from 'src/app/model/employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { EmployeeDialogComponent } from '../employee-dialog/employee-dialog.component';

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.css']
})
export class EmployeeTableComponent implements OnInit {

  employee!: Employee;

  actualSalary!: number;

  displayedColumns: string[] = ['name', 'designation', 'salary', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private _employeeService: EmployeeService, private _dialog: MatDialog) {}

  ngOnInit() {
    this.getEmployeesList();
  }

  getEmployeesList() {
    this._employeeService.getEmployees().subscribe({
      next: (response) => {
        for(var key in response) {
          response[key].isHidden = false;
          //response[key].actualSalary = this.getActualSalary(response[key].id);
          //console.log(response[key]);
        }
        //console.log(response);
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error: console.log
    });
  }

  getActualSalary(id: number) : number {
    this._employeeService.getEmployee(id).subscribe({
      next: (response) => {
        console.log(response);
        this.actualSalary = Number(response.salary);
        console.log(this.actualSalary);
      }
    })
    console.log(this.actualSalary);
    this.actualSalary = this.actualSalary;
    // this.actualSalary = 500000;
    return this.actualSalary;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteEmployee(id: number) {
    this._employeeService.deleteEmployee(id).subscribe({
      next: (response) => {
        alert('Employee Deleted Successfully');
        this.getEmployeesList();
      },
      error: console.log
    })
  }

  editEmployee(data: any) {
    this._dialog.open(EmployeeDialogComponent, {
      data: data 
    });
  }

  decryptByButton(row: any, hidden: boolean) {
    row.isHidden = !row.isHidden;
    console.log(!row.isHidden);
    // console.log(row.id);
    // this.actualSalary = 0;
    this.actualSalary = this.getActualSalary(row.id);
  }
}
