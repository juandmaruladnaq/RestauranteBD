import { Usuario } from './../models/Usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService
{
  private URL_API = "http://localhost:8080/usuarios"

  private URL_API_LOGIN = "http://localhost:8080/login"

  constructor(private http: HttpClient) { }

  save(usuario: Usuario)
  {
    return this.http.post<String>(this.URL_API, usuario)    
  }

  get()
  {
    return this.http.get<Usuario[]>(this.URL_API)
  }

  
  login(usuario: Usuario)
  {    
    return this.http.post<any>(this.URL_API_LOGIN, usuario)
  }
}
