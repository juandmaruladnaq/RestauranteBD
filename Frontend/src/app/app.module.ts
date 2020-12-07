import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NewOrderComponent } from './components/pedidos/new-order/new-order.component';
import { PanelPedidosComponent } from './components/pedidos/panel-pedidos/panel-pedidos.component';
import { PanelClientesComponent } from './components/clientes/panel-clientes/panel-clientes.component';
import { PanelEmpleadosComponent } from './components/empleados/panel-empleados/panel-empleados.component';
import { PanelUsuariosComponent } from './components/usuarios/panel-usuarios/panel-usuarios.component';
import { PanelProductosComponent } from './components/productos/panel-productos/panel-productos.component';
import { NavigationMenuComponent } from './components/navbar/navigation-menu/navigation-menu.component';
import { LoginComponent } from './components/usuarios/login/login.component';
import { EnviosComponent } from './components/envios/envios.component';
import { DomiciliariosComponent } from './components/domiciliarios/domiciliarios.component';

@NgModule({
  declarations: [
    AppComponent,
    NewOrderComponent,
    PanelPedidosComponent,
    PanelClientesComponent,
    PanelEmpleadosComponent,
    PanelUsuariosComponent,
    PanelProductosComponent,
    NavigationMenuComponent,
    LoginComponent,
    EnviosComponent,
    DomiciliariosComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
