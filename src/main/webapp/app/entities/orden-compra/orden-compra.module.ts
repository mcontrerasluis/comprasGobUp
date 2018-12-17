import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComprasGobUpSharedModule } from 'app/shared';
import {
    OrdenCompraComponent,
    OrdenCompraDetailComponent,
    OrdenCompraUpdateComponent,
    OrdenCompraDeletePopupComponent,
    OrdenCompraDeleteDialogComponent,
    ordenCompraRoute,
    ordenCompraPopupRoute
} from './';

const ENTITY_STATES = [...ordenCompraRoute, ...ordenCompraPopupRoute];

@NgModule({
    imports: [ComprasGobUpSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrdenCompraComponent,
        OrdenCompraDetailComponent,
        OrdenCompraUpdateComponent,
        OrdenCompraDeleteDialogComponent,
        OrdenCompraDeletePopupComponent
    ],
    entryComponents: [OrdenCompraComponent, OrdenCompraUpdateComponent, OrdenCompraDeleteDialogComponent, OrdenCompraDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpOrdenCompraModule {}
