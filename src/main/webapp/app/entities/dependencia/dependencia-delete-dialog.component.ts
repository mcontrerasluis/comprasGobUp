import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDependencia } from 'app/shared/model/dependencia.model';
import { DependenciaService } from './dependencia.service';

@Component({
    selector: 'jhi-dependencia-delete-dialog',
    templateUrl: './dependencia-delete-dialog.component.html'
})
export class DependenciaDeleteDialogComponent {
    dependencia: IDependencia;

    constructor(
        private dependenciaService: DependenciaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dependenciaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'dependenciaListModification',
                content: 'Deleted an dependencia'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dependencia-delete-popup',
    template: ''
})
export class DependenciaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ dependencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DependenciaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.dependencia = dependencia;
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
