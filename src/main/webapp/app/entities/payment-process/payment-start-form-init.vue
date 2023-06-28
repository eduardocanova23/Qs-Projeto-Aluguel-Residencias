<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="airBnBFakeApp.paymentStartForm.home.createOrEditLabel"
          data-cy="PaymentStartFormCreateUpdateHeading"
          v-text="$t('airBnBFakeApp.paymentStartForm.home.createOrEditLabel')"
        >
          Create or edit a PaymentStartForm
        </h2>
        <div v-if="paymentProcess.processInstance">
          <akip-show-process-definition :processDefinition="paymentProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="paymentProcess.payment">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentStartForm.userName')" for="payment-start-form-userName"
                    >User Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userName"
                    id="payment-start-form-userName"
                    data-cy="userName"
                    :class="{ valid: !$v.paymentProcess.payment.userName.$invalid, invalid: $v.paymentProcess.payment.userName.$invalid }"
                    v-model="$v.paymentProcess.payment.userName.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.paymentStartForm.userEmail')"
                    for="payment-start-form-userEmail"
                    >User Email</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userEmail"
                    id="payment-start-form-userEmail"
                    data-cy="userEmail"
                    :class="{ valid: !$v.paymentProcess.payment.userEmail.$invalid, invalid: $v.paymentProcess.payment.userEmail.$invalid }"
                    v-model="$v.paymentProcess.payment.userEmail.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.paymentProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./payment-start-form-init.component.ts"></script>
