import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IFactura } from 'app/shared/model/factura.model';
import { FacturaService } from './factura.service';
import { IOrdenCompra } from 'app/shared/model/orden-compra.model';
import { OrdenCompraService } from 'app/entities/orden-compra';

@Component({
    selector: 'jhi-factura-update',
    templateUrl: './factura-update.component.html'
})
export class FacturaUpdateComponent implements OnInit {
    factura: IFactura;
    isSaving: boolean;

    ordencompras: IOrdenCompra[];
    fecha: string;
    fechaPago: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private facturaService: FacturaService,
        private ordenCompraService: OrdenCompraService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ factura }) => {
            this.factura = factura;
            this.fecha = this.factura.fecha != null ? this.factura.fecha.format(DATE_TIME_FORMAT) : null;
            this.fechaPago = this.factura.fechaPago != null ? this.factura.fechaPago.format(DATE_TIME_FORMAT) : null;
        });
        this.ordenCompraService.query().subscribe(
            (res: HttpResponse<IOrdenCompra[]>) => {
                this.ordencompras = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.factura.fecha = this.fecha != null ? moment(this.fecha, DATE_TIME_FORMAT) : null;
        this.factura.fechaPago = this.fechaPago != null ? moment(this.fechaPago, DATE_TIME_FORMAT) : null;
        if (this.factura.id !== undefined) {
            this.subscribeToSaveResponse(this.facturaService.update(this.factura));
        } else {
            this.subscribeToSaveResponse(this.facturaService.create(this.factura));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFactura>>) {
        result.subscribe((res: HttpResponse<IFactura>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackOrdenCompraById(index: number, item: IOrdenCompra) {
        return item.id;
    }
}
