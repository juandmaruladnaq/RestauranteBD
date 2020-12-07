import { UsuariosService } from './../../../services/usuarios.service';
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/Usuario';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-panel-usuarios',
  templateUrl: './panel-usuarios.component.html',
  styleUrls: ['./panel-usuarios.component.css']
})
export class PanelUsuariosComponent implements OnInit 
{
  formUsuario: FormGroup
  usuarios: Usuario[]
  msg: String

  constructor(private usuariosService: UsuariosService) { }

  ngOnInit(): void 
  {
    this.buildFormUsuario()
    this.getUsuarios()
  }

  private buildFormUsuario()
  {
    this.formUsuario = new FormGroup({
      cedula: new FormControl('', [Validators.required]),      
      password: new FormControl('', [Validators.required])      
    })
  }

  saveUsuario(usuario: Usuario)
  {
    this.usuariosService.save(usuario)
          .pipe(first())
          .subscribe(
            res => {
              console.log(res)
              this.msg = res
              this.formUsuario.reset()
              this.getUsuarios()
            },
            error =>{
              console.log(error)
              this.msg = error.error.message
            } 
          )
  }

  getUsuarios()
  {
    this.usuariosService.get()
        .subscribe(
          res => {
            this.usuarios = res
          },
          error => {
            console.log(error)
          }
        )
  }

}
