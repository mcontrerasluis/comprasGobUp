/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { CUCOPDetailComponent } from 'app/entities/cucop/cucop-detail.component';
import { CUCOP } from 'app/shared/model/cucop.model';

describe('Component Tests', () => {
    describe('CUCOP Management Detail Component', () => {
        let comp: CUCOPDetailComponent;
        let fixture: ComponentFixture<CUCOPDetailComponent>;
        const route = ({ data: of({ cUCOP: new CUCOP(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [CUCOPDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CUCOPDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CUCOPDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cUCOP).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
