import { Component, Vue, Inject } from 'vue-property-decorator';

import BookReservationTaskService from './book-reservation-task.service';
import { BookReservationTaskContext } from './book-reservation-task.model';

const validations: any = {
  taskContext: {
    rentalPlanProcess: {
      rentalPlan: {
        city: {},
        neighborhood: {},
        startDate: {},
        endDate: {},
        confirmation: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class BookReservationTaskExecuteComponent extends Vue {
  private bookReservationTaskService: BookReservationTaskService = new BookReservationTaskService();
  private taskContext: BookReservationTaskContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.bookReservationTaskService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.bookReservationTaskService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
