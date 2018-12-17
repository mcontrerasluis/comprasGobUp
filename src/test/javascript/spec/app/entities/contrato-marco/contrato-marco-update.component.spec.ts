/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { ContratoMarcoUpdateComponent } from 'app/entities/contrato-marco/contrato-marco-update.component';
import { ContratoMarcoService } from 'app/entities/contrato-marco/contrato-marco.service';
import { ContratoMarco } from 'app/shared/model/contrato-marco.model';

describe('Component Tests', () => {
    describe('ContratoMarco Management Update Component', () => {
        let comp: ContratoMarcoUpdateComponent;
        let fixture: ComponentFixture<ContratoMarcoUpdateComponent>;
        let service: ContratoMarcoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [ContratoMarcoUpdateComponent]
            })
                .overrideTemplate(ContratoMarcoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ContratoMarcoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContratoMarcoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ContratoMarco(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.contratoMarco = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ContratoMarco();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.contratoMarco = entity;
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
