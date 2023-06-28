<template>
  <div>
    <h2 id="page-heading" data-cy="LivingQuartersHeading">
      <span v-text="$t('airBnBFakeApp.livingQuarters.home.title')" id="living-quarters-heading">Living Quarters</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('airBnBFakeApp.livingQuarters.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'LivingQuartersCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-living-quarters"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('airBnBFakeApp.livingQuarters.home.createLabel')"> Create a new Living Quarters </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && livingQuarters && livingQuarters.length === 0">
      <span v-text="$t('airBnBFakeApp.livingQuarters.home.notFound')">No livingQuarters found</span>
    </div>
    <div class="table-responsive" v-if="livingQuarters && livingQuarters.length > 0">
      <table class="table table-striped" aria-describedby="livingQuarters">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.livingQuarters.name')">Name</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.livingQuarters.city')">City</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.livingQuarters.neighborhood')">Neighborhood</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.livingQuarters.description')">Description</span></th>
            <th scope="row"><span v-text="$t('airBnBFakeApp.livingQuarters.image')">Image</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="livingQuarters in livingQuarters" :key="livingQuarters.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LivingQuartersView', params: { livingQuartersId: livingQuarters.id } }">{{
                livingQuarters.id
              }}</router-link>
            </td>
            <td>{{ livingQuarters.name }}</td>
            <td>{{ livingQuarters.city }}</td>
            <td>{{ livingQuarters.neighborhood }}</td>
            <td>{{ livingQuarters.description }}</td>
            <td>{{ livingQuarters.image }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'LivingQuartersView', params: { livingQuartersId: livingQuarters.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'LivingQuartersEdit', params: { livingQuartersId: livingQuarters.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(livingQuarters)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="airBnBFakeApp.livingQuarters.delete.question"
          data-cy="livingQuartersDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-livingQuarters-heading" v-text="$t('airBnBFakeApp.livingQuarters.delete.question', { id: removeId })">
          Are you sure you want to delete this Living Quarters?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-livingQuarters"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeLivingQuarters()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./living-quarters.component.ts"></script>
