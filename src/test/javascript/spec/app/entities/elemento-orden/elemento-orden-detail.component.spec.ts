/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComprasGobUpTestModule } from '../../../test.module';
import { ElementoOrdenDetailComponent } from 'app/entities/elemento-orden/elemento-orden-detail.component';
import { ElementoOrden } from 'app/shared/model/elemento-orden.model';

describe('Component Tests', () => {
    describe('ElementoOrden Management Detail Component', () => {
        let comp: ElementoOrdenDetailComponent;
        let fixture: ComponentFixture<ElementoOrdenDetailComponent>;
        const route = ({ data: of({ elementoOrden: new ElementoOrden(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComprasGobUpTestModule],
                declarations: [ElementoOrdenDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ElementoOrdenDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ElementoOrdenDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.elementoOrden).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
