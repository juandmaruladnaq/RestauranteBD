import { Producto } from './Producto';
import { Cliente } from './Cliente';
export interface Pedido 
{
    cliente: Cliente
    productos: Producto[]
}