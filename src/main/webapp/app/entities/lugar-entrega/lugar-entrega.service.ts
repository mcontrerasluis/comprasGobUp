import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILugarEntrega } from 'app/shared/model/lugar-entrega.model';

type EntityResponseType = HttpResponse<ILugarEntrega>;
type EntityArrayResponseType = HttpResponse<ILugarEntrega[]>;

@Injectable({ providedIn: 'root' })
export class LugarEntregaService {
    public resourceUrl = SERVER_API_URL + 'api/lugar-entregas';

    constructor(private http: HttpClient) {}

    create(lugarEntrega: ILugarEntrega): Observable<EntityResponseType> {
        return this.http.post<ILugarEntrega>(this.resourceUrl, lugarEntrega, { observe: 'response' });
    }

    update(lugarEntrega: ILugarEntrega): Observable<EntityResponseType> {
        return this.http.put<ILugarEntrega>(this.resourceUrl, lugarEntrega, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILugarEntrega>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILugarEntrega[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
