import { Domiciliario } from './models/Domiciliario';
import { CheckTokenGuard } from './guards/check-token.guard';
import { NewOrderComponent } from './components/pedidos/new-order/new-order.component';
import { PanelEmpleadosComponent } from './components/empleados/panel-empleados/panel-empleados.component';
import { PanelUsuariosComponent } from './components/usuarios/panel-usuarios/panel-usuarios.component'
import { LoginComponent } from './components/usuarios/login/login.component'
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PanelClientesComponent } from './components/clientes/panel-clientes/panel-clientes.component';
import { PanelProductosComponent } from './components/productos/panel-productos/panel-productos.component';
import { EnviosComponent } from './components/envios/envios.component';
import { DomiciliariosComponent } from './components/domiciliarios/domiciliarios.component';

const routes: Routes = [
  {path:'ordenar', component: NewOrderComponent, canActivate: [CheckTokenGuard]},
  {path:'empleados', component: PanelEmpleadosComponent, canActivate: [CheckTokenGuard]},
  {path:'usuarios', component: PanelUsuariosComponent, canActivate: [CheckTokenGuard]},
  {path:'clientes', component: PanelClientesComponent, canActivate: [CheckTokenGuard] },
  {path:'productos', component:PanelProductosComponent, canActivate: [CheckTokenGuard]},
  {path:'envios', component: EnviosComponent, canActivate: [CheckTokenGuard]},
  {path:'domicilios', component: DomiciliariosComponent, canActivate: [CheckTokenGuard]},
  {path:'login', component: LoginComponent},
  { path: '**', pathMatch:'full', redirectTo: 'ordenar' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
