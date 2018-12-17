import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrdenCompra } from 'app/shared/model/orden-compra.model';

type EntityResponseType = HttpResponse<IOrdenCompra>;
type EntityArrayResponseType = HttpResponse<IOrdenCompra[]>;

@Injectable({ providedIn: 'root' })
export class OrdenCompraService {
    public resourceUrl = SERVER_API_URL + 'api/orden-compras';

    constructor(private http: HttpClient) {}

    create(ordenCompra: IOrdenCompra): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ordenCompra);
        return this.http
            .post<IOrdenCompra>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(ordenCompra: IOrdenCompra): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ordenCompra);
        return this.http
            .put<IOrdenCompra>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IOrdenCompra>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IOrdenCompra[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(ordenCompra: IOrdenCompra): IOrdenCompra {
        const copy: IOrdenCompra = Object.assign({}, ordenCompra, {
            fechaEntrada: ordenCompra.fechaEntrada != null && ordenCompra.fechaEntrada.isValid() ? ordenCompra.fechaEntrada.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fechaEntrada = res.body.fechaEntrada != null ? moment(res.body.fechaEntrada) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((ordenCompra: IOrdenCompra) => {
                ordenCompra.fechaEntrada = ordenCompra.fechaEntrada != null ? moment(ordenCompra.fechaEntrada) : null;
            });
        }
        return res;
    }
}
