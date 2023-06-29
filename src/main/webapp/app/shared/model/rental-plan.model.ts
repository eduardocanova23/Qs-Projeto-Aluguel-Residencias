import { ILivingQuarters } from '@/shared/model/living-quarters.model';

export interface IRentalPlan {
  id?: number;
  city?: string | null;
  neighborhood?: string | null;
  userName?: string | null;
  userEmail?: string | null;
  startDate?: Date | null;
  endDate?: Date | null;
  rentalConfirmationNumber?: string | null;
  cardNumber?: string | null;
  cardVerificationValue?: string | null;
  expirationDate?: Date | null;
  confirmation?: boolean | null;
  livingQuarters?: ILivingQuarters | null;
}

export class RentalPlan implements IRentalPlan {
  constructor(
    public id?: number,
    public city?: string | null,
    public neighborhood?: string | null,
    public userName?: string | null,
    public userEmail?: string | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public rentalConfirmationNumber?: string | null,
    public cardNumber?: string | null,
    public cardVerificationValue?: string | null,
    public expirationDate?: Date | null,
    public confirmation?: boolean | null,
    public livingQuarters?: ILivingQuarters | null
  ) {
    this.confirmation = this.confirmation ?? false;
  }
}
