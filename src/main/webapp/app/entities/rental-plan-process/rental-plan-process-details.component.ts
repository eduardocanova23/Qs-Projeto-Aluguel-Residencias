import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRentalPlanProcess } from '@/shared/model/rental-plan-process.model';
import RentalPlanProcessService from './rental-plan-process.service';

@Component
export default class RentalPlanProcessDetailsComponent extends Vue {
  @Inject('rentalPlanProcessService') private rentalPlanProcessService: () => RentalPlanProcessService;
  public rentalPlanProcess: IRentalPlanProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveRentalPlanProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveRentalPlanProcess(rentalPlanProcessId) {
    this.isFetching = true;
    this.rentalPlanProcessService()
      .find(rentalPlanProcessId)
      .then(
        res => {
          this.rentalPlanProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
