import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElementoOrden } from 'app/shared/model/elemento-orden.model';

@Component({
    selector: 'jhi-elemento-orden-detail',
    templateUrl: './elemento-orden-detail.component.html'
})
export class ElementoOrdenDetailComponent implements OnInit {
    elementoOrden: IElementoOrden;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ elementoOrden }) => {
            this.elementoOrden = elementoOrden;
        });
    }

    previousState() {
        window.history.back();
    }
}
