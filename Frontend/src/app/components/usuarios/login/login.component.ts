import { UsuariosService } from './../../../services/usuarios.service';
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/Usuario';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit 
{
  formUsuario: FormGroup
  usuarios: Usuario[]
  msg: String

  constructor(
    private usuariosService: UsuariosService,
    private router: Router
  ) { }

  ngOnInit(): void 
  {
    this.buildFormUsuario()
  }

  private buildFormUsuario()
  {
    this.formUsuario = new FormGroup({
      cedula: new FormControl('', [Validators.required]),      
      password: new FormControl('', [Validators.required])      
    })
  }

  login(usuario: Usuario)
  {
    this.usuariosService.login(usuario)          
          .subscribe(
            res => {
              localStorage.setItem("token", res.token)
              console.log("Resp")
              console.log(res)
              this.msg = res
              this.formUsuario.reset()      
              this.router.navigate(['']);          
            },
            error =>{
              console.log("Resp2")
              console.log(error)
              this.msg = error.error.message
            } 
          )
  }

}
