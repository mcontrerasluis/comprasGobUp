import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDependencia } from 'app/shared/model/dependencia.model';

@Component({
    selector: 'jhi-dependencia-detail',
    templateUrl: './dependencia-detail.component.html'
})
export class DependenciaDetailComponent implements OnInit {
    dependencia: IDependencia;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dependencia }) => {
            this.dependencia = dependencia;
        });
    }

    previousState() {
        window.history.back();
    }
}
