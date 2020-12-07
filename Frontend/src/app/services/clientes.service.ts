import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from './../models/cliente';
@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  private URL_API = "http://localhost:8080/clientes"

  constructor(private http: HttpClient) { }

  save(cliente: Cliente)
  {
    return this.http.post<{control: string}>(this.URL_API, cliente)    
  }

  get()
  {
    return this.http.get<Cliente[]>(this.URL_API)
  }
}
