import axios from 'axios';
import { PaymentDetailsTaskContext } from './payment-details-task.model';

const baseApiUrl = 'api/payment-process/payment-details-task';

export default class PaymentDetailsTaskService {
  public loadContext(taskId: number): Promise<PaymentDetailsTaskContext> {
    return new Promise<PaymentDetailsTaskContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<PaymentDetailsTaskContext> {
    return new Promise<PaymentDetailsTaskContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(paymentDetailsTaskContext: PaymentDetailsTaskContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, paymentDetailsTaskContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
