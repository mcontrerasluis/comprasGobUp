/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { CUCOPService } from 'app/entities/cucop/cucop.service';
import { ICUCOP, CUCOP } from 'app/shared/model/cucop.model';

describe('Service Tests', () => {
    describe('CUCOP Service', () => {
        let injector: TestBed;
        let service: CUCOPService;
        let httpMock: HttpTestingController;
        let elemDefault: ICUCOP;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CUCOPService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new CUCOP(0, 0, 0, 0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'image/png', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a CUCOP', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new CUCOP(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a CUCOP', async () => {
                const returnedFromService = Object.assign(
                    {
                        tipo: 1,
                        claveCUCOP: 1,
                        partidaEsp: 1,
                        descripcion: 'BBBBBB',
                        nivel: 1,
                        cABM: 'BBBBBB',
                        unidadMed: 'BBBBBB',
                        tipoContrata: 'BBBBBB',
                        imagen: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of CUCOP', async () => {
                const returnedFromService = Object.assign(
                    {
                        tipo: 1,
                        claveCUCOP: 1,
                        partidaEsp: 1,
                        descripcion: 'BBBBBB',
                        nivel: 1,
                        cABM: 'BBBBBB',
                        unidadMed: 'BBBBBB',
                        tipoContrata: 'BBBBBB',
                        imagen: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a CUCOP', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
