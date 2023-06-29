<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="payment">
        <h2 class="jh-entity-heading" data-cy="paymentDetailsHeading">
          <span v-text="$t('airBnBFakeApp.payment.detail.title')">Payment</span> {{ payment.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('airBnBFakeApp.payment.cardNumber')">Card Number</span>
          </dt>
          <dd>
            <span>{{ payment.cardNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.payment.cardVerificationValue')">Card Verification Value</span>
          </dt>
          <dd>
            <span>{{ payment.cardVerificationValue }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.payment.expirationDate')">Expiration Date</span>
          </dt>
          <dd>
            <span>{{ payment.expirationDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.payment.userName')">User Name</span>
          </dt>
          <dd>
            <span>{{ payment.userName }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.payment.userEmail')">User Email</span>
          </dt>
          <dd>
            <span>{{ payment.userEmail }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.payment.phoneNumber')">Phone Number</span>
          </dt>
          <dd>
            <span>{{ payment.phoneNumber }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./payment-details.component.ts"></script>
