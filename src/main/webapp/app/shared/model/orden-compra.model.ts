import { Moment } from 'moment';
import { IElementoOrden } from 'app/shared/model//elemento-orden.model';
import { IFactura } from 'app/shared/model//factura.model';
import { ILugarEntrega } from 'app/shared/model//lugar-entrega.model';
import { IDependencia } from 'app/shared/model//dependencia.model';

export const enum EstatusOrden {
    COMPLETADA = 'COMPLETADA',
    PENDIENTE = 'PENDIENTE',
    CANCELADA = 'CANCELADA'
}

export interface IOrdenCompra {
    id?: number;
    fechaEntrada?: Moment;
    estatus?: EstatusOrden;
    codigo?: string;
    lugarEntregaD?: string;
    elementoOrdens?: IElementoOrden[];
    facturas?: IFactura[];
    lugarEntrega?: ILugarEntrega;
    dependencia?: IDependencia;
}

export class OrdenCompra implements IOrdenCompra {
    constructor(
        public id?: number,
        public fechaEntrada?: Moment,
        public estatus?: EstatusOrden,
        public codigo?: string,
        public lugarEntregaD?: string,
        public elementoOrdens?: IElementoOrden[],
        public facturas?: IFactura[],
        public lugarEntrega?: ILugarEntrega,
        public dependencia?: IDependencia
    ) {}
}
