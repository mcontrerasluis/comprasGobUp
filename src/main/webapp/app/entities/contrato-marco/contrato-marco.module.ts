import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComprasGobUpSharedModule } from 'app/shared';
import {
    ContratoMarcoComponent,
    ContratoMarcoDetailComponent,
    ContratoMarcoUpdateComponent,
    ContratoMarcoDeletePopupComponent,
    ContratoMarcoDeleteDialogComponent,
    contratoMarcoRoute,
    contratoMarcoPopupRoute
} from './';

const ENTITY_STATES = [...contratoMarcoRoute, ...contratoMarcoPopupRoute];

@NgModule({
    imports: [ComprasGobUpSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ContratoMarcoComponent,
        ContratoMarcoDetailComponent,
        ContratoMarcoUpdateComponent,
        ContratoMarcoDeleteDialogComponent,
        ContratoMarcoDeletePopupComponent
    ],
    entryComponents: [
        ContratoMarcoComponent,
        ContratoMarcoUpdateComponent,
        ContratoMarcoDeleteDialogComponent,
        ContratoMarcoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpContratoMarcoModule {}
