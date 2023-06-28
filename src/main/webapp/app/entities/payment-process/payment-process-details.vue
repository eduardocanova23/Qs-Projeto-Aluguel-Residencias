<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="paymentProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="paymentProcessDetailsHeading">
          <span v-text="$t('airBnBFakeApp.paymentProcess.detail.title')">PaymentProcess</span> {{ paymentProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="paymentProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('airBnBFakeApp.paymentProcess.detail.title')">PaymentProcess</span> #{{ paymentProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="paymentProcess.payment">
              <h5 class="card-header">
                <span class="title"> Payment #{{ paymentProcess.payment.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentProcess.cardNumber')">cardNumber</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="cardNumber"
                    id="payment-cardNumber"
                    data-cy="cardNumber"
                    v-model="paymentProcess.payment.cardNumber"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentProcess.CVV')">CVV</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="CVV"
                    id="payment-CVV"
                    data-cy="CVV"
                    v-model="paymentProcess.payment.CVV"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentProcess.expirationDate')">expirationDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="expirationDate"
                    id="payment-expirationDate"
                    data-cy="expirationDate"
                    v-model="paymentProcess.payment.expirationDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentProcess.userName')">userName</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="userName"
                    id="payment-userName"
                    data-cy="userName"
                    v-model="paymentProcess.payment.userName"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentProcess.userEmail')">userEmail</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="userEmail"
                    id="payment-userEmail"
                    data-cy="userEmail"
                    v-model="paymentProcess.payment.userEmail"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.paymentProcess.phoneNumber')">phoneNumber</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="phoneNumber"
                    id="payment-phoneNumber"
                    data-cy="phoneNumber"
                    v-model="paymentProcess.payment.phoneNumber"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./payment-process-details.component.ts"></script>
