/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import LivingQuartersUpdateComponent from '@/entities/living-quarters/living-quarters-update.vue';
import LivingQuartersClass from '@/entities/living-quarters/living-quarters-update.component';
import LivingQuartersService from '@/entities/living-quarters/living-quarters.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('LivingQuarters Management Update Component', () => {
    let wrapper: Wrapper<LivingQuartersClass>;
    let comp: LivingQuartersClass;
    let livingQuartersServiceStub: SinonStubbedInstance<LivingQuartersService>;

    beforeEach(() => {
      livingQuartersServiceStub = sinon.createStubInstance<LivingQuartersService>(LivingQuartersService);

      wrapper = shallowMount<LivingQuartersClass>(LivingQuartersUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          livingQuartersService: () => livingQuartersServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.livingQuarters = entity;
        livingQuartersServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(livingQuartersServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.livingQuarters = entity;
        livingQuartersServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(livingQuartersServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLivingQuarters = { id: 123 };
        livingQuartersServiceStub.find.resolves(foundLivingQuarters);
        livingQuartersServiceStub.retrieve.resolves([foundLivingQuarters]);

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
