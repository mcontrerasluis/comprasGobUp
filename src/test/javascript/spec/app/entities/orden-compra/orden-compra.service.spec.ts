/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { OrdenCompraService } from 'app/entities/orden-compra/orden-compra.service';
import { IOrdenCompra, OrdenCompra, EstatusOrden } from 'app/shared/model/orden-compra.model';

describe('Service Tests', () => {
    describe('OrdenCompra Service', () => {
        let injector: TestBed;
        let service: OrdenCompraService;
        let httpMock: HttpTestingController;
        let elemDefault: IOrdenCompra;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(OrdenCompraService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new OrdenCompra(0, currentDate, EstatusOrden.COMPLETADA, 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        fechaEntrada: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a OrdenCompra', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        fechaEntrada: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        fechaEntrada: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new OrdenCompra(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a OrdenCompra', async () => {
                const returnedFromService = Object.assign(
                    {
                        fechaEntrada: currentDate.format(DATE_TIME_FORMAT),
                        estatus: 'BBBBBB',
                        codigo: 'BBBBBB',
                        lugarEntregaD: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        fechaEntrada: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of OrdenCompra', async () => {
                const returnedFromService = Object.assign(
                    {
                        fechaEntrada: currentDate.format(DATE_TIME_FORMAT),
                        estatus: 'BBBBBB',
                        codigo: 'BBBBBB',
                        lugarEntregaD: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        fechaEntrada: currentDate
                    },
                    returnedFromService
                );
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

            it('should delete a OrdenCompra', async () => {
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
