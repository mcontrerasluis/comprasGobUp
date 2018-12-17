/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { CUCOPUpdateComponent } from 'app/entities/cucop/cucop-update.component';
import { CUCOPService } from 'app/entities/cucop/cucop.service';
import { CUCOP } from 'app/shared/model/cucop.model';

describe('Component Tests', () => {
    describe('CUCOP Management Update Component', () => {
        let comp: CUCOPUpdateComponent;
        let fixture: ComponentFixture<CUCOPUpdateComponent>;
        let service: CUCOPService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [CUCOPUpdateComponent]
            })
                .overrideTemplate(CUCOPUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CUCOPUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CUCOPService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CUCOP(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cUCOP = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CUCOP();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cUCOP = entity;
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
