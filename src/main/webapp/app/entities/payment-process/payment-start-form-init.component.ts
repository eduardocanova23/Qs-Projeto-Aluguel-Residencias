import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPaymentProcess, PaymentProcess } from '@/shared/model/payment-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Payment } from '@/shared/model/payment.model';
import PaymentProcessService from './payment-process.service';

const validations: any = {
  paymentProcess: {
    payment: {
      userName: {},
      userEmail: {},
    },
  },
};

@Component({
  validations,
})
export default class PaymentStartFormInitComponent extends Vue {
  @Inject('paymentProcessService') private paymentProcessService: () => PaymentProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'FakeAirBnBProcess';
  public paymentProcess: IPaymentProcess = new PaymentProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initPaymentStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.paymentProcessService()
      .create(this.paymentProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('airBnBFakeApp.paymentStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initPaymentStartForm(): void {
    this.paymentProcess.payment = new Payment();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.paymentProcess.processInstance = new ProcessInstance();
      this.paymentProcess.processInstance.processDefinition = res;
    });
  }
}
