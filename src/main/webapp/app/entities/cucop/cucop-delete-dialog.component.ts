import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICUCOP } from 'app/shared/model/cucop.model';
import { CUCOPService } from './cucop.service';

@Component({
    selector: 'jhi-cucop-delete-dialog',
    templateUrl: './cucop-delete-dialog.component.html'
})
export class CUCOPDeleteDialogComponent {
    cUCOP: ICUCOP;

    constructor(private cUCOPService: CUCOPService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cUCOPService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cUCOPListModification',
                content: 'Deleted an cUCOP'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cucop-delete-popup',
    template: ''
})
export class CUCOPDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cUCOP }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CUCOPDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cUCOP = cUCOP;
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
