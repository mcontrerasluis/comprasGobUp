import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEmbarque } from 'app/shared/model/embarque.model';
import { EmbarqueService } from './embarque.service';
import { IFactura } from 'app/shared/model/factura.model';
import { FacturaService } from 'app/entities/factura';

@Component({
    selector: 'jhi-embarque-update',
    templateUrl: './embarque-update.component.html'
})
export class EmbarqueUpdateComponent implements OnInit {
    embarque: IEmbarque;
    isSaving: boolean;

    facturas: IFactura[];
    fecha: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private embarqueService: EmbarqueService,
        private facturaService: FacturaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ embarque }) => {
            this.embarque = embarque;
            this.fecha = this.embarque.fecha != null ? this.embarque.fecha.format(DATE_TIME_FORMAT) : null;
        });
        this.facturaService.query().subscribe(
            (res: HttpResponse<IFactura[]>) => {
                this.facturas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.embarque.fecha = this.fecha != null ? moment(this.fecha, DATE_TIME_FORMAT) : null;
        if (this.embarque.id !== undefined) {
            this.subscribeToSaveResponse(this.embarqueService.update(this.embarque));
        } else {
            this.subscribeToSaveResponse(this.embarqueService.create(this.embarque));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEmbarque>>) {
        result.subscribe((res: HttpResponse<IEmbarque>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFacturaById(index: number, item: IFactura) {
        return item.id;
    }
}
