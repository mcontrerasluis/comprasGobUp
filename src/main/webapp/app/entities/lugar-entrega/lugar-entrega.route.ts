import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LugarEntrega } from 'app/shared/model/lugar-entrega.model';
import { LugarEntregaService } from './lugar-entrega.service';
import { LugarEntregaComponent } from './lugar-entrega.component';
import { LugarEntregaDetailComponent } from './lugar-entrega-detail.component';
import { LugarEntregaUpdateComponent } from './lugar-entrega-update.component';
import { LugarEntregaDeletePopupComponent } from './lugar-entrega-delete-dialog.component';
import { ILugarEntrega } from 'app/shared/model/lugar-entrega.model';

@Injectable({ providedIn: 'root' })
export class LugarEntregaResolve implements Resolve<ILugarEntrega> {
    constructor(private service: LugarEntregaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LugarEntrega> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LugarEntrega>) => response.ok),
                map((lugarEntrega: HttpResponse<LugarEntrega>) => lugarEntrega.body)
            );
        }
        return of(new LugarEntrega());
    }
}

export const lugarEntregaRoute: Routes = [
    {
        path: 'lugar-entrega',
        component: LugarEntregaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comprasGobUpApp.lugarEntrega.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lugar-entrega/:id/view',
        component: LugarEntregaDetailComponent,
        resolve: {
            lugarEntrega: LugarEntregaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.lugarEntrega.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lugar-entrega/new',
        component: LugarEntregaUpdateComponent,
        resolve: {
            lugarEntrega: LugarEntregaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.lugarEntrega.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'lugar-entrega/:id/edit',
        component: LugarEntregaUpdateComponent,
        resolve: {
            lugarEntrega: LugarEntregaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.lugarEntrega.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const lugarEntregaPopupRoute: Routes = [
    {
        path: 'lugar-entrega/:id/delete',
        component: LugarEntregaDeletePopupComponent,
        resolve: {
            lugarEntrega: LugarEntregaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.lugarEntrega.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
