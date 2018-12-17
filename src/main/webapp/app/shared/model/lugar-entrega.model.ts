import { IDependencia } from 'app/shared/model//dependencia.model';

export interface ILugarEntrega {
    id?: number;
    descripcion?: string;
    estado?: string;
    municipio?: string;
    direccion?: string;
    latitud?: string;
    longitud?: string;
    dependencia?: IDependencia;
}

export class LugarEntrega implements ILugarEntrega {
    constructor(
        public id?: number,
        public descripcion?: string,
        public estado?: string,
        public municipio?: string,
        public direccion?: string,
        public latitud?: string,
        public longitud?: string,
        public dependencia?: IDependencia
    ) {}
}
