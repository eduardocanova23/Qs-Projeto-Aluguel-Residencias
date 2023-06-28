import { IRentalPlan } from '@/shared/model/rental-plan.model';

export interface IRentalPlanProcess {
  id?: number;
  processInstance?: any | null;
  rentalPlan?: IRentalPlan | null;
}

export class RentalPlanProcess implements IRentalPlanProcess {
  constructor(public id?: number, public processInstance?: any | null, public rentalPlan?: IRentalPlan | null) {}
}
