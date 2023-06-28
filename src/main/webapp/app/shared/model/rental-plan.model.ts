export interface IRentalPlan {
  id?: number;
  targetAddress?: string | null;
  userName?: string | null;
  userEmail?: string | null;
  startDate?: Date | null;
  endDate?: Date | null;
  rentalConfirmationNumber?: string | null;
}

export class RentalPlan implements IRentalPlan {
  constructor(
    public id?: number,
    public targetAddress?: string | null,
    public userName?: string | null,
    public userEmail?: string | null,
    public startDate?: Date | null,
    public endDate?: Date | null,
    public rentalConfirmationNumber?: string | null
  ) {}
}
