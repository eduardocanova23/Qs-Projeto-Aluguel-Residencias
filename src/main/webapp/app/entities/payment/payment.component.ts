import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IPayment } from '@/shared/model/payment.model';

import PaymentService from './payment.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Payment extends Vue {
  @Inject('paymentService') private paymentService: () => PaymentService;
  private removeId: number = null;

  public payments: IPayment[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllPayments();
  }

  public clear(): void {
    this.retrieveAllPayments();
  }

  public retrieveAllPayments(): void {
    this.isFetching = true;

    this.paymentService()
      .retrieve()
      .then(
        res => {
          this.payments = res.data;
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
