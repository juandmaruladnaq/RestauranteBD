import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Pedido } from 'src/app/models/Pedido';
import { PedidoService } from './../../../services/pedido.service';

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.css']
})
export class NewOrderComponent implements OnInit 
{  
  myForm: FormGroup;

  get productos(): FormArray {
    return this.myForm.get("productos") as FormArray;
  }

  constructor(
    private fb: FormBuilder,
    private pedidoService: PedidoService
    ) { }

  ngOnInit() {
    this.myForm = new FormGroup({
      cliente: new FormGroup({
        cedula: new FormControl('')
      }),
      usuario: new FormGroup({
        cedula: new FormControl('')
      })
    })
    this.myForm = this.fb.group({
      cliente: this.fb.group({
        cedula: [""]
      }),
      usuario: this.fb.group({
        cedula: [""]
      }),
      productos: this.fb.array([])
    });
  }

  
  private buildProducto(): FormGroup {
    return this.fb.group({
      producto: this.fb.group({
        codigo: [""]
      }),
      cantidad: [""],
      valor: [""]
    });
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
