import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskBrowseResidencesService from './task-browse-residences.service';
import { TaskBrowseResidencesContext } from './task-browse-residences.model';

@Component
export default class TaskBrowseResidencesDetailsComponent extends Vue {
  private taskBrowseResidencesService: TaskBrowseResidencesService = new TaskBrowseResidencesService();
  private taskContext: TaskBrowseResidencesContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskBrowseResidencesService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
