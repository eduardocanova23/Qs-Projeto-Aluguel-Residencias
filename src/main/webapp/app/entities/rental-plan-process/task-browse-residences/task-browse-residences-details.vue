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
              <label class="form-control-label" v-text="$t('airBnBFakeApp.taskBrowseResidences.city')">city</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="city"
                id="rental-plan-city"
                data-cy="city"
                v-model="taskContext.rentalPlanProcess.rentalPlan.city"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.taskBrowseResidences.neighborhood')">neighborhood</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="neighborhood"
                id="rental-plan-neighborhood"
                data-cy="neighborhood"
                v-model="taskContext.rentalPlanProcess.rentalPlan.neighborhood"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.taskBrowseResidences.userName')">userName</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="userName"
                id="rental-plan-userName"
                data-cy="userName"
                v-model="taskContext.rentalPlanProcess.rentalPlan.userName"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.taskBrowseResidences.userEmail')">userEmail</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="userEmail"
                id="rental-plan-userEmail"
                data-cy="userEmail"
                v-model="taskContext.rentalPlanProcess.rentalPlan.userEmail"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.taskBrowseResidences.startDate')">startDate</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="startDate"
                id="rental-plan-startDate"
                data-cy="startDate"
                v-model="taskContext.rentalPlanProcess.rentalPlan.startDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.taskBrowseResidences.endDate')">endDate</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="endDate"
                id="rental-plan-endDate"
                data-cy="endDate"
                v-model="taskContext.rentalPlanProcess.rentalPlan.endDate"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('airBnBFakeApp.taskBrowseResidences.rentalConfirmationNumber')"
                >rentalConfirmationNumber</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="rentalConfirmationNumber"
                id="rental-plan-rentalConfirmationNumber"
                data-cy="rentalConfirmationNumber"
                v-model="taskContext.rentalPlanProcess.rentalPlan.rentalConfirmationNumber"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.taskBrowseResidences.livingQuarters')"
                for="task-browse-residences-livingQuarters"
                >Living Quarters</label
              >
              <input
                v-if="taskContext.rentalPlanProcess.rentalPlan.livingQuarters"
                readonly
                type="text"
                class="form-control"
                name="livingQuarters"
                id="rental-plan-livingQuarters"
                data-cy="livingQuarters"
                :value="taskContext.rentalPlanProcess.rentalPlan.livingQuarters.name"
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
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/RentalPlanProcess/task/BrowseResidences/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-browse-residences-details.component.ts"></script>
