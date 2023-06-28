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
                  valid: !$v.taskContext.paymentProcess.payment.cardNumber.$invalid,
                  invalid: $v.taskContext.paymentProcess.payment.cardNumber.$invalid,
                }"
                v-model="$v.taskContext.paymentProcess.payment.cardNumber.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentDetailsTask.CVV')" for="payment-details-task-CVV"
                >CVV</label
              >
              <input
                type="text"
                class="form-control"
                name="CVV"
                id="payment-details-task-CVV"
                data-cy="CVV"
                :class="{
                  valid: !$v.taskContext.paymentProcess.payment.CVV.$invalid,
                  invalid: $v.taskContext.paymentProcess.payment.CVV.$invalid,
                }"
                v-model="$v.taskContext.paymentProcess.payment.CVV.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.paymentDetailsTask.expirationDate')"
                for="payment-details-task-expirationDate"
                >Expiration Date</label
              >
              <input
                type="text"
                class="form-control"
                name="expirationDate"
                id="payment-details-task-expirationDate"
                data-cy="expirationDate"
                :class="{
                  valid: !$v.taskContext.paymentProcess.payment.expirationDate.$invalid,
                  invalid: $v.taskContext.paymentProcess.payment.expirationDate.$invalid,
                }"
                v-model="$v.taskContext.paymentProcess.payment.expirationDate.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.paymentDetailsTask.phoneNumber')"
                for="payment-details-task-phoneNumber"
                >Phone Number</label
              >
              <input
                type="text"
                class="form-control"
                name="phoneNumber"
                id="payment-details-task-phoneNumber"
                data-cy="phoneNumber"
                :class="{
                  valid: !$v.taskContext.paymentProcess.payment.phoneNumber.$invalid,
                  invalid: $v.taskContext.paymentProcess.payment.phoneNumber.$invalid,
                }"
                v-model="$v.taskContext.paymentProcess.payment.phoneNumber.$model"
              />
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
