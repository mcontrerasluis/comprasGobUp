import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrdenCompra } from 'app/shared/model/orden-compra.model';
import { OrdenCompraService } from './orden-compra.service';

@Component({
    selector: 'jhi-orden-compra-delete-dialog',
    templateUrl: './orden-compra-delete-dialog.component.html'
})
export class OrdenCompraDeleteDialogComponent {
    ordenCompra: IOrdenCompra;

    constructor(
        private ordenCompraService: OrdenCompraService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ordenCompraService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ordenCompraListModification',
                content: 'Deleted an ordenCompra'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-orden-compra-delete-popup',
    template: ''
})
export class OrdenCompraDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ordenCompra }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OrdenCompraDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.ordenCompra = ordenCompra;
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
