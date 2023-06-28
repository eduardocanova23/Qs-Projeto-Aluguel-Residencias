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
const Payment = () => import('@/entities/payment/payment.vue');
// prettier-ignore
const PaymentDetails = () => import('@/entities/payment/payment-details.vue');
// prettier-ignore
const PaymentProcessDetails = () => import('@/entities/payment-process/payment-process-details.vue');
// prettier-ignore
const PaymentProcessList = () => import('@/entities/payment-process/payment-process-list.vue');
// prettier-ignore
const PaymentProcess_PaymentDetailsTaskDetails = () => import('@/entities/payment-process/payment-details-task/payment-details-task-details.vue');
// prettier-ignore
const PaymentProcess_PaymentDetailsTaskExecute = () => import('@/entities/payment-process/payment-details-task/payment-details-task-execute.vue');
// prettier-ignore
const PaymentStartFormInit = () => import('@/entities/payment-process/payment-start-form-init.vue');
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
    path: '/process-definition/FakeAirBnBProcess/instance/:processInstanceId/view',
    name: 'PaymentProcessView',
    component: PaymentProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/FakeAirBnBProcess/instances',
    name: 'PaymentProcessList',
    component: PaymentProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/FakeAirBnBProcess/task/PaymentDetails/:taskInstanceId/view',
    name: 'PaymentProcess_PaymentDetailsTaskDetails',
    component: PaymentProcess_PaymentDetailsTaskDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/FakeAirBnBProcess/task/PaymentDetails/:taskInstanceId/execute',
    name: 'PaymentProcess_PaymentDetailsTaskExecute',
    component: PaymentProcess_PaymentDetailsTaskExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/FakeAirBnBProcess/init',
    name: 'PaymentStartFormInit',
    component: PaymentStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
