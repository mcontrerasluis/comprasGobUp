import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Embarque } from 'app/shared/model/embarque.model';
import { EmbarqueService } from './embarque.service';
import { EmbarqueComponent } from './embarque.component';
import { EmbarqueDetailComponent } from './embarque-detail.component';
import { EmbarqueUpdateComponent } from './embarque-update.component';
import { EmbarqueDeletePopupComponent } from './embarque-delete-dialog.component';
import { IEmbarque } from 'app/shared/model/embarque.model';

@Injectable({ providedIn: 'root' })
export class EmbarqueResolve implements Resolve<IEmbarque> {
    constructor(private service: EmbarqueService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Embarque> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Embarque>) => response.ok),
                map((embarque: HttpResponse<Embarque>) => embarque.body)
            );
        }
        return of(new Embarque());
    }
}

export const embarqueRoute: Routes = [
    {
        path: 'embarque',
        component: EmbarqueComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comprasGobUpApp.embarque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'embarque/:id/view',
        component: EmbarqueDetailComponent,
        resolve: {
            embarque: EmbarqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.embarque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'embarque/new',
        component: EmbarqueUpdateComponent,
        resolve: {
            embarque: EmbarqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.embarque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'embarque/:id/edit',
        component: EmbarqueUpdateComponent,
        resolve: {
            embarque: EmbarqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.embarque.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const embarquePopupRoute: Routes = [
    {
        path: 'embarque/:id/delete',
        component: EmbarqueDeletePopupComponent,
        resolve: {
            embarque: EmbarqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.embarque.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
