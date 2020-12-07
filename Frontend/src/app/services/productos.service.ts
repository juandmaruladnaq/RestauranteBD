import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from './../models/producto';
@Injectable({
  providedIn: 'root'
})
export class ProductosService {
  private URL_API = "http://localhost:8080/productos"

  constructor(private http: HttpClient) { }

  save(producto: Producto)
  {
    return this.http.post<{control: string}>(this.URL_API, producto)    
  }

  get()
  {
    return this.http.get<Producto[]>(this.URL_API)
  }
}
