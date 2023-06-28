import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRentalPlanProcess, RentalPlanProcess } from '@/shared/model/rental-plan-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { RentalPlan } from '@/shared/model/rental-plan.model';
import RentalPlanProcessService from './rental-plan-process.service';

const validations: any = {
  rentalPlanProcess: {
    rentalPlan: {
      targetCity: {},
      targetNeighboorhood: {},
      startDate: {},
      endDate: {},
      userName: {},
      userEmail: {},
      userPhoneNumber: {},
    },
  },
};

@Component({
  validations,
})
export default class RentalPlanStartFormInitComponent extends Vue {
  @Inject('rentalPlanProcessService') private rentalPlanProcessService: () => RentalPlanProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'RentalPlanProcess';
  public rentalPlanProcess: IRentalPlanProcess = new RentalPlanProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initRentalPlanStartForm();
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

    this.rentalPlanProcessService()
      .create(this.rentalPlanProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('airBnBFakeApp.rentalPlanStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initRentalPlanStartForm(): void {
    this.rentalPlanProcess.rentalPlan = new RentalPlan();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.rentalPlanProcess.processInstance = new ProcessInstance();
      this.rentalPlanProcess.processInstance.processDefinition = res;
    });
  }
}
