import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ILugarEntrega } from 'app/shared/model/lugar-entrega.model';
import { LugarEntregaService } from './lugar-entrega.service';
import { IDependencia } from 'app/shared/model/dependencia.model';
import { DependenciaService } from 'app/entities/dependencia';

@Component({
    selector: 'jhi-lugar-entrega-update',
    templateUrl: './lugar-entrega-update.component.html'
})
export class LugarEntregaUpdateComponent implements OnInit {
    lugarEntrega: ILugarEntrega;
    isSaving: boolean;

    dependencias: IDependencia[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private lugarEntregaService: LugarEntregaService,
        private dependenciaService: DependenciaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ lugarEntrega }) => {
            this.lugarEntrega = lugarEntrega;
        });
        this.dependenciaService.query().subscribe(
            (res: HttpResponse<IDependencia[]>) => {
                this.dependencias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.lugarEntrega.id !== undefined) {
            this.subscribeToSaveResponse(this.lugarEntregaService.update(this.lugarEntrega));
        } else {
            this.subscribeToSaveResponse(this.lugarEntregaService.create(this.lugarEntrega));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILugarEntrega>>) {
        result.subscribe((res: HttpResponse<ILugarEntrega>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDependenciaById(index: number, item: IDependencia) {
        return item.id;
    }
}
