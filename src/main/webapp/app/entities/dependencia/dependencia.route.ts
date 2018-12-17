import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Dependencia } from 'app/shared/model/dependencia.model';
import { DependenciaService } from './dependencia.service';
import { DependenciaComponent } from './dependencia.component';
import { DependenciaDetailComponent } from './dependencia-detail.component';
import { DependenciaUpdateComponent } from './dependencia-update.component';
import { DependenciaDeletePopupComponent } from './dependencia-delete-dialog.component';
import { IDependencia } from 'app/shared/model/dependencia.model';

@Injectable({ providedIn: 'root' })
export class DependenciaResolve implements Resolve<IDependencia> {
    constructor(private service: DependenciaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Dependencia> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Dependencia>) => response.ok),
                map((dependencia: HttpResponse<Dependencia>) => dependencia.body)
            );
        }
        return of(new Dependencia());
    }
}

export const dependenciaRoute: Routes = [
    {
        path: 'dependencia',
        component: DependenciaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comprasGobUpApp.dependencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dependencia/:id/view',
        component: DependenciaDetailComponent,
        resolve: {
            dependencia: DependenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.dependencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dependencia/new',
        component: DependenciaUpdateComponent,
        resolve: {
            dependencia: DependenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.dependencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dependencia/:id/edit',
        component: DependenciaUpdateComponent,
        resolve: {
            dependencia: DependenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.dependencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dependenciaPopupRoute: Routes = [
    {
        path: 'dependencia/:id/delete',
        component: DependenciaDeletePopupComponent,
        resolve: {
            dependencia: DependenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.dependencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
