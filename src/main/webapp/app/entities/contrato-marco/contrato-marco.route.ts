import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ContratoMarco } from 'app/shared/model/contrato-marco.model';
import { ContratoMarcoService } from './contrato-marco.service';
import { ContratoMarcoComponent } from './contrato-marco.component';
import { ContratoMarcoDetailComponent } from './contrato-marco-detail.component';
import { ContratoMarcoUpdateComponent } from './contrato-marco-update.component';
import { ContratoMarcoDeletePopupComponent } from './contrato-marco-delete-dialog.component';
import { IContratoMarco } from 'app/shared/model/contrato-marco.model';

@Injectable({ providedIn: 'root' })
export class ContratoMarcoResolve implements Resolve<IContratoMarco> {
    constructor(private service: ContratoMarcoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ContratoMarco> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ContratoMarco>) => response.ok),
                map((contratoMarco: HttpResponse<ContratoMarco>) => contratoMarco.body)
            );
        }
        return of(new ContratoMarco());
    }
}

export const contratoMarcoRoute: Routes = [
    {
        path: 'contrato-marco',
        component: ContratoMarcoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comprasGobUpApp.contratoMarco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'contrato-marco/:id/view',
        component: ContratoMarcoDetailComponent,
        resolve: {
            contratoMarco: ContratoMarcoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.contratoMarco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'contrato-marco/new',
        component: ContratoMarcoUpdateComponent,
        resolve: {
            contratoMarco: ContratoMarcoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.contratoMarco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'contrato-marco/:id/edit',
        component: ContratoMarcoUpdateComponent,
        resolve: {
            contratoMarco: ContratoMarcoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.contratoMarco.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const contratoMarcoPopupRoute: Routes = [
    {
        path: 'contrato-marco/:id/delete',
        component: ContratoMarcoDeletePopupComponent,
        resolve: {
            contratoMarco: ContratoMarcoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.contratoMarco.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
