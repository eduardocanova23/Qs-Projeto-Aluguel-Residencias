import axios from 'axios';

import { IPayment } from '@/shared/model/payment.model';

const baseApiUrl = 'api/payments';

export default class PaymentService {
  public find(id: number): Promise<IPayment> {
    return new Promise<IPayment>((resolve, reject) => {
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
