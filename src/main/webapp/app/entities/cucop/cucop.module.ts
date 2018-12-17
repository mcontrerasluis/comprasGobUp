import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComprasGobUpSharedModule } from 'app/shared';
import {
    CUCOPComponent,
    CUCOPDetailComponent,
    CUCOPUpdateComponent,
    CUCOPDeletePopupComponent,
    CUCOPDeleteDialogComponent,
    cUCOPRoute,
    cUCOPPopupRoute
} from './';

const ENTITY_STATES = [...cUCOPRoute, ...cUCOPPopupRoute];

@NgModule({
    imports: [ComprasGobUpSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CUCOPComponent, CUCOPDetailComponent, CUCOPUpdateComponent, CUCOPDeleteDialogComponent, CUCOPDeletePopupComponent],
    entryComponents: [CUCOPComponent, CUCOPUpdateComponent, CUCOPDeleteDialogComponent, CUCOPDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpCUCOPModule {}
