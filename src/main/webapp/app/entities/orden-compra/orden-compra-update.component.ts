import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IOrdenCompra } from 'app/shared/model/orden-compra.model';
import { OrdenCompraService } from './orden-compra.service';
import { ILugarEntrega } from 'app/shared/model/lugar-entrega.model';
import { LugarEntregaService } from 'app/entities/lugar-entrega';
import { IDependencia } from 'app/shared/model/dependencia.model';
import { DependenciaService } from 'app/entities/dependencia';

@Component({
    selector: 'jhi-orden-compra-update',
    templateUrl: './orden-compra-update.component.html'
})
export class OrdenCompraUpdateComponent implements OnInit {
    ordenCompra: IOrdenCompra;
    isSaving: boolean;

    lugarentregas: ILugarEntrega[];

    dependencias: IDependencia[];
    fechaEntrada: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private ordenCompraService: OrdenCompraService,
        private lugarEntregaService: LugarEntregaService,
        private dependenciaService: DependenciaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ordenCompra }) => {
            this.ordenCompra = ordenCompra;
            this.fechaEntrada = this.ordenCompra.fechaEntrada != null ? this.ordenCompra.fechaEntrada.format(DATE_TIME_FORMAT) : null;
        });
        this.lugarEntregaService.query().subscribe(
            (res: HttpResponse<ILugarEntrega[]>) => {
                this.lugarentregas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        this.ordenCompra.fechaEntrada = this.fechaEntrada != null ? moment(this.fechaEntrada, DATE_TIME_FORMAT) : null;
        if (this.ordenCompra.id !== undefined) {
            this.subscribeToSaveResponse(this.ordenCompraService.update(this.ordenCompra));
        } else {
            this.subscribeToSaveResponse(this.ordenCompraService.create(this.ordenCompra));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrdenCompra>>) {
        result.subscribe((res: HttpResponse<IOrdenCompra>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackLugarEntregaById(index: number, item: ILugarEntrega) {
        return item.id;
    }

    trackDependenciaById(index: number, item: IDependencia) {
        return item.id;
    }
}
