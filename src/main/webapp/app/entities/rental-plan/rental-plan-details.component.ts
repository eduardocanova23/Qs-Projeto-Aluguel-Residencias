import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRentalPlan } from '@/shared/model/rental-plan.model';
import RentalPlanService from './rental-plan.service';

@Component
export default class RentalPlanDetails extends Vue {
  @Inject('rentalPlanService') private rentalPlanService: () => RentalPlanService;
  public rentalPlan: IRentalPlan = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rentalPlanId) {
        vm.retrieveRentalPlan(to.params.rentalPlanId);
      }
    });
  }

  public retrieveRentalPlan(rentalPlanId) {
    this.rentalPlanService()
      .find(rentalPlanId)
      .then(res => {
        this.rentalPlan = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
