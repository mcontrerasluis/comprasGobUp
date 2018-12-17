import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OrdenCompra } from 'app/shared/model/orden-compra.model';
import { OrdenCompraService } from './orden-compra.service';
import { OrdenCompraComponent } from './orden-compra.component';
import { OrdenCompraDetailComponent } from './orden-compra-detail.component';
import { OrdenCompraUpdateComponent } from './orden-compra-update.component';
import { OrdenCompraDeletePopupComponent } from './orden-compra-delete-dialog.component';
import { IOrdenCompra } from 'app/shared/model/orden-compra.model';

@Injectable({ providedIn: 'root' })
export class OrdenCompraResolve implements Resolve<IOrdenCompra> {
    constructor(private service: OrdenCompraService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OrdenCompra> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OrdenCompra>) => response.ok),
                map((ordenCompra: HttpResponse<OrdenCompra>) => ordenCompra.body)
            );
        }
        return of(new OrdenCompra());
    }
}

export const ordenCompraRoute: Routes = [
    {
        path: 'orden-compra',
        component: OrdenCompraComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comprasGobUpApp.ordenCompra.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'orden-compra/:id/view',
        component: OrdenCompraDetailComponent,
        resolve: {
            ordenCompra: OrdenCompraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.ordenCompra.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'orden-compra/new',
        component: OrdenCompraUpdateComponent,
        resolve: {
            ordenCompra: OrdenCompraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.ordenCompra.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'orden-compra/:id/edit',
        component: OrdenCompraUpdateComponent,
        resolve: {
            ordenCompra: OrdenCompraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.ordenCompra.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ordenCompraPopupRoute: Routes = [
    {
        path: 'orden-compra/:id/delete',
        component: OrdenCompraDeletePopupComponent,
        resolve: {
            ordenCompra: OrdenCompraResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.ordenCompra.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
