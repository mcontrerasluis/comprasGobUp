import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IContratoMarco } from 'app/shared/model/contrato-marco.model';
import { ContratoMarcoService } from './contrato-marco.service';
import { IProveedor } from 'app/shared/model/proveedor.model';
import { ProveedorService } from 'app/entities/proveedor';
import { ICUCOP } from 'app/shared/model/cucop.model';
import { CUCOPService } from 'app/entities/cucop';
import { IDependencia } from 'app/shared/model/dependencia.model';
import { DependenciaService } from 'app/entities/dependencia';

@Component({
    selector: 'jhi-contrato-marco-update',
    templateUrl: './contrato-marco-update.component.html'
})
export class ContratoMarcoUpdateComponent implements OnInit {
    contratoMarco: IContratoMarco;
    isSaving: boolean;

    proveedors: IProveedor[];

    cucops: ICUCOP[];

    dependencias: IDependencia[];
    fechaVigenciaDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private contratoMarcoService: ContratoMarcoService,
        private proveedorService: ProveedorService,
        private cUCOPService: CUCOPService,
        private dependenciaService: DependenciaService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ contratoMarco }) => {
            this.contratoMarco = contratoMarco;
        });
        this.proveedorService.query().subscribe(
            (res: HttpResponse<IProveedor[]>) => {
                this.proveedors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.cUCOPService.query().subscribe(
            (res: HttpResponse<ICUCOP[]>) => {
                this.cucops = res.body;
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

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.contratoMarco, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.contratoMarco.id !== undefined) {
            this.subscribeToSaveResponse(this.contratoMarcoService.update(this.contratoMarco));
        } else {
            this.subscribeToSaveResponse(this.contratoMarcoService.create(this.contratoMarco));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IContratoMarco>>) {
        result.subscribe((res: HttpResponse<IContratoMarco>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackProveedorById(index: number, item: IProveedor) {
        return item.id;
    }

    trackCUCOPById(index: number, item: ICUCOP) {
        return item.id;
    }

    trackDependenciaById(index: number, item: IDependencia) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
