import { DomiciliarioService } from './../../services/domiciliario.service';
import { Domiciliario } from './../../models/Domiciliario';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';





@Component({
  selector: 'app-domiciliarios',
  templateUrl: './domiciliarios.component.html',
  styleUrls: ['./domiciliarios.component.css']
})
export class DomiciliariosComponent implements OnInit {

  formDomicilio: FormGroup
  domicilios: Domiciliario[]

  constructor(private domiciliarioService: DomiciliarioService) { }

  ngOnInit(): void 
  {
    this.buildFormDomicilios()
    this.getClientes()
  }

  private buildFormDomicilios()
  {
    this.formDomicilio = new FormGroup({
      cedula: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      apellidos: new FormControl('', [Validators.required]),      
      telefono: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email])
    })
  }

  saveDomicilio(domicilio: Domiciliario)
  {
    this.domiciliarioService.save(domicilio)
          .pipe(first())
          .subscribe(
            res => {
              console.log(res)
              this.formDomicilio.reset()
              this.getClientes()
            },
            error => console.log(error)
          )
  }

  getClientes()
  {
    this.domiciliarioService.get()
        .subscribe(
          res => {
            this.domicilios = res
          },
          error => {
            console.log(error)
          }
        )
  }
}

