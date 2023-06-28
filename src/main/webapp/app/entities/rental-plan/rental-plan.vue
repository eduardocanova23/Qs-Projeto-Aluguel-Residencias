<template>
  <div>
    <h2 id="page-heading" data-cy="RentalPlanHeading">
      <span v-text="$t('airBnBFakeApp.rentalPlan.home.title')" id="rental-plan-heading">Rental Plans</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airBnBFakeApp.rentalPlan.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && rentalPlans && rentalPlans.length === 0">
      <span v-text="$t('airBnBFakeApp.rentalPlan.home.notFound')">No rentalPlans found</span>
    </div>
    <div class="table-responsive" v-if="rentalPlans && rentalPlans.length > 0">
      <table class="table table-striped" aria-describedby="rentalPlans">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.rentalPlan.targetAddress')">Target Address</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.rentalPlan.userName')">User Name</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.rentalPlan.userEmail')">User Email</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.rentalPlan.startDate')">Start Date</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.rentalPlan.endDate')">End Date</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.rentalPlan.rentalConfirmationNumber')">Rental Confirmation Number</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rentalPlan in rentalPlans" :key="rentalPlan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RentalPlanView', params: { rentalPlanId: rentalPlan.id } }">{{ rentalPlan.id }}</router-link>
            </td>
            <td>{{ rentalPlan.targetAddress }}</td>
            <td>{{ rentalPlan.userName }}</td>
            <td>{{ rentalPlan.userEmail }}</td>
            <td>{{ rentalPlan.startDate }}</td>
            <td>{{ rentalPlan.endDate }}</td>
            <td>{{ rentalPlan.rentalConfirmationNumber }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RentalPlanView', params: { rentalPlanId: rentalPlan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="airBnBFakeApp.rentalPlan.delete.question" data-cy="rentalPlanDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-rentalPlan-heading" v-text="$t('airBnBFakeApp.rentalPlan.delete.question', { id: removeId })">
          Are you sure you want to delete this Rental Plan?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-rentalPlan"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeRentalPlan()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./rental-plan.component.ts"></script>
