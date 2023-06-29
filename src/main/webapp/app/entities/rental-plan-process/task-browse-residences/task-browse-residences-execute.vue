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
                v-text="$t('airBnBFakeApp.taskBrowseResidences.targetAddress')"
                for="task-browse-residences-targetAddress"
                >Target Address</label
              >
              <input
                type="text"
                class="form-control"
                name="targetAddress"
                id="task-browse-residences-targetAddress"
                readonly
                data-cy="targetAddress"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.targetAddress.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.targetAddress.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.targetAddress.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.taskBrowseResidences.userName')"
                for="task-browse-residences-userName"
                >User Name</label
              >
              <input
                type="text"
                class="form-control"
                name="userName"
                id="task-browse-residences-userName"
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
                v-text="$t('airBnBFakeApp.taskBrowseResidences.userEmail')"
                for="task-browse-residences-userEmail"
                >User Email</label
              >
              <input
                type="text"
                class="form-control"
                name="userEmail"
                id="task-browse-residences-userEmail"
                readonly
                data-cy="userEmail"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.userEmail.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.userEmail.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.userEmail.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.taskBrowseResidences.startDate')"
                for="task-browse-residences-startDate"
                >Start Date</label
              >
              <b-input-group class="mb-3">
                <b-form-input
                  id="task-browse-residences-startDate"
                  readonly
                  data-cy="startDate"
                  type="text"
                  class="form-control"
                  name="startDate"
                  :class="{
                    valid: !$v.taskContext.rentalPlanProcess.rentalPlan.startDate.$invalid,
                    invalid: $v.taskContext.rentalPlanProcess.rentalPlan.startDate.$invalid,
                  }"
                  v-model="$v.taskContext.rentalPlanProcess.rentalPlan.startDate.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.taskBrowseResidences.endDate')"
                for="task-browse-residences-endDate"
                >End Date</label
              >
              <b-input-group class="mb-3">
                <b-form-input
                  id="task-browse-residences-endDate"
                  readonly
                  data-cy="endDate"
                  type="text"
                  class="form-control"
                  name="endDate"
                  :class="{
                    valid: !$v.taskContext.rentalPlanProcess.rentalPlan.endDate.$invalid,
                    invalid: $v.taskContext.rentalPlanProcess.rentalPlan.endDate.$invalid,
                  }"
                  v-model="$v.taskContext.rentalPlanProcess.rentalPlan.endDate.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.taskBrowseResidences.rentalConfirmationNumber')"
                for="task-browse-residences-rentalConfirmationNumber"
                >Rental Confirmation Number</label
              >
              <input
                type="text"
                class="form-control"
                name="rentalConfirmationNumber"
                id="task-browse-residences-rentalConfirmationNumber"
                data-cy="rentalConfirmationNumber"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.rentalConfirmationNumber.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.rentalConfirmationNumber.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.rentalConfirmationNumber.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.taskBrowseResidences.livingQuarters')"
                for="task-browse-residences-livingQuarters"
                >Living Quarters</label
              >
              <select
                class="form-control"
                id="task-browse-residences-livingQuarters"
                data-cy="livingQuarters"
                name="livingQuarters"
                v-model="taskContext.rentalPlanProcess.rentalPlan.livingQuarters"
              >
                <option v-bind:value="null"></option>
                <option
                  v-bind:value="
                    taskContext.rentalPlanProcess.rentalPlan.livingQuarters &&
                    livingQuartersOption.id === taskContext.rentalPlanProcess.rentalPlan.livingQuarters.id
                      ? taskContext.rentalPlanProcess.rentalPlan.livingQuarters
                      : livingQuartersOption
                  "
                  v-for="livingQuartersOption in livingQuarters"
                  :key="livingQuartersOption.id"
                >
                  {{ livingQuartersOption.name }}
                </option>
              </select>
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

<script lang="ts" src="./task-browse-residences-execute.component.ts"></script>
