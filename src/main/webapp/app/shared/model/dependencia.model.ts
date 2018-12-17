import { IOrdenCompra } from 'app/shared/model//orden-compra.model';
import { IContratoMarco } from 'app/shared/model//contrato-marco.model';
import { IUser } from 'app/core/user/user.model';
import { ILugarEntrega } from 'app/shared/model//lugar-entrega.model';

export interface IDependencia {
    id?: number;
    ramo?: number;
    nombreRamo?: string;
    unidad?: string;
    nombreUnidad?: string;
    contacto?: string;
    correoElectronico?: string;
    telefono?: string;
    ordenCompras?: IOrdenCompra[];
    contratoMarcos?: IContratoMarco[];
    user?: IUser;
    lugarEntregas?: ILugarEntrega[];
}

export class Dependencia implements IDependencia {
    constructor(
        public id?: number,
        public ramo?: number,
        public nombreRamo?: string,
        public unidad?: string,
        public nombreUnidad?: string,
        public contacto?: string,
        public correoElectronico?: string,
        public telefono?: string,
        public ordenCompras?: IOrdenCompra[],
        public contratoMarcos?: IContratoMarco[],
        public user?: IUser,
        public lugarEntregas?: ILugarEntrega[]
    ) {}
}
