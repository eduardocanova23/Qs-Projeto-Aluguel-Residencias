<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="airBnBFakeApp.rentalPlanStartForm.home.createOrEditLabel"
          data-cy="RentalPlanStartFormCreateUpdateHeading"
          v-text="$t('airBnBFakeApp.rentalPlanStartForm.home.createOrEditLabel')"
        >
          Create or edit a RentalPlanStartForm
        </h2>
        <div v-if="rentalPlanProcess.processInstance">
          <akip-show-process-definition :processDefinition="rentalPlanProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="rentalPlanProcess.rentalPlan">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('airBnBFakeApp.rentalPlanStartForm.city')" for="rental-plan-start-form-city"
                    >City</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="city"
                    id="rental-plan-start-form-city"
                    data-cy="city"
                    :class="{
                      valid: !$v.rentalPlanProcess.rentalPlan.city.$invalid,
                      invalid: $v.rentalPlanProcess.rentalPlan.city.$invalid,
                    }"
                    v-model="$v.rentalPlanProcess.rentalPlan.city.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.rentalPlanStartForm.neighborhood')"
                    for="rental-plan-start-form-neighborhood"
                    >Neighborhood</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="neighborhood"
                    id="rental-plan-start-form-neighborhood"
                    data-cy="neighborhood"
                    :class="{
                      valid: !$v.rentalPlanProcess.rentalPlan.neighborhood.$invalid,
                      invalid: $v.rentalPlanProcess.rentalPlan.neighborhood.$invalid,
                    }"
                    v-model="$v.rentalPlanProcess.rentalPlan.neighborhood.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.rentalPlanStartForm.startDate')"
                    for="rental-plan-start-form-startDate"
                    >Start Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="rental-plan-start-form-startDate"
                        v-model="$v.rentalPlanProcess.rentalPlan.startDate.$model"
                        name="startDate"
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
                      id="rental-plan-start-form-startDate"
                      data-cy="startDate"
                      type="text"
                      class="form-control"
                      name="startDate"
                      :class="{
                        valid: !$v.rentalPlanProcess.rentalPlan.startDate.$invalid,
                        invalid: $v.rentalPlanProcess.rentalPlan.startDate.$invalid,
                      }"
                      v-model="$v.rentalPlanProcess.rentalPlan.startDate.$model"
                    />
                  </b-input-group>
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.rentalPlanStartForm.endDate')"
                    for="rental-plan-start-form-endDate"
                    >End Date</label
                  >
                  <b-input-group class="mb-3">
                    <b-input-group-prepend>
                      <b-form-datepicker
                        aria-controls="rental-plan-start-form-endDate"
                        v-model="$v.rentalPlanProcess.rentalPlan.endDate.$model"
                        name="endDate"
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
                      id="rental-plan-start-form-endDate"
                      data-cy="endDate"
                      type="text"
                      class="form-control"
                      name="endDate"
                      :class="{
                        valid: !$v.rentalPlanProcess.rentalPlan.endDate.$invalid,
                        invalid: $v.rentalPlanProcess.rentalPlan.endDate.$invalid,
                      }"
                      v-model="$v.rentalPlanProcess.rentalPlan.endDate.$model"
                    />
                  </b-input-group>
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.rentalPlanStartForm.userName')"
                    for="rental-plan-start-form-userName"
                    >User Name</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userName"
                    id="rental-plan-start-form-userName"
                    data-cy="userName"
                    :class="{
                      valid: !$v.rentalPlanProcess.rentalPlan.userName.$invalid,
                      invalid: $v.rentalPlanProcess.rentalPlan.userName.$invalid,
                    }"
                    v-model="$v.rentalPlanProcess.rentalPlan.userName.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.rentalPlanStartForm.userEmail')"
                    for="rental-plan-start-form-userEmail"
                    >User Email</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userEmail"
                    id="rental-plan-start-form-userEmail"
                    data-cy="userEmail"
                    :class="{
                      valid: !$v.rentalPlanProcess.rentalPlan.userEmail.$invalid,
                      invalid: $v.rentalPlanProcess.rentalPlan.userEmail.$invalid,
                    }"
                    v-model="$v.rentalPlanProcess.rentalPlan.userEmail.$model"
                  />
                </div>
                <div class="form-group">
                  <label
                    class="form-control-label"
                    v-text="$t('airBnBFakeApp.rentalPlanStartForm.userPhoneNumber')"
                    for="rental-plan-start-form-userPhoneNumber"
                    >User Phone Number</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="userPhoneNumber"
                    id="rental-plan-start-form-userPhoneNumber"
                    data-cy="userPhoneNumber"
                    :class="{
                      valid: !$v.rentalPlanProcess.rentalPlan.userPhoneNumber.$invalid,
                      invalid: $v.rentalPlanProcess.rentalPlan.userPhoneNumber.$invalid,
                    }"
                    v-model="$v.rentalPlanProcess.rentalPlan.userPhoneNumber.$model"
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
            :disabled="$v.rentalPlanProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./rental-plan-start-form-init.component.ts"></script>
