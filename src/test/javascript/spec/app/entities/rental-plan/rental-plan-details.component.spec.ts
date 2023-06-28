/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RentalPlanDetailComponent from '@/entities/rental-plan/rental-plan-details.vue';
import RentalPlanClass from '@/entities/rental-plan/rental-plan-details.component';
import RentalPlanService from '@/entities/rental-plan/rental-plan.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RentalPlan Management Detail Component', () => {
    let wrapper: Wrapper<RentalPlanClass>;
    let comp: RentalPlanClass;
    let rentalPlanServiceStub: SinonStubbedInstance<RentalPlanService>;

    beforeEach(() => {
      rentalPlanServiceStub = sinon.createStubInstance<RentalPlanService>(RentalPlanService);

      wrapper = shallowMount<RentalPlanClass>(RentalPlanDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { rentalPlanService: () => rentalPlanServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRentalPlan = { id: 123 };
        rentalPlanServiceStub.find.resolves(foundRentalPlan);

        // WHEN
        comp.retrieveRentalPlan(123);
        await comp.$nextTick();

        // THEN
        expect(comp.rentalPlan).toBe(foundRentalPlan);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRentalPlan = { id: 123 };
        rentalPlanServiceStub.find.resolves(foundRentalPlan);

        // WHEN
        comp.beforeRouteEnter({ params: { rentalPlanId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.rentalPlan).toBe(foundRentalPlan);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
