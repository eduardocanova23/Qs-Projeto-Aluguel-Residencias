import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILivingQuarters } from '@/shared/model/living-quarters.model';

import LivingQuartersService from './living-quarters.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class LivingQuarters extends Vue {
  @Inject('livingQuartersService') private livingQuartersService: () => LivingQuartersService;
  private removeId: number = null;

  public livingQuarters: ILivingQuarters[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLivingQuarterss();
  }

  public clear(): void {
    this.retrieveAllLivingQuarterss();
  }

  public retrieveAllLivingQuarterss(): void {
    this.isFetching = true;

    this.livingQuartersService()
      .retrieve()
      .then(
        res => {
          this.livingQuarters = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ILivingQuarters): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLivingQuarters(): void {
    this.livingQuartersService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('airBnBFakeApp.livingQuarters.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLivingQuarterss();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
