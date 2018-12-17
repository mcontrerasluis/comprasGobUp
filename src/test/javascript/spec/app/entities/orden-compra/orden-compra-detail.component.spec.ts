/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { OrdenCompraDetailComponent } from 'app/entities/orden-compra/orden-compra-detail.component';
import { OrdenCompra } from 'app/shared/model/orden-compra.model';

describe('Component Tests', () => {
    describe('OrdenCompra Management Detail Component', () => {
        let comp: OrdenCompraDetailComponent;
        let fixture: ComponentFixture<OrdenCompraDetailComponent>;
        const route = ({ data: of({ ordenCompra: new OrdenCompra(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [OrdenCompraDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OrdenCompraDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrdenCompraDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.ordenCompra).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
