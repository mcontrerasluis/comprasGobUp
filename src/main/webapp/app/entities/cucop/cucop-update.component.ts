import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { ICUCOP } from 'app/shared/model/cucop.model';
import { CUCOPService } from './cucop.service';

@Component({
    selector: 'jhi-cucop-update',
    templateUrl: './cucop-update.component.html'
})
export class CUCOPUpdateComponent implements OnInit {
    cUCOP: ICUCOP;
    isSaving: boolean;

    constructor(
        private dataUtils: JhiDataUtils,
        private cUCOPService: CUCOPService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cUCOP }) => {
            this.cUCOP = cUCOP;
        });
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
        this.dataUtils.clearInputImage(this.cUCOP, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cUCOP.id !== undefined) {
            this.subscribeToSaveResponse(this.cUCOPService.update(this.cUCOP));
        } else {
            this.subscribeToSaveResponse(this.cUCOPService.create(this.cUCOP));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICUCOP>>) {
        result.subscribe((res: HttpResponse<ICUCOP>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
