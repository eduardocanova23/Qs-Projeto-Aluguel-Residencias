/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LivingQuartersComponent from '@/entities/living-quarters/living-quarters.vue';
import LivingQuartersClass from '@/entities/living-quarters/living-quarters.component';
import LivingQuartersService from '@/entities/living-quarters/living-quarters.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('LivingQuarters Management Component', () => {
    let wrapper: Wrapper<LivingQuartersClass>;
    let comp: LivingQuartersClass;
    let livingQuartersServiceStub: SinonStubbedInstance<LivingQuartersService>;

    beforeEach(() => {
      livingQuartersServiceStub = sinon.createStubInstance<LivingQuartersService>(LivingQuartersService);
      livingQuartersServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LivingQuartersClass>(LivingQuartersComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          livingQuartersService: () => livingQuartersServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      livingQuartersServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLivingQuarterss();
      await comp.$nextTick();

      // THEN
      expect(livingQuartersServiceStub.retrieve.called).toBeTruthy();
      expect(comp.livingQuarters[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      livingQuartersServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeLivingQuarters();
      await comp.$nextTick();

      // THEN
      expect(livingQuartersServiceStub.delete.called).toBeTruthy();
      expect(livingQuartersServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
