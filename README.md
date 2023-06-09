# AirBnBFake

This application was generated using JHipster 7.0.1, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v7.0.1](https://www.jhipster.tech/documentation-archive/v7.0.1).

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

We use npm scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

```
./mvnw
npm start
```

Npm is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in [package.json](package.json). You can also run `npm update` and `npm install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `npm help update`.

The `npm run` command will list all of the scripts available to run for this project.

### PWA Support

JHipster ships with PWA (Progressive Web App) support, and it's turned off by default. One of the main components of a PWA is a service worker.

The service worker initialization code is commented out by default. To enable it, uncomment the following code in `src/main/webapp/index.html`:

```html
<script>
  if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('./service-worker.js').then(function () {
      console.log('Service Worker Registered');
    });
  }
</script>
```

Note: [Workbox](https://developers.google.com/web/tools/workbox/) powers JHipster's service worker. It dynamically generates the `service-worker.js` file.

### Managing dependencies

For example, to add [Leaflet][] library as a runtime dependency of your application, you would run following command:

```
npm install --save --save-exact leaflet
```

To benefit from TypeScript type definitions from [DefinitelyTyped][] repository in development, you would run following command:

```
npm install --save-dev --save-exact @types/leaflet
```

Then you would import the JS and CSS files specified in library's installation instructions so that [Webpack][] knows about them:
Note: There are still a few other things remaining to do for Leaflet that we won't detail here.

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the AirBnBFake application for production, run:

```
./mvnw -Pprod clean verify
```

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

```
java -jar target/*.jar
```

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./mvnw -Pprod,war clean verify
```

## Testing

To launch your application's tests, run:

```
./mvnw verify
```

### Client tests

Unit tests are run by [Jest][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

```
npm test
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

Note: we have turned off authentication in [src/main/docker/sonar.yml](src/main/docker/sonar.yml) for out of the box experience while trying out SonarQube, for real use cases turn it back on.

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/postgresql.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

```
./mvnw -Pprod verify jib:dockerBuild
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 7.0.1 archive]: https://www.jhipster.tech/documentation-archive/v7.0.1
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v7.0.1/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v7.0.1/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v7.0.1/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v7.0.1/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v7.0.1/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v7.0.1/setting-up-ci/
[node.js]: https://nodejs.org/
[webpack]: https://webpack.github.io/
[browsersync]: https://www.browsersync.io/
[jest]: https://facebook.github.io/jest/
[jasmine]: https://jasmine.github.io/2.0/introduction.html
[protractor]: https://angular.github.io/protractor/
[leaflet]: https://leafletjs.com/
[definitelytyped]: https://definitelytyped.org/




## FakeBNB Process description

We have chosen to implement a process for house and apartment renting in this project. To model the business process, Camunda 7 was used

<p align="center">
    <br />
    <a href="https://camunda.com/download/modeler/"><strong>You can download it here »</strong></a>
    <br />
    <br />
    
  </p>
</div>

The modelled process is the following:

<div align="center">
<a>
    <img src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/PROCESSONOVO.png?raw=true" alt="Logo" >
  </a>

</div>

To execute the project you will need to pull the agilekip docker image. Open a terminal and run:

```
docker pull agilekip/generator-jhipster-agilekip:v0.0.12
```

In the same terminal, create the jhipster container:

```
docker run --name generator-jhipster-pais -v $PWD:/home/jhipster/app -d -t agilekip/generator-jhipster-agilekip:v0.0.12
```



Now open the container's bash: 

```
docker container exec -it generator-jhipster-pais bash
```

Navigate to the project root folder

and finally run:
```
jhipster entity RentalPlan
```

Accept all the options offered. The next step is to execute the project in another terminal:

```
./mvnw
```

The project should appear in localhost:8080
 The screen should be similar to this:

 <div align="center">
<a>
    <img src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/InitialPage.PNG?raw=true" alt="main page" >
  </a>

</div>

After login, you can add a new available residence for rent in "Entity" from the navigation bar:

<div align="center">
<a>
    <img src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/menu.PNG?raw=true" alt="menu">
  </a>
</div>
For that , you'll need to select the "Living Quarters" option. Then, select "Create Living Quarters":

<div align="center">
<a>
    < src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/newliving.PNG?raw=true" alt="menu" >
  </a>
</div>
After this, a new form will appear which permits the addition of a residence offer

<div align="center">
<a>
    <img alt="residenceform.PNG" src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/residenceform.PNG?raw=true" data-hpc="true" class="Box-sc-g0xbh4-0 kzRgrI">
  </a>
</div>
After you deploy our bpmn process into agile kip, it should appear in Administration>Process Definitions:

<div align="center">
<a>
    <img alt="RentalPlan.PNG" src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/RentalPlan.PNG?raw=true" data-hpc="true" class="Box-sc-g0xbh4-0 kzRgrI">
  </a>
</div>
When initialized, a new form will appear asking for the desired specifications about the residence that the user wants to book:

<div align="center">
<a>
    <img alt="RentalPlan.PNG" src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/bookresidenceform.PNG?raw=true" data-hpc="true" class="Box-sc-g0xbh4-0 kzRgrI">
  </a>


  After submitting it, a new process instance is created, in which a user can browse through the available residences that match his description:
  <div align="center">
<a>
    <img alt="processstarted.PNG" src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/processstarted.PNG?raw=true" data-hpc="true" class="Box-sc-g0xbh4-0 kzRgrI">
  </a>
  </div>

You can then edit the payment details so the purchase can be efectuaded:

  <div align="center">
<a>
    <img alt="processstarted.PNG" src="https://github.com/eduardocanova23/Qs-Projeto-Aluguel-Residencias/blob/main/misc/paymentdetails.PNG?raw=true" data-hpc="true" class="Box-sc-g0xbh4-0 kzRgrI">
  </a>
  </div>
