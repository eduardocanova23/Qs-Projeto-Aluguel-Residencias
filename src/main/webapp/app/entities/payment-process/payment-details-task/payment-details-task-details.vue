<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('airBnBFakeApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentDetailsTask.cardNumber')">cardNumber</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="cardNumber"
                id="payment-cardNumber"
                data-cy="cardNumber"
                v-model="taskContext.paymentProcess.payment.cardNumber"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentDetailsTask.cardVerificationValue')"
                >cardVerificationValue</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="cardVerificationValue"
                id="payment-cardVerificationValue"
                data-cy="cardVerificationValue"
                v-model="taskContext.paymentProcess.payment.cardVerificationValue"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentDetailsTask.expirationDate')">expirationDate</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="expirationDate"
                id="payment-expirationDate"
                data-cy="expirationDate"
                v-model="taskContext.paymentProcess.payment.expirationDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentDetailsTask.phoneNumber')">phoneNumber</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="phoneNumber"
                id="payment-phoneNumber"
                data-cy="phoneNumber"
                v-model="taskContext.paymentProcess.payment.phoneNumber"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/FakeAirBnBProcess/task/PaymentDetails/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./payment-details-task-details.component.ts"></script>
