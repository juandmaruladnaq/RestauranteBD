import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Producto } from './../../../models/Producto';
import { ProductosService } from './../../../services/productos.service';

@Component({
  selector: 'app-panel-productos',
  templateUrl: './panel-productos.component.html',
  styleUrls: ['./panel-productos.component.css']
})
export class PanelProductosComponent implements OnInit {
  formProducto: FormGroup
  productos: Producto[]

  constructor(private productosService: ProductosService) { }

  ngOnInit(): void 
  {
    this.buildFormProducto()
    this.getProductos()
  }

  private buildFormProducto()
  {
    this.formProducto = new FormGroup({
      codigo: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', [Validators.required]),
      valor: new FormControl('', [Validators.required])
      
    })
  }

  saveProducto(producto: Producto)
  {
    this.productosService.save(producto)
          .pipe(first())
          .subscribe(
            res => {
              console.log(res)
              this.formProducto.reset()
              this.getProductos()
            },
            error => console.log(error)
          )
  }

  getProductos()
  {
    this.productosService.get()
        .subscribe(
          res => {
            this.productos = res
          },
          error => {
            console.log(error)
          }
        )
  }

}