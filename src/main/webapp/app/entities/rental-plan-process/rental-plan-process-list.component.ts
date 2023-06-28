import { Component, Vue, Inject } from 'vue-property-decorator';
import { IRentalPlanProcess } from '@/shared/model/rental-plan-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import RentalPlanProcessService from './rental-plan-process.service';

@Component
export default class RentalPlanProcessListComponent extends Vue {
  @Inject('rentalPlanProcessService') private rentalPlanProcessService: () => RentalPlanProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'RentalPlanProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public rentalPlanProcessList: IRentalPlanProcess[] = [];

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
    this.rentalPlanProcessService()
      .retrieve()
      .then(
        res => {
          this.rentalPlanProcessList = res.data;
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
