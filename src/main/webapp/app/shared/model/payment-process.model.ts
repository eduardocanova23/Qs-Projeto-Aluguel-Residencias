import { IPayment } from '@/shared/model/payment.model';

export interface IPaymentProcess {
  id?: number;
  processInstance?: any | null;
  payment?: IPayment | null;
}

export class PaymentProcess implements IPaymentProcess {
  constructor(public id?: number, public processInstance?: any | null, public payment?: IPayment | null) {}
}
