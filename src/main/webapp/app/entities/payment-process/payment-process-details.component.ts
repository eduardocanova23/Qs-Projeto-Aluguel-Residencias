import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPaymentProcess } from '@/shared/model/payment-process.model';
import PaymentProcessService from './payment-process.service';

@Component
export default class PaymentProcessDetailsComponent extends Vue {
  @Inject('paymentProcessService') private paymentProcessService: () => PaymentProcessService;
  public paymentProcess: IPaymentProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrievePaymentProcess(to.params.processInstanceId);
      }
    });
  }

  public retrievePaymentProcess(paymentProcessId) {
    this.isFetching = true;
    this.paymentProcessService()
      .find(paymentProcessId)
      .then(
        res => {
          this.paymentProcess = res;
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
