import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILivingQuarters } from '@/shared/model/living-quarters.model';
import LivingQuartersService from './living-quarters.service';

@Component
export default class LivingQuartersDetails extends Vue {
  @Inject('livingQuartersService') private livingQuartersService: () => LivingQuartersService;
  public livingQuarters: ILivingQuarters = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.livingQuartersId) {
        vm.retrieveLivingQuarters(to.params.livingQuartersId);
      }
    });
  }

  public retrieveLivingQuarters(livingQuartersId) {
    this.livingQuartersService()
      .find(livingQuartersId)
      .then(res => {
        this.livingQuarters = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
