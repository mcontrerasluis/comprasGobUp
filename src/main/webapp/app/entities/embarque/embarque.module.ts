import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComprasGobUpSharedModule } from 'app/shared';
import {
    EmbarqueComponent,
    EmbarqueDetailComponent,
    EmbarqueUpdateComponent,
    EmbarqueDeletePopupComponent,
    EmbarqueDeleteDialogComponent,
    embarqueRoute,
    embarquePopupRoute
} from './';

const ENTITY_STATES = [...embarqueRoute, ...embarquePopupRoute];

@NgModule({
    imports: [ComprasGobUpSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EmbarqueComponent,
        EmbarqueDetailComponent,
        EmbarqueUpdateComponent,
        EmbarqueDeleteDialogComponent,
        EmbarqueDeletePopupComponent
    ],
    entryComponents: [EmbarqueComponent, EmbarqueUpdateComponent, EmbarqueDeleteDialogComponent, EmbarqueDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpEmbarqueModule {}
