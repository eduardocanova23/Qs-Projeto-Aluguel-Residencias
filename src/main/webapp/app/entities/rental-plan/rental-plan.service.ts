import axios from 'axios';

import { IRentalPlan } from '@/shared/model/rental-plan.model';

const baseApiUrl = 'api/rental-plans';

export default class RentalPlanService {
  public find(id: number): Promise<IRentalPlan> {
    return new Promise<IRentalPlan>((resolve, reject) => {
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
}
