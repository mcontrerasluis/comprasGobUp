import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILugarEntrega } from 'app/shared/model/lugar-entrega.model';
import { LugarEntregaService } from './lugar-entrega.service';

@Component({
    selector: 'jhi-lugar-entrega-delete-dialog',
    templateUrl: './lugar-entrega-delete-dialog.component.html'
})
export class LugarEntregaDeleteDialogComponent {
    lugarEntrega: ILugarEntrega;

    constructor(
        private lugarEntregaService: LugarEntregaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.lugarEntregaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'lugarEntregaListModification',
                content: 'Deleted an lugarEntrega'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-lugar-entrega-delete-popup',
    template: ''
})
export class LugarEntregaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ lugarEntrega }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LugarEntregaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.lugarEntrega = lugarEntrega;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
