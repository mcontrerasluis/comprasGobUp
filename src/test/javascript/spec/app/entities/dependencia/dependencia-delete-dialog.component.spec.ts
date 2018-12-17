/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComprasGobUpTestModule } from '../../../test.module';
import { DependenciaDeleteDialogComponent } from 'app/entities/dependencia/dependencia-delete-dialog.component';
import { DependenciaService } from 'app/entities/dependencia/dependencia.service';

describe('Component Tests', () => {
    describe('Dependencia Management Delete Component', () => {
        let comp: DependenciaDeleteDialogComponent;
        let fixture: ComponentFixture<DependenciaDeleteDialogComponent>;
        let service: DependenciaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [DependenciaDeleteDialogComponent]
            })
                .overrideTemplate(DependenciaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DependenciaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DependenciaService);
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
