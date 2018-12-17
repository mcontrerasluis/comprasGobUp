import { Moment } from 'moment';
import { IFactura } from 'app/shared/model//factura.model';

export interface IEmbarque {
    id?: number;
    codigoRastreo?: string;
    fecha?: Moment;
    detalles?: string;
    factura?: IFactura;
}

export class Embarque implements IEmbarque {
    constructor(
        public id?: number,
        public codigoRastreo?: string,
        public fecha?: Moment,
        public detalles?: string,
        public factura?: IFactura
    ) {}
}
