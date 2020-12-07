import { Cliente } from './../../../models/cliente';
import { Empleado } from './../../../models/Empleado';
import { Usuario } from './../../../models/Usuario';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Pedido } from 'src/app/models/Pedido';
import { PedidoService } from './../../../services/pedido.service';
import { AuthService } from './../../../services/auth.service'
import { ClientesService } from './../../../services/clientes.service'

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.css']
})
export class NewOrderComponent implements OnInit 
{  
  myForm: FormGroup;

  usuario: Empleado;

  cliente: Cliente;

  msg: String;
  

  get productos(): FormArray {
    return this.myForm.get("productos") as FormArray;
  }

  constructor(
    private fb: FormBuilder,
    private pedidoService: PedidoService,
    private authService: AuthService,
    private clientesService: ClientesService
    ) { }

  ngOnInit() {    
    this.myForm = new FormGroup({
      cliente: new FormGroup({
        cedula: new FormControl('')
      })      
    })
    this.myForm = this.fb.group({
      cliente: this.fb.group({
        cedula: [""]
      }),      
      productos: this.fb.array([])
    });

    this.getUser()
  }

  private async getUser(){
    await this.authService.getUserFromToken()
        .subscribe(
          res => {
            console.log(res)
            this.usuario = res
          },
          error => {
            console.log(error)
          }
        )
  }

  
  private buildProducto(): FormGroup {
    return this.fb.group({
      producto: this.fb.group({
        codigo: [""]
      }),
      cantidad: [""],
      valorUn: [""]
    });
  }

  async buscarCliente(cedula: String)
  {
    console.log(cedula)
    await this.clientesService.getById(cedula)
            .subscribe(
              res => {
                console.log("ById: ",res)
                this.cliente = res   
                this.msg = undefined                       
              },
              err =>{
                console.log(err)
                this.cliente = undefined
                this.msg = "Cliente No Existe"
              }
            )           
  }

  addProducto() {
    this.productos.push(this.buildProducto())
  }

  removeProducto(i: number) {
    this.productos.removeAt(i);
  }

  save(pedido: Pedido)
  {
    this.pedidoService.save(pedido)
                  .subscribe(
                    res => {
                      console.log(res)
                      this.myForm.reset()                      
                    },
                    error => console.log(error)
                  )
  }

}
