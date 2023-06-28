import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const RentalPlan = () => import('@/entities/rental-plan/rental-plan.vue');
// prettier-ignore
const RentalPlanDetails = () => import('@/entities/rental-plan/rental-plan-details.vue');
// prettier-ignore
const RentalPlanProcessDetails = () => import('@/entities/rental-plan-process/rental-plan-process-details.vue');
// prettier-ignore
const RentalPlanProcessList = () => import('@/entities/rental-plan-process/rental-plan-process-list.vue');
// prettier-ignore
const RentalPlanStartFormInit = () => import('@/entities/rental-plan-process/rental-plan-start-form-init.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/rental-plan',
    name: 'RentalPlan',
    component: RentalPlan,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/rental-plan/:rentalPlanId/view',
    name: 'RentalPlanView',
    component: RentalPlanDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/instance/:processInstanceId/view',
    name: 'RentalPlanProcessView',
    component: RentalPlanProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/instances',
    name: 'RentalPlanProcessList',
    component: RentalPlanProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/init',
    name: 'RentalPlanStartFormInit',
    component: RentalPlanStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
