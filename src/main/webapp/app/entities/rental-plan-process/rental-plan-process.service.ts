import axios from 'axios';

import { IRentalPlanProcess } from '@/shared/model/rental-plan-process.model';

const baseApiUrl = 'api/rental-plan-processes';

export default class RentalPlanProcessService {
  public find(id: number): Promise<IRentalPlanProcess> {
    return new Promise<IRentalPlanProcess>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${id}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public retrieve(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .get(baseApiUrl)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public create(entity: IRentalPlanProcess): Promise<IRentalPlanProcess> {
    return new Promise<IRentalPlanProcess>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}`, entity)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
