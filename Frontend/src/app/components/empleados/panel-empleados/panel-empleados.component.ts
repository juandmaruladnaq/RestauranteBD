import { Empleado } from './../../../models/Empleado';
import { EmpleadosService } from './../../../services/empleados.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-panel-empleados',
  templateUrl: './panel-empleados.component.html',
  styleUrls: ['./panel-empleados.component.css']
})
export class PanelEmpleadosComponent implements OnInit 
{
  formEmpleado: FormGroup
  empleados: Empleado[]

  constructor(private empleadosService: EmpleadosService) { }

  ngOnInit(): void 
  {
    this.buildFormEmpleado()
    this.getEmpleados()
  }

  private buildFormEmpleado()
  {
    this.formEmpleado = new FormGroup({
      cedula: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      apellidos: new FormControl('', [Validators.required]),
      telefono: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email])
    })
  }

  saveEmpleado(empleado: Empleado)
  {
    this.empleadosService.save(empleado)
          .pipe(first())
          .subscribe(
            res => {
              console.log(res)
              this.formEmpleado.reset()
              this.getEmpleados()
            },
            error => console.log(error)
          )
  }

  getEmpleados()
  {
    this.empleadosService.get()
        .subscribe(
          res => {
            this.empleados = res
          },
          error => {
            console.log(error)
          }
        )
  }

}
