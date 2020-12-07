import { Empleado } from './../models/Empleado';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService 
{
  private URL_API = "http://localhost:8080/empleados"

  constructor(private http: HttpClient) { }

  save(empleado: Empleado)
  {
    return this.http.post<{control: string}>(this.URL_API, empleado)    
  }

  get()
  {
    return this.http.get<Empleado[]>(this.URL_API)
  }
}
