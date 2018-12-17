import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDependencia } from 'app/shared/model/dependencia.model';

type EntityResponseType = HttpResponse<IDependencia>;
type EntityArrayResponseType = HttpResponse<IDependencia[]>;

@Injectable({ providedIn: 'root' })
export class DependenciaService {
    public resourceUrl = SERVER_API_URL + 'api/dependencias';

    constructor(private http: HttpClient) {}

    create(dependencia: IDependencia): Observable<EntityResponseType> {
        return this.http.post<IDependencia>(this.resourceUrl, dependencia, { observe: 'response' });
    }

    update(dependencia: IDependencia): Observable<EntityResponseType> {
        return this.http.put<IDependencia>(this.resourceUrl, dependencia, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDependencia>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDependencia[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
