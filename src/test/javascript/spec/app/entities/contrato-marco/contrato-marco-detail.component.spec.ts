/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { ContratoMarcoDetailComponent } from 'app/entities/contrato-marco/contrato-marco-detail.component';
import { ContratoMarco } from 'app/shared/model/contrato-marco.model';

describe('Component Tests', () => {
    describe('ContratoMarco Management Detail Component', () => {
        let comp: ContratoMarcoDetailComponent;
        let fixture: ComponentFixture<ContratoMarcoDetailComponent>;
        const route = ({ data: of({ contratoMarco: new ContratoMarco(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [ContratoMarcoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ContratoMarcoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ContratoMarcoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.contratoMarco).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
