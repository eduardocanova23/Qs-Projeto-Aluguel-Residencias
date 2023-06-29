import { Component, Vue, Inject } from 'vue-property-decorator';

import LivingQuartersService from '@/entities/living-quarters/living-quarters.service';
import { ILivingQuarters } from '@/shared/model/living-quarters.model';

import TaskBrowseResidencesService from './task-browse-residences.service';
import { TaskBrowseResidencesContext } from './task-browse-residences.model';

const validations: any = {
  taskContext: {
    rentalPlanProcess: {
      rentalPlan: {
        targetAddress: {},
        userName: {},
        userEmail: {},
        startDate: {},
        endDate: {},
        rentalConfirmationNumber: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskBrowseResidencesExecuteComponent extends Vue {
  private taskBrowseResidencesService: TaskBrowseResidencesService = new TaskBrowseResidencesService();
  private taskContext: TaskBrowseResidencesContext = {};

  @Inject('livingQuartersService') private livingQuartersService: () => LivingQuartersService;

  public livingQuarters: ILivingQuarters[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskBrowseResidencesService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskBrowseResidencesService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.livingQuartersService()
      .retrieve()
      .then(res => {
        this.livingQuarters = res.data;
      });
  }
}
