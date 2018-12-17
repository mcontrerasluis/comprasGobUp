/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComprasGobUpTestModule } from '../../../test.module';
import { ElementoOrdenDeleteDialogComponent } from 'app/entities/elemento-orden/elemento-orden-delete-dialog.component';
import { ElementoOrdenService } from 'app/entities/elemento-orden/elemento-orden.service';

describe('Component Tests', () => {
    describe('ElementoOrden Management Delete Component', () => {
        let comp: ElementoOrdenDeleteDialogComponent;
        let fixture: ComponentFixture<ElementoOrdenDeleteDialogComponent>;
        let service: ElementoOrdenService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [ElementoOrdenDeleteDialogComponent]
            })
                .overrideTemplate(ElementoOrdenDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ElementoOrdenDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElementoOrdenService);
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
