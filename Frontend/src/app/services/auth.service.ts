import { Empleado } from './../models/Empleado';
import { Usuario } from './../models/Usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService 
{
  private URL_API = "http://localhost:8080/"

  constructor(private http: HttpClient) { }

  checkToken()
  {
    let header = new HttpHeaders().set(
      "Authorization",
       localStorage.getItem("token")
    ); 
    return this.http.get<boolean>(this.URL_API+"validate-token", {headers:header});
  
  }

  getUserFromToken()
  {
    let header = new HttpHeaders().set(
      "Authorization",
       localStorage.getItem("token")
    ); 
    return this.http.get<Empleado>(this.URL_API+"get-user", {headers:header});
  }
  
}
