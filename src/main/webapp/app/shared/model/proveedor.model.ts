import { IUser } from 'app/core/user/user.model';
import { IContratoMarco } from 'app/shared/model//contrato-marco.model';

export interface IProveedor {
    id?: number;
    nombre?: string;
    razonSocial?: string;
    rFC?: string;
    domicilioFiscal?: string;
    personaAutorizada?: string;
    correoElectronico?: string;
    telefonoContacto?: string;
    telefonoContactoDos?: string;
    user?: IUser;
    contratoMarcos?: IContratoMarco[];
}

export class Proveedor implements IProveedor {
    constructor(
        public id?: number,
        public nombre?: string,
        public razonSocial?: string,
        public rFC?: string,
        public domicilioFiscal?: string,
        public personaAutorizada?: string,
        public correoElectronico?: string,
        public telefonoContacto?: string,
        public telefonoContactoDos?: string,
        public user?: IUser,
        public contratoMarcos?: IContratoMarco[]
    ) {}
}
