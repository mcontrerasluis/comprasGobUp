import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComprasGobUpSharedModule } from 'app/shared';
import {
    LugarEntregaComponent,
    LugarEntregaDetailComponent,
    LugarEntregaUpdateComponent,
    LugarEntregaDeletePopupComponent,
    LugarEntregaDeleteDialogComponent,
    lugarEntregaRoute,
    lugarEntregaPopupRoute
} from './';

const ENTITY_STATES = [...lugarEntregaRoute, ...lugarEntregaPopupRoute];

@NgModule({
    imports: [ComprasGobUpSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LugarEntregaComponent,
        LugarEntregaDetailComponent,
        LugarEntregaUpdateComponent,
        LugarEntregaDeleteDialogComponent,
        LugarEntregaDeletePopupComponent
    ],
    entryComponents: [
        LugarEntregaComponent,
        LugarEntregaUpdateComponent,
        LugarEntregaDeleteDialogComponent,
        LugarEntregaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpLugarEntregaModule {}
