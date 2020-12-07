import { NewOrderComponent } from './components/pedidos/new-order/new-order.component';
import { PanelEmpleadosComponent } from './components/empleados/panel-empleados/panel-empleados.component';
import { PanelUsuariosComponent } from './components/usuarios/panel-usuarios/panel-usuarios.component'
import { LoginComponent } from './components/usuarios/login/login.component'
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PanelClientesComponent } from './components/clientes/panel-clientes/panel-clientes.component';
import { PanelProductosComponent } from './components/productos/panel-productos/panel-productos.component';

const routes: Routes = [
  {path:'ordenar', component: NewOrderComponent},
  {path:'empleados', component: PanelEmpleadosComponent},
  {path:'usuarios', component: PanelUsuariosComponent},
  {path:'clientes', component: PanelClientesComponent },
  {path:'productos', component:PanelProductosComponent},
  {path:'login', component: LoginComponent},
  { path: '**', pathMatch:'full', redirectTo: 'ordenar' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
