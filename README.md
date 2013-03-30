#errai-math

A basic calculator and statistics application which demonstrates the following components of the JBoss Errai framework:

* Errai UI
* Errai JAX-RS
* Errai CDI
* Errai DataBinding

This application allows users to perform various basic math operations. The results of the calculation are presented to the user and _all_ calculations performed on the server are displayed within the calculations scoreboard panel

##Running and deploying

This Maven based project is configured to run on multiple application containers including GWT development mode (standalone/Eclipse), JBoss AS7/EAP6 and OpenShift

Requirements: 

* Apache Maven


####Running from JBoss Developer Studio/Eclipse


By default, GWT projects run in either Eclipse or using the standalone GWT development mode both launch a Jetty Instance. CDI support in Jetty is enabled by adding the following to either the _gwt-maven-plugin_ within the _pom.xml_ or in the Eclipse Configuration:

    org.jboss.errai.cdi.server.gwt.JettyLauncher

The _pom.xml_ is already configured to run the standalone GWT development mode, but the Eclipse configuration must be done manually. Import the project into Eclipse as an existing Maven Project. Launch the application in development mode by right clicking on the project -> **Run As** -> **Web Application**. Click the red server stop button on the Development Mode tab. Right click on the project once again -> ** Run As** -> **Run Configurations...**. Click on the **Arguments** tab. Modify the _war_ switch to point to the _errai-math-0.0.1-SNAPSHOT_ folder within the _target_ folder of your application. Additionally, add the following switch:

    -server org.jboss.errai.cdi.server.gwt.JettyLauncher

Your program arguments should look similar to the following:

    -war C:\Projects\Errai\errai-math\target\errai-math-0.0.1-SNAPSHOT -remoteUI "${gwt_remote_ui_server_port}:${unique_id}" -startupUrl ErraiMath.html -logLevel INFO -codeServerPort 9997 -port 8888 -server org.jboss.errai.cdi.server.gwt.JettyLauncher com.redhat.errai.math.ErraiMath

Click *Run* to launch development mode. Double click on the URL provided the application in a web browser

####Running from the Command Line using GWT Development Mode

From the command, run the following command
    
    mvn clean install
    mvn gwt:run
    
When the GWT Development Mode window appears, click **Launch Default Browser**

**Note**: Due to issues experienced with CDI injection with JAX-RS in Jetty, the statistics scoreboard will not update while running in development mode in either standalone mode or within Eclipse

####Deploying to JBoss EAP6/AS7

Build the project using the following command

	mvn clean install -P eap6
	
Deploy and enable the generated war file through the management console or by placing the .war file in the deployments directory within JBoss

####Deploying to OpenShift

The project is configured to build an artifact for deployment to Red Hat's OpenShit PaaS. To build the project for OpenShift deployment, run the following command:

  mvn clean install -P openshift

This will build the project and generate a file called _ROOT.war_. This file can be deployed to the root context of your OpenShift application. See the [OpenShift User Guide](https://access.redhat.com/knowledge/docs/en-US/OpenShift/2.0/html-single/User_Guide/index.html#sect-OpenShift-User_Guide-Editing_and_Deploying_Applications-Deploying_Your_Application_to_the_Cloud) for instruction on how to deploy pre-built artifacts to OpenShift