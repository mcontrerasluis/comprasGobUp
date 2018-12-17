import { IContratoMarco } from 'app/shared/model//contrato-marco.model';
import { IProveedor } from 'app/shared/model//proveedor.model';
import { IOrdenCompra } from 'app/shared/model//orden-compra.model';

export const enum EstatusElementoOrden {
    DISPONIBLE = 'DISPONIBLE',
    AGOTADO = 'AGOTADO',
    SIN_EXISTENCIA = 'SIN_EXISTENCIA'
}

export interface IElementoOrden {
    id?: number;
    cantidad?: number;
    precioTotal?: number;
    estatus?: EstatusElementoOrden;
    proveedorD?: string;
    contratoMarcoD?: string;
    contratoMarco?: IContratoMarco;
    proveedor?: IProveedor;
    ordenCompra?: IOrdenCompra;
}

export class ElementoOrden implements IElementoOrden {
    constructor(
        public id?: number,
        public cantidad?: number,
        public precioTotal?: number,
        public estatus?: EstatusElementoOrden,
        public proveedorD?: string,
        public contratoMarcoD?: string,
        public contratoMarco?: IContratoMarco,
        public proveedor?: IProveedor,
        public ordenCompra?: IOrdenCompra
    ) {}
}
