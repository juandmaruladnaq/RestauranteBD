import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.css']
})
export class NewOrderComponent implements OnInit 
{
  formPedido: FormGroup

  constructor() { }

  ngOnInit(): void 
  {
    this.buildFormUsuario()
  }

  private buildFormUsuario()
  {
    this.formPedido = new FormGroup({
      cliente: new FormArray([
        new FormControl('', [Validators.required])
      ])      
    })
  }

  private builtForm() {
    this.formPedido = new FormGroup({
      name: new FormControl('', [Validators.required]),
      telefonos: new FormArray([
        new FormControl('', [Validators.required])
      ])
    });
  }

}
