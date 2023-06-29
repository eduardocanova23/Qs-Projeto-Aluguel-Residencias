<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="rentalPlanProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="rentalPlanProcessDetailsHeading">
          <span v-text="$t('airBnBFakeApp.rentalPlanProcess.detail.title')">RentalPlanProcess</span> {{ rentalPlanProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="rentalPlanProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('airBnBFakeApp.rentalPlanProcess.detail.title')">RentalPlanProcess</span> #{{ rentalPlanProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="rentalPlanProcess.rentalPlan">
              <h5 class="card-header">
                <span class="title"> Rental Plan #{{ rentalPlanProcess.rentalPlan.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.rentalPlanProcess.targetAddress')">targetAddress</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="targetAddress"
                    id="rental-plan-targetAddress"
                    data-cy="targetAddress"
                    v-model="rentalPlanProcess.rentalPlan.targetAddress"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.rentalPlanProcess.userName')">userName</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="userName"
                    id="rental-plan-userName"
                    data-cy="userName"
                    v-model="rentalPlanProcess.rentalPlan.userName"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.rentalPlanProcess.userEmail')">userEmail</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="userEmail"
                    id="rental-plan-userEmail"
                    data-cy="userEmail"
                    v-model="rentalPlanProcess.rentalPlan.userEmail"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.rentalPlanProcess.startDate')">startDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="startDate"
                    id="rental-plan-startDate"
                    data-cy="startDate"
                    v-model="rentalPlanProcess.rentalPlan.startDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.rentalPlanProcess.endDate')">endDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="endDate"
                    id="rental-plan-endDate"
                    data-cy="endDate"
                    v-model="rentalPlanProcess.rentalPlan.endDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.rentalPlanProcess.rentalConfirmationNumber')"
                    >rentalConfirmationNumber</label
                  >
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="rentalConfirmationNumber"
                    id="rental-plan-rentalConfirmationNumber"
                    data-cy="rentalConfirmationNumber"
                    v-model="rentalPlanProcess.rentalPlan.rentalConfirmationNumber"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.rentalPlanProcess.livingQuarters')"
                    for="rental-plan-process-livingQuarters"
                    >Living Quarters</label
                  >
                  <input
                    v-if="rentalPlanProcess.rentalPlan.livingQuarters"
                    readonly
                    type="text"
                    class="form-control"
                    name="livingQuarters"
                    id="rental-plan-livingQuarters"
                    data-cy="livingQuarters"
                    :value="rentalPlanProcess.rentalPlan.livingQuarters.name"
                  />
                  <input
                    v-else
                    readonly
                    type="text"
                    class="form-control"
                    name="livingQuarters"
                    id="rental-plan-livingQuarters"
                    data-cy="livingQuarters"
                    value=""
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

<script lang="ts" src="./rental-plan-process-details.component.ts"></script>
