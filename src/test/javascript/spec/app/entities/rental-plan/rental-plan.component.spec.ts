/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import RentalPlanComponent from '@/entities/rental-plan/rental-plan.vue';
import RentalPlanClass from '@/entities/rental-plan/rental-plan.component';
import RentalPlanService from '@/entities/rental-plan/rental-plan.service';

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
  describe('RentalPlan Management Component', () => {
    let wrapper: Wrapper<RentalPlanClass>;
    let comp: RentalPlanClass;
    let rentalPlanServiceStub: SinonStubbedInstance<RentalPlanService>;

    beforeEach(() => {
      rentalPlanServiceStub = sinon.createStubInstance<RentalPlanService>(RentalPlanService);
      rentalPlanServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<RentalPlanClass>(RentalPlanComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          rentalPlanService: () => rentalPlanServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      rentalPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllRentalPlans();
      await comp.$nextTick();

      // THEN
      expect(rentalPlanServiceStub.retrieve.called).toBeTruthy();
      expect(comp.rentalPlans[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
