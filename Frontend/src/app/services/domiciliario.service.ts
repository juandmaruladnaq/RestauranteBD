import { Domiciliario } from './../models/Domiciliario';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DomiciliarioService {

  private URL_API = "http://localhost:8080/domiciliarios"

  constructor(private http: HttpClient) { }

  save(domicilio: Domiciliario)
  {
    return this.http.post<{control: string}>(this.URL_API, domicilio)    
  }

  get()
  {
    return this.http.get<Domiciliario[]>(this.URL_API)
  }

  getById(id: String)
  {
    return this.http.post<Domiciliario>(this.URL_API+"/id", id)
  }

}
