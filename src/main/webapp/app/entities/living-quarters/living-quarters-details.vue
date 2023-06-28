<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="livingQuarters">
        <h2 class="jh-entity-heading" data-cy="livingQuartersDetailsHeading">
          <span v-text="$t('airBnBFakeApp.livingQuarters.detail.title')">LivingQuarters</span> {{ livingQuarters.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('airBnBFakeApp.livingQuarters.name')">Name</span>
          </dt>
          <dd>
            <span>{{ livingQuarters.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.livingQuarters.city')">City</span>
          </dt>
          <dd>
            <span>{{ livingQuarters.city }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.livingQuarters.neighborhood')">Neighborhood</span>
          </dt>
          <dd>
            <span>{{ livingQuarters.neighborhood }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.livingQuarters.description')">Description</span>
          </dt>
          <dd>
            <span>{{ livingQuarters.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('airBnBFakeApp.livingQuarters.image')">Image</span>
          </dt>
          <dd>
            <span>{{ livingQuarters.image }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="livingQuarters.id"
          :to="{ name: 'LivingQuartersEdit', params: { livingQuartersId: livingQuarters.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./living-quarters-details.component.ts"></script>
