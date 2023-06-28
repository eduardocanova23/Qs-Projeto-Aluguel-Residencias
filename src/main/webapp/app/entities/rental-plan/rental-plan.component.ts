import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRentalPlan } from '@/shared/model/rental-plan.model';

import RentalPlanService from './rental-plan.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class RentalPlan extends Vue {
  @Inject('rentalPlanService') private rentalPlanService: () => RentalPlanService;
  private removeId: number = null;

  public rentalPlans: IRentalPlan[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRentalPlans();
  }

  public clear(): void {
    this.retrieveAllRentalPlans();
  }

  public retrieveAllRentalPlans(): void {
    this.isFetching = true;

    this.rentalPlanService()
      .retrieve()
      .then(
        res => {
          this.rentalPlans = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
