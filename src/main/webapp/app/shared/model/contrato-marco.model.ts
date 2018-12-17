import { Moment } from 'moment';
import { IProveedor } from 'app/shared/model//proveedor.model';
import { ICUCOP } from 'app/shared/model//cucop.model';
import { IDependencia } from 'app/shared/model//dependencia.model';

export interface IContratoMarco {
    id?: number;
    autor?: string;
    titulo?: string;
    descripcion?: any;
    fechaVigencia?: Moment;
    monto?: number;
    cantidad?: number;
    imagenContentType?: string;
    imagen?: any;
    contratoContentType?: string;
    contrato?: any;
    anexoContentType?: string;
    anexo?: any;
    convenioContentType?: string;
    convenio?: any;
    requisitosContentType?: string;
    requisitos?: any;
    proveedors?: IProveedor[];
    cUCOP?: ICUCOP;
    dependencia?: IDependencia;
}

export class ContratoMarco implements IContratoMarco {
    constructor(
        public id?: number,
        public autor?: string,
        public titulo?: string,
        public descripcion?: any,
        public fechaVigencia?: Moment,
        public monto?: number,
        public cantidad?: number,
        public imagenContentType?: string,
        public imagen?: any,
        public contratoContentType?: string,
        public contrato?: any,
        public anexoContentType?: string,
        public anexo?: any,
        public convenioContentType?: string,
        public convenio?: any,
        public requisitosContentType?: string,
        public requisitos?: any,
        public proveedors?: IProveedor[],
        public cUCOP?: ICUCOP,
        public dependencia?: IDependencia
    ) {}
}
