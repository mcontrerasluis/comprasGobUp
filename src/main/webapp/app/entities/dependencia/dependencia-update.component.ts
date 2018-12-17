import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDependencia } from 'app/shared/model/dependencia.model';
import { DependenciaService } from './dependencia.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-dependencia-update',
    templateUrl: './dependencia-update.component.html'
})
export class DependenciaUpdateComponent implements OnInit {
    dependencia: IDependencia;
    isSaving: boolean;

    users: IUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private dependenciaService: DependenciaService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dependencia }) => {
            this.dependencia = dependencia;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.dependencia.id !== undefined) {
            this.subscribeToSaveResponse(this.dependenciaService.update(this.dependencia));
        } else {
            this.subscribeToSaveResponse(this.dependenciaService.create(this.dependencia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDependencia>>) {
        result.subscribe((res: HttpResponse<IDependencia>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
