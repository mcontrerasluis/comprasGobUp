/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { ElementoOrdenUpdateComponent } from 'app/entities/elemento-orden/elemento-orden-update.component';
import { ElementoOrdenService } from 'app/entities/elemento-orden/elemento-orden.service';
import { ElementoOrden } from 'app/shared/model/elemento-orden.model';

describe('Component Tests', () => {
    describe('ElementoOrden Management Update Component', () => {
        let comp: ElementoOrdenUpdateComponent;
        let fixture: ComponentFixture<ElementoOrdenUpdateComponent>;
        let service: ElementoOrdenService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [ElementoOrdenUpdateComponent]
            })
                .overrideTemplate(ElementoOrdenUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ElementoOrdenUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ElementoOrdenService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ElementoOrden(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.elementoOrden = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ElementoOrden();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.elementoOrden = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
