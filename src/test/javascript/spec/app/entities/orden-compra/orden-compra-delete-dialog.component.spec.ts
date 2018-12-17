/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComprasGobUpTestModule } from '../../../test.module';
import { OrdenCompraDeleteDialogComponent } from 'app/entities/orden-compra/orden-compra-delete-dialog.component';
import { OrdenCompraService } from 'app/entities/orden-compra/orden-compra.service';

describe('Component Tests', () => {
    describe('OrdenCompra Management Delete Component', () => {
        let comp: OrdenCompraDeleteDialogComponent;
        let fixture: ComponentFixture<OrdenCompraDeleteDialogComponent>;
        let service: OrdenCompraService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [OrdenCompraDeleteDialogComponent]
            })
                .overrideTemplate(OrdenCompraDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrdenCompraDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrdenCompraService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
