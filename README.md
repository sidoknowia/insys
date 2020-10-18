# Insys
Insys is Open Source IoT Platform that is being built for Manufacturing Industries



Phase 1 - Core API

- Ingestion
  Insys uses Kafka's streaming capabilities to create an ingestion layer. Remote devices will need to publish to a topic. Insys will consume from these 
  topics and store the data in influxDb
  
- Device Management
  Insys is built using Springboot. A set of api's will be offered to create an enterprise structure and manage the fleet of devices. Only registered 
  devices will be able to publish
  
- Authentication and Authorization
  
  
Phase 2 - UI

Phase 3 - Industrilization

Phase 4 - Machine Learning Capabilities

