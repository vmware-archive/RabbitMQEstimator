<h1> VMware has ended active development of this project, this repository will no longer be updated.</h1><br># RabbitMQEstimator
RabbitMQEstimator - RabbitMQ configuration management database, provisioner, and heuristic analysis of traffic.<br />
<br />
This project is a result of many discussions around the use and provisioning of RabbitMQ which has become pervasive in the past year. &nbsp;The issue is that when you initially setup RabbitMQ and expose it to the different needs of the business it typically will fail. &nbsp;As the use increases, the setup out of the box does not suffice. &nbsp;This is where the RabbitMQEstimator comes into play. &nbsp;The plan is for it to be a small clustered application that will work in a Pivotal Cloud Foundry, Docker compose or bare metal environment. &nbsp;Eventually the work can be done to enable Puppet, Chef, and Ansible.<br />
<br />
Note that this code base uses ideas and code snippets from the RabbidManagement project&nbsp;<a href="https://www.blogger.com/null">https://github.com/Berico-Technologies/RabbidManagement</a><br />
<br />
This project is a REST service that provides a set of RESTful endpoints protected by OAuth 2. The REST service is based on the&nbsp;<a href="https://spring.io/guides/gs/rest-service/">Building a RESTful Web Service</a>&nbsp;getting started guide. This project incorporates the new Java-based configuration support, now available in Spring Security OAuth 2.0.<br />
<br />
This project builds multiple virtual RabbitMQ clusters in an internal database through a provided resource based REST interface. This set of clusters is a "Project" which has clusters and then nodes.  The purpose of this is to have one place where a RabbitMQ Federation or just one RabbitMQ cluster can be stored either through provisioning the changes to the RabbitMQ cluster or provisioning the cluster through:
<br />
<ul>
<li>BOSH release</li>
<li>Docker Compose</li>
<li>Bare Metal (create the RabbitMQ config files)</li>
<li>Puppet (later)</li>
<li>Chef (later)</li>
<li>Ansible (later)</li>
</ul>
<div>
The purpose of the RabbitMQEstimator service is that once the project is provisioned, it will continually collect heuristic information about the cluster. &nbsp;This would be things like&nbsp;</div>
<div>
<ul>
<li>Drive space and memory used by queues</li>
<li>Memory used by the system overall</li>
<li>Number of Exchanges created</li>
<li>Use of Federation</li>
</ul>
<div>
Using the original template by which the project (and it's clusters) are created the RabbitMQEstimator will have the capability to automatically create the new cluster in the environments and then transition the load to the new cluster(s). &nbsp;An HAProxy instance will be used to to create the no-downtime upgrade/deployment. &nbsp;In the future it should support hardware based load balancers.</div>
</div>
<div>
<br /></div>
