import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Cliente } from './../../../models/Cliente';
import { ClientesService } from './../../../services/clientes.service';


@Component({
  selector: 'app-panel-clientes',
  templateUrl: './panel-clientes.component.html',
  styleUrls: ['./panel-clientes.component.css']
})
export class PanelClientesComponent implements OnInit {

  formCliente: FormGroup
  clientes: Cliente[]

  constructor(private clientesService: ClientesService) { }

  ngOnInit(): void 
  {
    this.buildFormCliente()
    this.getClientes()
  }




  
  private buildFormCliente()
  {
    this.formCliente = new FormGroup({
      cedula: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      apellidos: new FormControl('', [Validators.required]),
      direccion: new FormControl('', [Validators.required]),
      telefono: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email])
    })
  }

  saveCliente(cliente: Cliente)
  {
    this.clientesService.save(cliente)
          .pipe(first())
          .subscribe(
            res => {
              console.log(res)
              this.formCliente.reset()
              this.getClientes()
            },
            error => console.log(error)
          )
  }

  getClientes()
  {
    this.clientesService.get()
        .subscribe(
          res => {
            this.clientes = res
          },
          error => {
            console.log(error)
          }
        )
  }
}
