import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Domiciliario } from 'src/app/models/Domiciliario';
import { Envio } from 'src/app/models/envio';
import { DomiciliarioService } from 'src/app/services/domiciliario.service';
import { EnviosService } from 'src/app/services/envios.service';

@Component({
  selector: 'app-envios',
  templateUrl: './envios.component.html',
  styleUrls: ['./envios.component.css']
})
export class EnviosComponent implements OnInit {

    envios: Envio[]
    myForm: FormGroup;
    domiciliario:Domiciliario;
    msg: String;

  constructor(
    private fb: FormBuilder,
    private domiciliarioService: DomiciliarioService,
    private enviosService: EnviosService
  ) { }

  ngOnInit(): void {
    this.getEnvios()
    this.myForm = new FormGroup({
      domiciliario: new FormGroup({
        cedula: new FormControl('')
      })      
    });
  }
  async buscarDomiciliario(cedula: String)
  {
    console.log(cedula)
    await this.domiciliarioService.getById(cedula)
            .subscribe(
              res => {
                console.log("ById: ",res)
                this.domiciliario = res   
                this.msg = undefined                       
              },
              err =>{
                console.log(err)
                this.domiciliario = undefined
                this.msg = "Cliente No Existe"
              }
            )           
  }

  getEnvios()
  {
    this.enviosService.get()
        .subscribe(
          res => {
            this.envios = res
          },
          error => {
            console.log(error)
          }
        )
  }

  save(envio: Envio)
  {
    this.enviosService.save(envio)
                  .subscribe(
                    res => {
                      console.log(res)
                      this.myForm.reset()                      
                    },
                    error => console.log(error)
                  )
  }
}
