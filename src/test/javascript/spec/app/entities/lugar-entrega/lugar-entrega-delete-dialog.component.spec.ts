/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComprasGobUpTestModule } from '../../../test.module';
import { LugarEntregaDeleteDialogComponent } from 'app/entities/lugar-entrega/lugar-entrega-delete-dialog.component';
import { LugarEntregaService } from 'app/entities/lugar-entrega/lugar-entrega.service';

describe('Component Tests', () => {
    describe('LugarEntrega Management Delete Component', () => {
        let comp: LugarEntregaDeleteDialogComponent;
        let fixture: ComponentFixture<LugarEntregaDeleteDialogComponent>;
        let service: LugarEntregaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [LugarEntregaDeleteDialogComponent]
            })
                .overrideTemplate(LugarEntregaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LugarEntregaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LugarEntregaService);
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
