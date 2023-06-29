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
              <label class="form-control-label" v-text="$t('airBnBFakeApp.bookReservationTask.city')" for="book-reservation-task-city"
                >City</label
              >
              <input
                type="text"
                class="form-control"
                name="city"
                id="book-reservation-task-city"
                readonly
                data-cy="city"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.city.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.city.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.city.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.bookReservationTask.neighborhood')"
                for="book-reservation-task-neighborhood"
                >Neighborhood</label
              >
              <input
                type="text"
                class="form-control"
                name="neighborhood"
                id="book-reservation-task-neighborhood"
                readonly
                data-cy="neighborhood"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.neighborhood.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.neighborhood.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.neighborhood.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('airBnBFakeApp.bookReservationTask.startDate')"
                for="book-reservation-task-startDate"
                >Start Date</label
              >
              <b-input-group class="mb-3">
                <b-form-input
                  id="book-reservation-task-startDate"
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
              <label class="form-control-label" v-text="$t('airBnBFakeApp.bookReservationTask.endDate')" for="book-reservation-task-endDate"
                >End Date</label
              >
              <b-input-group class="mb-3">
                <b-form-input
                  id="book-reservation-task-endDate"
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
                v-text="$t('airBnBFakeApp.bookReservationTask.confirmation')"
                for="book-reservation-task-confirmation"
                >Confirmation</label
              >
              <input
                type="checkbox"
                class="form-check"
                name="confirmation"
                id="book-reservation-task-confirmation"
                data-cy="confirmation"
                :class="{
                  valid: !$v.taskContext.rentalPlanProcess.rentalPlan.confirmation.$invalid,
                  invalid: $v.taskContext.rentalPlanProcess.rentalPlan.confirmation.$invalid,
                }"
                v-model="$v.taskContext.rentalPlanProcess.rentalPlan.confirmation.$model"
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

<script lang="ts" src="./book-reservation-task-execute.component.ts"></script>
