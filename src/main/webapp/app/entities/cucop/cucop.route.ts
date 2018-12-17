import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CUCOP } from 'app/shared/model/cucop.model';
import { CUCOPService } from './cucop.service';
import { CUCOPComponent } from './cucop.component';
import { CUCOPDetailComponent } from './cucop-detail.component';
import { CUCOPUpdateComponent } from './cucop-update.component';
import { CUCOPDeletePopupComponent } from './cucop-delete-dialog.component';
import { ICUCOP } from 'app/shared/model/cucop.model';

@Injectable({ providedIn: 'root' })
export class CUCOPResolve implements Resolve<ICUCOP> {
    constructor(private service: CUCOPService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CUCOP> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CUCOP>) => response.ok),
                map((cUCOP: HttpResponse<CUCOP>) => cUCOP.body)
            );
        }
        return of(new CUCOP());
    }
}

export const cUCOPRoute: Routes = [
    {
        path: 'cucop',
        component: CUCOPComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comprasGobUpApp.cUCOP.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cucop/:id/view',
        component: CUCOPDetailComponent,
        resolve: {
            cUCOP: CUCOPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.cUCOP.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cucop/new',
        component: CUCOPUpdateComponent,
        resolve: {
            cUCOP: CUCOPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.cUCOP.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cucop/:id/edit',
        component: CUCOPUpdateComponent,
        resolve: {
            cUCOP: CUCOPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.cUCOP.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cUCOPPopupRoute: Routes = [
    {
        path: 'cucop/:id/delete',
        component: CUCOPDeletePopupComponent,
        resolve: {
            cUCOP: CUCOPResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comprasGobUpApp.cUCOP.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
