import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IContratoMarco } from 'app/shared/model/contrato-marco.model';

@Component({
    selector: 'jhi-contrato-marco-detail',
    templateUrl: './contrato-marco-detail.component.html'
})
export class ContratoMarcoDetailComponent implements OnInit {
    contratoMarco: IContratoMarco;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ contratoMarco }) => {
            this.contratoMarco = contratoMarco;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
