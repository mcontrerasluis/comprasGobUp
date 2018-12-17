import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILugarEntrega } from 'app/shared/model/lugar-entrega.model';

@Component({
    selector: 'jhi-lugar-entrega-detail',
    templateUrl: './lugar-entrega-detail.component.html'
})
export class LugarEntregaDetailComponent implements OnInit {
    lugarEntrega: ILugarEntrega;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ lugarEntrega }) => {
            this.lugarEntrega = lugarEntrega;
        });
    }

    previousState() {
        window.history.back();
    }
}
