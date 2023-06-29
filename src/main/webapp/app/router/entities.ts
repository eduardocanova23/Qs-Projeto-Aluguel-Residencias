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
// prettier-ignore
const LivingQuarters = () => import('@/entities/living-quarters/living-quarters.vue');
// prettier-ignore
const LivingQuartersUpdate = () => import('@/entities/living-quarters/living-quarters-update.vue');
// prettier-ignore
const LivingQuartersDetails = () => import('@/entities/living-quarters/living-quarters-details.vue');
// prettier-ignore
const RentalPlanProcess_TaskBrowseResidencesDetails = () => import('@/entities/rental-plan-process/task-browse-residences/task-browse-residences-details.vue');
// prettier-ignore
const RentalPlanProcess_TaskBrowseResidencesExecute = () => import('@/entities/rental-plan-process/task-browse-residences/task-browse-residences-execute.vue');
// prettier-ignore
const Payment = () => import('@/entities/payment/payment.vue');
// prettier-ignore
const PaymentDetails = () => import('@/entities/payment/payment-details.vue');
// prettier-ignore
const RentalPlanProcess_PaymentDetailsTaskDetails = () => import('@/entities/rental-plan-process/payment-details-task/payment-details-task-details.vue');
// prettier-ignore
const RentalPlanProcess_PaymentDetailsTaskExecute = () => import('@/entities/rental-plan-process/payment-details-task/payment-details-task-execute.vue');
// prettier-ignore
const RentalPlan = () => import('@/entities/rental-plan/rental-plan.vue');
// prettier-ignore
const RentalPlanDetails = () => import('@/entities/rental-plan/rental-plan-details.vue');
// prettier-ignore
const RentalPlanProcessDetails = () => import('@/entities/rental-plan-process/rental-plan-process-details.vue');
// prettier-ignore
const RentalPlanProcessList = () => import('@/entities/rental-plan-process/rental-plan-process-list.vue');
// prettier-ignore
const LivingQuarters = () => import('@/entities/living-quarters/living-quarters.vue');
// prettier-ignore
const LivingQuartersUpdate = () => import('@/entities/living-quarters/living-quarters-update.vue');
// prettier-ignore
const LivingQuartersDetails = () => import('@/entities/living-quarters/living-quarters-details.vue');
// prettier-ignore
const RentalPlanProcess_TaskBrowseResidencesDetails = () => import('@/entities/rental-plan-process/task-browse-residences/task-browse-residences-details.vue');
// prettier-ignore
const RentalPlanProcess_TaskBrowseResidencesExecute = () => import('@/entities/rental-plan-process/task-browse-residences/task-browse-residences-execute.vue');
// prettier-ignore
const RentalPlanStartFormInit = () => import('@/entities/rental-plan-process/rental-plan-start-form-init.vue');
// prettier-ignore
const RentalPlanProcess_PaymentDetailsTaskDetails = () => import('@/entities/rental-plan-process/payment-details-task/payment-details-task-details.vue');
// prettier-ignore
const RentalPlanProcess_PaymentDetailsTaskExecute = () => import('@/entities/rental-plan-process/payment-details-task/payment-details-task-execute.vue');
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
  {
    path: '/living-quarters',
    name: 'LivingQuarters',
    component: LivingQuarters,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/living-quarters/new',
    name: 'LivingQuartersCreate',
    component: LivingQuartersUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/living-quarters/:livingQuartersId/edit',
    name: 'LivingQuartersEdit',
    component: LivingQuartersUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/living-quarters/:livingQuartersId/view',
    name: 'LivingQuartersView',
    component: LivingQuartersDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/task/BrowseResidences/:taskInstanceId/view',
    name: 'RentalPlanProcess_TaskBrowseResidencesDetails',
    component: RentalPlanProcess_TaskBrowseResidencesDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/task/BrowseResidences/:taskInstanceId/execute',
    name: 'RentalPlanProcess_TaskBrowseResidencesExecute',
    component: RentalPlanProcess_TaskBrowseResidencesExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/payment',
    name: 'Payment',
    component: Payment,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/payment/:paymentId/view',
    name: 'PaymentView',
    component: PaymentDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/task/PaymentDetails/:taskInstanceId/view',
    name: 'RentalPlanProcess_PaymentDetailsTaskDetails',
    component: RentalPlanProcess_PaymentDetailsTaskDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/task/PaymentDetails/:taskInstanceId/execute',
    name: 'RentalPlanProcess_PaymentDetailsTaskExecute',
    component: RentalPlanProcess_PaymentDetailsTaskExecute,
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
    path: '/process-definition/RentalPlanProcess/task/BrowseResidences/:taskInstanceId/view',
    name: 'RentalPlanProcess_TaskBrowseResidencesDetails',
    component: RentalPlanProcess_TaskBrowseResidencesDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/task/BrowseResidences/:taskInstanceId/execute',
    name: 'RentalPlanProcess_TaskBrowseResidencesExecute',
    component: RentalPlanProcess_TaskBrowseResidencesExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/init',
    name: 'RentalPlanStartFormInit',
    component: RentalPlanStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/task/PaymentDetails/:taskInstanceId/view',
    name: 'RentalPlanProcess_PaymentDetailsTaskDetails',
    component: RentalPlanProcess_PaymentDetailsTaskDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/RentalPlanProcess/task/PaymentDetails/:taskInstanceId/execute',
    name: 'RentalPlanProcess_PaymentDetailsTaskExecute',
    component: RentalPlanProcess_PaymentDetailsTaskExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
