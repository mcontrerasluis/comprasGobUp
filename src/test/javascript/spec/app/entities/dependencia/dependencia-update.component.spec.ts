/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { DependenciaUpdateComponent } from 'app/entities/dependencia/dependencia-update.component';
import { DependenciaService } from 'app/entities/dependencia/dependencia.service';
import { Dependencia } from 'app/shared/model/dependencia.model';

describe('Component Tests', () => {
    describe('Dependencia Management Update Component', () => {
        let comp: DependenciaUpdateComponent;
        let fixture: ComponentFixture<DependenciaUpdateComponent>;
        let service: DependenciaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [DependenciaUpdateComponent]
            })
                .overrideTemplate(DependenciaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DependenciaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DependenciaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Dependencia(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dependencia = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Dependencia();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.dependencia = entity;
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
