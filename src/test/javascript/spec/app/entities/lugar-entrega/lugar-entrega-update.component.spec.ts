/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { LugarEntregaUpdateComponent } from 'app/entities/lugar-entrega/lugar-entrega-update.component';
import { LugarEntregaService } from 'app/entities/lugar-entrega/lugar-entrega.service';
import { LugarEntrega } from 'app/shared/model/lugar-entrega.model';

describe('Component Tests', () => {
    describe('LugarEntrega Management Update Component', () => {
        let comp: LugarEntregaUpdateComponent;
        let fixture: ComponentFixture<LugarEntregaUpdateComponent>;
        let service: LugarEntregaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [LugarEntregaUpdateComponent]
            })
                .overrideTemplate(LugarEntregaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LugarEntregaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LugarEntregaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new LugarEntrega(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.lugarEntrega = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new LugarEntrega();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.lugarEntrega = entity;
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
