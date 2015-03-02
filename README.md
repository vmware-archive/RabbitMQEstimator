# RabbitMQEstimator
<p>RabbitMQ Estimator and provisionor application</p>
<p>Note that this code base uses ideas and code snippets from the RabbidManagement project <a>https://github.com/Berico-Technologies/RabbidManagement</a></p>
<h2>RabbitMQEstimator - RabbitMQ configuration management database, provisioner, and heuristic analysis of traffic.</h2>
<p>This project builds multiple virtual RabbitMQ clusters in an internal database through a provided resource based REST interface. This set of clusters is a "Project" which has clusters and then nodes.  The purpose of this is to have one place where a RabbitMQ Federation or just one RabbitMQ cluster can be stored.  then either provisioning the changes to the RabbitMQ cluster or provisioning the cluster through:
<ul>
<li>BOSH release</li>
<li>Docker Compose</li>
</ul>
<p> 