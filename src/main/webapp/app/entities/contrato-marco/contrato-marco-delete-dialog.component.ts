import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContratoMarco } from 'app/shared/model/contrato-marco.model';
import { ContratoMarcoService } from './contrato-marco.service';

@Component({
    selector: 'jhi-contrato-marco-delete-dialog',
    templateUrl: './contrato-marco-delete-dialog.component.html'
})
export class ContratoMarcoDeleteDialogComponent {
    contratoMarco: IContratoMarco;

    constructor(
        private contratoMarcoService: ContratoMarcoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.contratoMarcoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'contratoMarcoListModification',
                content: 'Deleted an contratoMarco'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-contrato-marco-delete-popup',
    template: ''
})
export class ContratoMarcoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ contratoMarco }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ContratoMarcoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.contratoMarco = contratoMarco;
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
