<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="rentalPlan">
        <h2 class="jh-entity-heading" data-cy="rentalPlanDetailsHeading">
          <span v-text="$t('airBnBFakeApp.rentalPlan.detail.title')">RentalPlan</span> {{ rentalPlan.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.city')">City</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.city }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.neighborhood')">Neighborhood</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.neighborhood }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.userName')">User Name</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.userName }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.userEmail')">User Email</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.userEmail }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.startDate')">Start Date</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.startDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.endDate')">End Date</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.endDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.rentalConfirmationNumber')">Rental Confirmation Number</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.rentalConfirmationNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.cardNumber')">Card Number</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.cardNumber }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.cardVerificationValue')">Card Verification Value</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.cardVerificationValue }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.expirationDate')">Expiration Date</span>
          </dt>
          <dd>
            <span>{{ rentalPlan.expirationDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.rentalPlan.livingQuarters')">Living Quarters</span>
          </dt>
          <dd>
            <div v-if="rentalPlan.livingQuarters">
              <router-link :to="{ name: 'LivingQuartersView', params: { livingQuartersId: rentalPlan.livingQuarters.id } }">{{
                rentalPlan.livingQuarters.name
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./rental-plan-details.component.ts"></script>
