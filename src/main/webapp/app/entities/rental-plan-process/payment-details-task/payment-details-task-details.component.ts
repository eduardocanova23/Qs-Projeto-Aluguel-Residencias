import { Component, Vue, Inject } from 'vue-property-decorator';

import PaymentDetailsTaskService from './payment-details-task.service';
import { PaymentDetailsTaskContext } from './payment-details-task.model';

@Component
export default class PaymentDetailsTaskDetailsComponent extends Vue {
  private paymentDetailsTaskService: PaymentDetailsTaskService = new PaymentDetailsTaskService();
  private taskContext: PaymentDetailsTaskContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.paymentDetailsTaskService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
