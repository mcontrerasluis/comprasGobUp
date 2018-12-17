import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComprasGobUpSharedModule } from 'app/shared';
import {
    ElementoOrdenComponent,
    ElementoOrdenDetailComponent,
    ElementoOrdenUpdateComponent,
    ElementoOrdenDeletePopupComponent,
    ElementoOrdenDeleteDialogComponent,
    elementoOrdenRoute,
    elementoOrdenPopupRoute
} from './';

const ENTITY_STATES = [...elementoOrdenRoute, ...elementoOrdenPopupRoute];

@NgModule({
    imports: [ComprasGobUpSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ElementoOrdenComponent,
        ElementoOrdenDetailComponent,
        ElementoOrdenUpdateComponent,
        ElementoOrdenDeleteDialogComponent,
        ElementoOrdenDeletePopupComponent
    ],
    entryComponents: [
        ElementoOrdenComponent,
        ElementoOrdenUpdateComponent,
        ElementoOrdenDeleteDialogComponent,
        ElementoOrdenDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpElementoOrdenModule {}
