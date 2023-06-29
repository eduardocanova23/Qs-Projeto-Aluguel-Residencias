export interface IPayment {
  id?: number;
  cardNumber?: string | null;
  cardVerificationValue?: string | null;
  expirationDate?: string | null;
  userName?: string | null;
  userEmail?: string | null;
  phoneNumber?: string | null;
}

export class Payment implements IPayment {
  constructor(
    public id?: number,
    public cardNumber?: string | null,
    public cardVerificationValue?: string | null,
    public expirationDate?: string | null,
    public userName?: string | null,
    public userEmail?: string | null,
    public phoneNumber?: string | null
  ) {}
}
