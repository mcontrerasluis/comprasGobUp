import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ICUCOP } from 'app/shared/model/cucop.model';

@Component({
    selector: 'jhi-cucop-detail',
    templateUrl: './cucop-detail.component.html'
})
export class CUCOPDetailComponent implements OnInit {
    cUCOP: ICUCOP;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
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
    previousState() {
        window.history.back();
    }
}
