import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILivingQuarters, LivingQuarters } from '@/shared/model/living-quarters.model';
import LivingQuartersService from './living-quarters.service';

const validations: any = {
  livingQuarters: {
    name: {},
    city: {},
    neighborhood: {},
    description: {},
    image: {},
  },
};

@Component({
  validations,
})
export default class LivingQuartersUpdate extends Vue {
  @Inject('livingQuartersService') private livingQuartersService: () => LivingQuartersService;
  public livingQuarters: ILivingQuarters = new LivingQuarters();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.livingQuartersId) {
        vm.retrieveLivingQuarters(to.params.livingQuartersId);
      }
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
    if (this.livingQuarters.id) {
      this.livingQuartersService()
        .update(this.livingQuarters)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('airBnBFakeApp.livingQuarters.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.livingQuartersService()
        .create(this.livingQuarters)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('airBnBFakeApp.livingQuarters.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveLivingQuarters(livingQuartersId): void {
    this.livingQuartersService()
      .find(livingQuartersId)
      .then(res => {
        this.livingQuarters = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
