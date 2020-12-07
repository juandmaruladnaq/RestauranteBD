import { Producto } from './producto';
import { Cliente } from './cliente';
export interface Pedido 
{
    cliente: Cliente
    productos: Producto[]
}