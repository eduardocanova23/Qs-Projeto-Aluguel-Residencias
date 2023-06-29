import { Component, Vue, Inject } from 'vue-property-decorator';

import BookReservationTaskService from './book-reservation-task.service';
import { BookReservationTaskContext } from './book-reservation-task.model';

@Component
export default class BookReservationTaskDetailsComponent extends Vue {
  private bookReservationTaskService: BookReservationTaskService = new BookReservationTaskService();
  private taskContext: BookReservationTaskContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.bookReservationTaskService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
