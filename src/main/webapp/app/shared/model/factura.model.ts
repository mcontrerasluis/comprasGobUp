import { Moment } from 'moment';
import { IEmbarque } from 'app/shared/model//embarque.model';
import { IOrdenCompra } from 'app/shared/model//orden-compra.model';

export const enum EstatusFactura {
    PAGADA = 'PAGADA',
    EMITIDA = 'EMITIDA',
    CANCELADA = 'CANCELADA'
}

export const enum MetodoPago {
    CREDITO = 'CREDITO',
    AJUSTE = 'AJUSTE',
    ADECUACION = 'ADECUACION'
}

export interface IFactura {
    id?: number;
    fecha?: Moment;
    detalles?: string;
    estatus?: EstatusFactura;
    metodoPago?: MetodoPago;
    fechaPago?: Moment;
    montoPagado?: number;
    embarques?: IEmbarque[];
    ordenCompra?: IOrdenCompra;
}

export class Factura implements IFactura {
    constructor(
        public id?: number,
        public fecha?: Moment,
        public detalles?: string,
        public estatus?: EstatusFactura,
        public metodoPago?: MetodoPago,
        public fechaPago?: Moment,
        public montoPagado?: number,
        public embarques?: IEmbarque[],
        public ordenCompra?: IOrdenCompra
    ) {}
}
