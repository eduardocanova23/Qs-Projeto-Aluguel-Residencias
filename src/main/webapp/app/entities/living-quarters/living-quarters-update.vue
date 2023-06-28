<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="airBnBFakeApp.livingQuarters.home.createOrEditLabel"
          data-cy="LivingQuartersCreateUpdateHeading"
          v-text="$t('airBnBFakeApp.livingQuarters.home.createOrEditLabel')"
        >
          Create or edit a LivingQuarters
        </h2>
        <div>
          <div class="form-group" v-if="livingQuarters.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="livingQuarters.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('airBnBFakeApp.livingQuarters.name')" for="living-quarters-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="living-quarters-name"
              data-cy="name"
              :class="{ valid: !$v.livingQuarters.name.$invalid, invalid: $v.livingQuarters.name.$invalid }"
              v-model="$v.livingQuarters.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('airBnBFakeApp.livingQuarters.city')" for="living-quarters-city">City</label>
            <input
              type="text"
              class="form-control"
              name="city"
              id="living-quarters-city"
              data-cy="city"
              :class="{ valid: !$v.livingQuarters.city.$invalid, invalid: $v.livingQuarters.city.$invalid }"
              v-model="$v.livingQuarters.city.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('airBnBFakeApp.livingQuarters.neighborhood')" for="living-quarters-neighborhood"
              >Neighborhood</label
            >
            <input
              type="text"
              class="form-control"
              name="neighborhood"
              id="living-quarters-neighborhood"
              data-cy="neighborhood"
              :class="{ valid: !$v.livingQuarters.neighborhood.$invalid, invalid: $v.livingQuarters.neighborhood.$invalid }"
              v-model="$v.livingQuarters.neighborhood.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('airBnBFakeApp.livingQuarters.description')" for="living-quarters-description"
              >Description</label
            >
            <input
              type="text"
              class="form-control"
              name="description"
              id="living-quarters-description"
              data-cy="description"
              :class="{ valid: !$v.livingQuarters.description.$invalid, invalid: $v.livingQuarters.description.$invalid }"
              v-model="$v.livingQuarters.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('airBnBFakeApp.livingQuarters.image')" for="living-quarters-image">Image</label>
            <input
              type="text"
              class="form-control"
              name="image"
              id="living-quarters-image"
              data-cy="image"
              :class="{ valid: !$v.livingQuarters.image.$invalid, invalid: $v.livingQuarters.image.$invalid }"
              v-model="$v.livingQuarters.image.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.livingQuarters.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./living-quarters-update.component.ts"></script>
