import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ComprasGobUpCUCOPModule } from './cucop/cucop.module';
import { ComprasGobUpLugarEntregaModule } from './lugar-entrega/lugar-entrega.module';
import { ComprasGobUpDependenciaModule } from './dependencia/dependencia.module';
import { ComprasGobUpProveedorModule } from './proveedor/proveedor.module';
import { ComprasGobUpContratoMarcoModule } from './contrato-marco/contrato-marco.module';
import { ComprasGobUpOrdenCompraModule } from './orden-compra/orden-compra.module';
import { ComprasGobUpElementoOrdenModule } from './elemento-orden/elemento-orden.module';
import { ComprasGobUpFacturaModule } from './factura/factura.module';
import { ComprasGobUpEmbarqueModule } from './embarque/embarque.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ComprasGobUpCUCOPModule,
        ComprasGobUpLugarEntregaModule,
        ComprasGobUpDependenciaModule,
        ComprasGobUpProveedorModule,
        ComprasGobUpContratoMarcoModule,
        ComprasGobUpOrdenCompraModule,
        ComprasGobUpElementoOrdenModule,
        ComprasGobUpFacturaModule,
        ComprasGobUpEmbarqueModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpEntityModule {}
