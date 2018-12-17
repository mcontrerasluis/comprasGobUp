import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrdenCompra } from 'app/shared/model/orden-compra.model';

@Component({
    selector: 'jhi-orden-compra-detail',
    templateUrl: './orden-compra-detail.component.html'
})
export class OrdenCompraDetailComponent implements OnInit {
    ordenCompra: IOrdenCompra;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ordenCompra }) => {
            this.ordenCompra = ordenCompra;
        });
    }

    previousState() {
        window.history.back();
    }
}
