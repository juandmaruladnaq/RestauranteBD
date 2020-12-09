import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Envio } from '../models/envio';

@Injectable({
  providedIn: 'root'
})
export class EnviosService {
  private URL_API = "http://localhost:8080/envios"

  constructor(private http: HttpClient) { }

  save(envio: Envio)
  {
    return this.http.post<{control: string}>(this.URL_API, envio)    
  }

  get()
  {
    return this.http.get<Envio[]>(this.URL_API)
  }

  getById(id: String)
  {
    return this.http.post<Envio>(this.URL_API+"/id", id)
  }
}
