import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IElementoOrden } from 'app/shared/model/elemento-orden.model';

type EntityResponseType = HttpResponse<IElementoOrden>;
type EntityArrayResponseType = HttpResponse<IElementoOrden[]>;

@Injectable({ providedIn: 'root' })
export class ElementoOrdenService {
    public resourceUrl = SERVER_API_URL + 'api/elemento-ordens';

    constructor(private http: HttpClient) {}

    create(elementoOrden: IElementoOrden): Observable<EntityResponseType> {
        return this.http.post<IElementoOrden>(this.resourceUrl, elementoOrden, { observe: 'response' });
    }

    update(elementoOrden: IElementoOrden): Observable<EntityResponseType> {
        return this.http.put<IElementoOrden>(this.resourceUrl, elementoOrden, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IElementoOrden>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IElementoOrden[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
