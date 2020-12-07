import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pedido } from '../models/Pedido';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  private URL_API = "http://localhost:8080/pedido"


  constructor(private http: HttpClient) { }

  save(pedido: Pedido)
  {
    return this.http.post<String>(this.URL_API, pedido)    
  }
}
