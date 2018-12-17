import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElementoOrden } from 'app/shared/model/elemento-orden.model';
import { ElementoOrdenService } from './elemento-orden.service';

@Component({
    selector: 'jhi-elemento-orden-delete-dialog',
    templateUrl: './elemento-orden-delete-dialog.component.html'
})
export class ElementoOrdenDeleteDialogComponent {
    elementoOrden: IElementoOrden;

    constructor(
        private elementoOrdenService: ElementoOrdenService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.elementoOrdenService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'elementoOrdenListModification',
                content: 'Deleted an elementoOrden'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-elemento-orden-delete-popup',
    template: ''
})
export class ElementoOrdenDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ elementoOrden }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ElementoOrdenDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.elementoOrden = elementoOrden;
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
