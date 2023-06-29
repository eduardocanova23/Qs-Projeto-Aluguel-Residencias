<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('airBnBFakeApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentDetailsTask.userName')" for="payment-details-task-userName"
                >User Name</label
              >
              <input
                type="text"
                class="form-control"
                name="userName"
                id="payment-details-task-userName"
                readonly
                data-cy="userName"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.userName.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.userName.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.userName.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.paymentDetailsTask.cardNumber')"
                for="payment-details-task-cardNumber"
                >Card Number</label
              >
              <input
                type="text"
                class="form-control"
                name="cardNumber"
                id="payment-details-task-cardNumber"
                data-cy="cardNumber"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.cardNumber.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.cardNumber.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.cardNumber.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.paymentDetailsTask.cardVerificationValue')"
                for="payment-details-task-cardVerificationValue"
                >Card Verification Value</label
              >
              <input
                type="text"
                class="form-control"
                name="cardVerificationValue"
                id="payment-details-task-cardVerificationValue"
                data-cy="cardVerificationValue"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.cardVerificationValue.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.cardVerificationValue.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.cardVerificationValue.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.paymentDetailsTask.expirationDate')"
                for="payment-details-task-expirationDate"
                >Expiration Date</label
              >
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="payment-details-task-expirationDate"
                    v-model="$v.taskContext.rentalPlanProcess.rentalPlan.expirationDate.$model"
                    name="expirationDate"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="payment-details-task-expirationDate"
                  data-cy="expirationDate"
                  type="text"
                  class="form-control"
                  name="expirationDate"
                  :class="{
                    valid: !$v.taskContext.rentalPlanProcess.rentalPlan.expirationDate.$invalid,
                    invalid: $v.taskContext.rentalPlanProcess.rentalPlan.expirationDate.$invalid,
                  }"
                  v-model="$v.taskContext.rentalPlanProcess.rentalPlan.expirationDate.$model"
                />
              </b-input-group>
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./payment-details-task-execute.component.ts"></script>
