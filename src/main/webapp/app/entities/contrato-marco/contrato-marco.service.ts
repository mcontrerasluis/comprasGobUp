import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IContratoMarco } from 'app/shared/model/contrato-marco.model';

type EntityResponseType = HttpResponse<IContratoMarco>;
type EntityArrayResponseType = HttpResponse<IContratoMarco[]>;

@Injectable({ providedIn: 'root' })
export class ContratoMarcoService {
    public resourceUrl = SERVER_API_URL + 'api/contrato-marcos';

    constructor(private http: HttpClient) {}

    create(contratoMarco: IContratoMarco): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(contratoMarco);
        return this.http
            .post<IContratoMarco>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(contratoMarco: IContratoMarco): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(contratoMarco);
        return this.http
            .put<IContratoMarco>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IContratoMarco>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IContratoMarco[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(contratoMarco: IContratoMarco): IContratoMarco {
        const copy: IContratoMarco = Object.assign({}, contratoMarco, {
            fechaVigencia:
                contratoMarco.fechaVigencia != null && contratoMarco.fechaVigencia.isValid()
                    ? contratoMarco.fechaVigencia.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fechaVigencia = res.body.fechaVigencia != null ? moment(res.body.fechaVigencia) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((contratoMarco: IContratoMarco) => {
                contratoMarco.fechaVigencia = contratoMarco.fechaVigencia != null ? moment(contratoMarco.fechaVigencia) : null;
            });
        }
        return res;
    }
}
