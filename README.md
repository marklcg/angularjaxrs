# AngularJS and JaxRS Rest Service Demo

This applicaton provides a basic working example of an AngularJS client and JaxRS REST Services.

## Pre-Requisites

* nodejs - https://nodejs.org/en/
* Angular CLI - https://github.com/angular/angular-cli#installation
* JDK 1.8 or newer - http://www.oracle.com/technetwork/java/javase/downloads/index.html
* Maven - https://maven.apache.org/
* Wildfly 11 or newer - http://wildfly.org/downloads/

## Demo

Our application is going to consist of two components:

* The AngularJS client
* JaxRS Services deployed to a WildFly Server

For this project, we are going to organize both components in the same repository.  In a real world application, these may be in different repositories.

Let's create two folders in our project folder:

```sh
  ng new client
  mkdir services
```

Note that we generically called this services.  In a real world project you may choose to create multipe independantly deployable modules.

Let's initiliaze our AngularJS application to include bootstrap:

```sh
  cd client
  npm install bootstrap ngx-bootstrap --save
```

Let's add the styles to our src/styles.css.  Edit the file with your favorite editor and add this line:

```css
  @import '~bootstrap/dist/css/bootstrap.min.css';
```

Let's test it:

```sh
  ng serve
```

Then browse to http://localhost:4200

If you do not see the AngularJS landing page or see error messages, retrace your steps.

Let's update the landing page with something else.  Edit src/app.component.html and replace the contents with this:

```html
<nav class="navbar navbar-default">
  <div class="container-fluid">
      <div class="navbar-header">
          <span class="navbar-brand">Angular + JaxRS</span>
      </div>
  </div>
</nav>
<main class="container">
  
</main>
```

## Services

Next we will create a pom.xml for our services which we will be building with maven.  Create and edit a pom.xml file in the services folder and paste the following content into the file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <!-- Project information -->
    <groupId>com.lcg</groupId>
    <artifactId>jaxrs-services</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <name>JaxRS Services</name>
    <description>JaxRS Services for our Angular Client</description>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Java EE 7 API Specificaton -->
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>7.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- Jax RS -->
        <dependency>
            <groupId>javax.ws.rs</groupId>
            <artifactId>javax.ws.rs-api</artifactId>
            <version>2.0.1</version>
            <scope>provided</scope>
        </dependency>
        <!-- Logging -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.25</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
</project>
```

Let's make the source folders:

```sh
  mkdir src\main\java
  mkdir src\main\webapp\WEB-INF
  mkdir src\test\java
```

## Let's Review the Source

* Show source already in place, and how it was brought over from last example

## IntelliJ and JBoss

* Import into IntelliJ (walk through, import add modules)
* Configure npm application to Run
  * Command is "start"
  * package.json from client dir
  * Node interpreter is program files nodejs install
* Configure JBoss server for run configuration: walk through
  * -Djboss.server.base.dir=C:\Projects\tools\jboss\wildfly-11.0.0.Final\apps\angularjaxrs (VM options)

## Let's see the Data

* Run and show JSON result

## Let's Display the Data

* Modify AngularJS app.component.html to display employees

Modify app.module.ts:

```js
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from "@angular/common/http";


import { AppComponent } from './app.component';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
```

Modify environment.ts:

```js
export const environment = {
  production: false,
  employeeServiceUrl: "http://localhost:8080/services/api/employee"
};
```

Modify the app.component.ts: 

```js
import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  implements OnInit {

  private readonly url = `${environment.employeeServiceUrl}/list`;

  data;

  constructor(public http: HttpClient) {
  }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.data = null;
    this.http.get(this.url, {})
      .subscribe(data => {
        console.log("Result:" + data);
        this.data = data;
      });
  }
}
```

```html
    <table *ngIf="data" class="table">
        <thead>
          <tr>
            <th>Employee No</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Date of Birth</th>
            <th>Hire Date</th>
            <th>Salary</th>
          </tr>
        </thead>
        <tr *ngFor="let employee of data">
          <td>{{ employee.employeeNo }}</td>
          <td>{{ employee.firstName }}</td>
          <td>{{ employee.lastName }}</td>
          <td>{{ employee.dateOfBirth | date }}</td>
          <td>{{ employee.hireDate | date }}</td>
          <td>{{ employee.salary }}</td>
        </tr>
    </table>
    <span *ngIf="!data">
      <i class="fa fa-circle-o-notch fa-spin" style="font-size:24px"></i>
    </span>
```

# A Refresh Button

Let's add a simple refresh button so we can see the spinner...maybe.

```html
  <div class="btn-toolbar" role="toolbar" style="margin-bottom: 16px">
    <button class="btn btn-default" (click)="refresh()">Refresh</button>
  </div>
```
