/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LivingQuartersDetailComponent from '@/entities/living-quarters/living-quarters-details.vue';
import LivingQuartersClass from '@/entities/living-quarters/living-quarters-details.component';
import LivingQuartersService from '@/entities/living-quarters/living-quarters.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('LivingQuarters Management Detail Component', () => {
    let wrapper: Wrapper<LivingQuartersClass>;
    let comp: LivingQuartersClass;
    let livingQuartersServiceStub: SinonStubbedInstance<LivingQuartersService>;

    beforeEach(() => {
      livingQuartersServiceStub = sinon.createStubInstance<LivingQuartersService>(LivingQuartersService);

      wrapper = shallowMount<LivingQuartersClass>(LivingQuartersDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { livingQuartersService: () => livingQuartersServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLivingQuarters = { id: 123 };
        livingQuartersServiceStub.find.resolves(foundLivingQuarters);

        // WHEN
        comp.retrieveLivingQuarters(123);
        await comp.$nextTick();

        // THEN
        expect(comp.livingQuarters).toBe(foundLivingQuarters);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLivingQuarters = { id: 123 };
        livingQuartersServiceStub.find.resolves(foundLivingQuarters);

        // WHEN
        comp.beforeRouteEnter({ params: { livingQuartersId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.livingQuarters).toBe(foundLivingQuarters);
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
