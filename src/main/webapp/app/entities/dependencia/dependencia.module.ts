import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ComprasGobUpSharedModule } from 'app/shared';
import { ComprasGobUpAdminModule } from 'app/admin/admin.module';
import {
    DependenciaComponent,
    DependenciaDetailComponent,
    DependenciaUpdateComponent,
    DependenciaDeletePopupComponent,
    DependenciaDeleteDialogComponent,
    dependenciaRoute,
    dependenciaPopupRoute
} from './';

const ENTITY_STATES = [...dependenciaRoute, ...dependenciaPopupRoute];

@NgModule({
    imports: [ComprasGobUpSharedModule, ComprasGobUpAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DependenciaComponent,
        DependenciaDetailComponent,
        DependenciaUpdateComponent,
        DependenciaDeleteDialogComponent,
        DependenciaDeletePopupComponent
    ],
    entryComponents: [DependenciaComponent, DependenciaUpdateComponent, DependenciaDeleteDialogComponent, DependenciaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ComprasGobUpDependenciaModule {}
