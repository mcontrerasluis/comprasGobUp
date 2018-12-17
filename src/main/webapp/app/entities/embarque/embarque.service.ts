import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmbarque } from 'app/shared/model/embarque.model';

type EntityResponseType = HttpResponse<IEmbarque>;
type EntityArrayResponseType = HttpResponse<IEmbarque[]>;

@Injectable({ providedIn: 'root' })
export class EmbarqueService {
    public resourceUrl = SERVER_API_URL + 'api/embarques';

    constructor(private http: HttpClient) {}

    create(embarque: IEmbarque): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(embarque);
        return this.http
            .post<IEmbarque>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(embarque: IEmbarque): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(embarque);
        return this.http
            .put<IEmbarque>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEmbarque>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEmbarque[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(embarque: IEmbarque): IEmbarque {
        const copy: IEmbarque = Object.assign({}, embarque, {
            fecha: embarque.fecha != null && embarque.fecha.isValid() ? embarque.fecha.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((embarque: IEmbarque) => {
                embarque.fecha = embarque.fecha != null ? moment(embarque.fecha) : null;
            });
        }
        return res;
    }
}
