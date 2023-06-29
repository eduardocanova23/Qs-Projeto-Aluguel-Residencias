import { Component, Vue, Inject } from 'vue-property-decorator';

import PaymentDetailsTaskService from './payment-details-task.service';
import { PaymentDetailsTaskContext } from './payment-details-task.model';

const validations: any = {
  taskContext: {
    paymentProcess: {
      payment: {
        cardNumber: {},
        cardVerificationValue: {},
        expirationDate: {},
        phoneNumber: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class PaymentDetailsTaskExecuteComponent extends Vue {
  private paymentDetailsTaskService: PaymentDetailsTaskService = new PaymentDetailsTaskService();
  private taskContext: PaymentDetailsTaskContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.paymentDetailsTaskService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.paymentDetailsTaskService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
