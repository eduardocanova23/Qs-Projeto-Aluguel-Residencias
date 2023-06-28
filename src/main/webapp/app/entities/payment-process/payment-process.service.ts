import axios from 'axios';

import { IPaymentProcess } from '@/shared/model/payment-process.model';

const baseApiUrl = 'api/payment-processes';

export default class PaymentProcessService {
  public find(id: number): Promise<IPaymentProcess> {
    return new Promise<IPaymentProcess>((resolve, reject) => {
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

  public create(entity: IPaymentProcess): Promise<IPaymentProcess> {
    return new Promise<IPaymentProcess>((resolve, reject) => {
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
