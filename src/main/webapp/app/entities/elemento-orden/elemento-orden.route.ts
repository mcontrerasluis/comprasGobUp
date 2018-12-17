import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ElementoOrden } from 'app/shared/model/elemento-orden.model';
import { ElementoOrdenService } from './elemento-orden.service';
import { ElementoOrdenComponent } from './elemento-orden.component';
import { ElementoOrdenDetailComponent } from './elemento-orden-detail.component';
import { ElementoOrdenUpdateComponent } from './elemento-orden-update.component';
import { ElementoOrdenDeletePopupComponent } from './elemento-orden-delete-dialog.component';
import { IElementoOrden } from 'app/shared/model/elemento-orden.model';

@Injectable({ providedIn: 'root' })
export class ElementoOrdenResolve implements Resolve<IElementoOrden> {
    constructor(private service: ElementoOrdenService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ElementoOrden> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ElementoOrden>) => response.ok),
                map((elementoOrden: HttpResponse<ElementoOrden>) => elementoOrden.body)
            );
        }
        return of(new ElementoOrden());
    }
}

export const elementoOrdenRoute: Routes = [
    {
        path: 'elemento-orden',
        component: ElementoOrdenComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comprasGobUpApp.elementoOrden.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'elemento-orden/:id/view',
        component: ElementoOrdenDetailComponent,
        resolve: {
            elementoOrden: ElementoOrdenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.elementoOrden.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'elemento-orden/new',
        component: ElementoOrdenUpdateComponent,
        resolve: {
            elementoOrden: ElementoOrdenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.elementoOrden.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'elemento-orden/:id/edit',
        component: ElementoOrdenUpdateComponent,
        resolve: {
            elementoOrden: ElementoOrdenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.elementoOrden.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const elementoOrdenPopupRoute: Routes = [
    {
        path: 'elemento-orden/:id/delete',
        component: ElementoOrdenDeletePopupComponent,
        resolve: {
            elementoOrden: ElementoOrdenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.elementoOrden.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
