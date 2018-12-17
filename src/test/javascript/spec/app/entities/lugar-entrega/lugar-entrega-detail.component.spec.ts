/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { LugarEntregaDetailComponent } from 'app/entities/lugar-entrega/lugar-entrega-detail.component';
import { LugarEntrega } from 'app/shared/model/lugar-entrega.model';

describe('Component Tests', () => {
    describe('LugarEntrega Management Detail Component', () => {
        let comp: LugarEntregaDetailComponent;
        let fixture: ComponentFixture<LugarEntregaDetailComponent>;
        const route = ({ data: of({ lugarEntrega: new LugarEntrega(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [LugarEntregaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LugarEntregaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LugarEntregaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.lugarEntrega).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
