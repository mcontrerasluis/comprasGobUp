import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IElementoOrden } from 'app/shared/model/elemento-orden.model';
import { ElementoOrdenService } from './elemento-orden.service';
import { IContratoMarco } from 'app/shared/model/contrato-marco.model';
import { ContratoMarcoService } from 'app/entities/contrato-marco';
import { IProveedor } from 'app/shared/model/proveedor.model';
import { ProveedorService } from 'app/entities/proveedor';
import { IOrdenCompra } from 'app/shared/model/orden-compra.model';
import { OrdenCompraService } from 'app/entities/orden-compra';

@Component({
    selector: 'jhi-elemento-orden-update',
    templateUrl: './elemento-orden-update.component.html'
})
export class ElementoOrdenUpdateComponent implements OnInit {
    elementoOrden: IElementoOrden;
    isSaving: boolean;

    contratomarcos: IContratoMarco[];

    proveedors: IProveedor[];

    ordencompras: IOrdenCompra[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private elementoOrdenService: ElementoOrdenService,
        private contratoMarcoService: ContratoMarcoService,
        private proveedorService: ProveedorService,
        private ordenCompraService: OrdenCompraService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ elementoOrden }) => {
            this.elementoOrden = elementoOrden;
        });
        this.contratoMarcoService.query().subscribe(
            (res: HttpResponse<IContratoMarco[]>) => {
                this.contratomarcos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.proveedorService.query().subscribe(
            (res: HttpResponse<IProveedor[]>) => {
                this.proveedors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.elementoOrden.id !== undefined) {
            this.subscribeToSaveResponse(this.elementoOrdenService.update(this.elementoOrden));
        } else {
            this.subscribeToSaveResponse(this.elementoOrdenService.create(this.elementoOrden));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IElementoOrden>>) {
        result.subscribe((res: HttpResponse<IElementoOrden>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackContratoMarcoById(index: number, item: IContratoMarco) {
        return item.id;
    }

    trackProveedorById(index: number, item: IProveedor) {
        return item.id;
    }

    trackOrdenCompraById(index: number, item: IOrdenCompra) {
        return item.id;
    }
}
