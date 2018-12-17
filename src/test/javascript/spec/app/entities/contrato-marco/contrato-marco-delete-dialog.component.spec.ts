/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComprasGobUpTestModule } from '../../../test.module';
import { ContratoMarcoDeleteDialogComponent } from 'app/entities/contrato-marco/contrato-marco-delete-dialog.component';
import { ContratoMarcoService } from 'app/entities/contrato-marco/contrato-marco.service';

describe('Component Tests', () => {
    describe('ContratoMarco Management Delete Component', () => {
        let comp: ContratoMarcoDeleteDialogComponent;
        let fixture: ComponentFixture<ContratoMarcoDeleteDialogComponent>;
        let service: ContratoMarcoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [ContratoMarcoDeleteDialogComponent]
            })
                .overrideTemplate(ContratoMarcoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ContratoMarcoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContratoMarcoService);
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
