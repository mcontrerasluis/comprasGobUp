/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { OrdenCompraUpdateComponent } from 'app/entities/orden-compra/orden-compra-update.component';
import { OrdenCompraService } from 'app/entities/orden-compra/orden-compra.service';
import { OrdenCompra } from 'app/shared/model/orden-compra.model';

describe('Component Tests', () => {
    describe('OrdenCompra Management Update Component', () => {
        let comp: OrdenCompraUpdateComponent;
        let fixture: ComponentFixture<OrdenCompraUpdateComponent>;
        let service: OrdenCompraService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [OrdenCompraUpdateComponent]
            })
                .overrideTemplate(OrdenCompraUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OrdenCompraUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrdenCompraService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new OrdenCompra(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ordenCompra = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new OrdenCompra();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.ordenCompra = entity;
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
