import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICUCOP } from 'app/shared/model/cucop.model';

type EntityResponseType = HttpResponse<ICUCOP>;
type EntityArrayResponseType = HttpResponse<ICUCOP[]>;

@Injectable({ providedIn: 'root' })
export class CUCOPService {
    public resourceUrl = SERVER_API_URL + 'api/cucops';

    constructor(private http: HttpClient) {}

    create(cUCOP: ICUCOP): Observable<EntityResponseType> {
        return this.http.post<ICUCOP>(this.resourceUrl, cUCOP, { observe: 'response' });
    }

    update(cUCOP: ICUCOP): Observable<EntityResponseType> {
        return this.http.put<ICUCOP>(this.resourceUrl, cUCOP, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICUCOP>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICUCOP[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
