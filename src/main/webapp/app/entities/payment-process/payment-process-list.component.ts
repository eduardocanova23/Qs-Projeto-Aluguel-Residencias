import { Component, Vue, Inject } from 'vue-property-decorator';
import { IPaymentProcess } from '@/shared/model/payment-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import PaymentProcessService from './payment-process.service';

@Component
export default class PaymentProcessListComponent extends Vue {
  @Inject('paymentProcessService') private paymentProcessService: () => PaymentProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'FakeAirBnBProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public paymentProcessList: IPaymentProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.paymentProcessService()
      .retrieve()
      .then(
        res => {
          this.paymentProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
