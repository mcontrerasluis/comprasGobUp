import { IContratoMarco } from 'app/shared/model//contrato-marco.model';

export interface ICUCOP {
    id?: number;
    tipo?: number;
    claveCUCOP?: number;
    partidaEsp?: number;
    descripcion?: string;
    nivel?: number;
    cABM?: string;
    unidadMed?: string;
    tipoContrata?: string;
    imagenContentType?: string;
    imagen?: any;
    contratoMarcos?: IContratoMarco[];
}

export class CUCOP implements ICUCOP {
    constructor(
        public id?: number,
        public tipo?: number,
        public claveCUCOP?: number,
        public partidaEsp?: number,
        public descripcion?: string,
        public nivel?: number,
        public cABM?: string,
        public unidadMed?: string,
        public tipoContrata?: string,
        public imagenContentType?: string,
        public imagen?: any,
        public contratoMarcos?: IContratoMarco[]
    ) {}
}
