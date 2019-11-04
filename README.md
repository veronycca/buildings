Synopsis
--------

The aim of this API is to manage real estate registry for building. 


How to run
----------

After you run ApplicationBuilding class API will start on your default host and 8081 port. 
You can change the port in application.yml file.


API endpoints
-------------

To get data from API you can use simple curl requests or use implemented Swagger framework (http://host:port/swagger-ui.html#/)


+ /v1/buildings: 
    - Provides information about all buildings.
    - Saves new building if body provided. Exmple of request body: 
    {
      "owner": "string",
      "address": {
        "city": "string",
        "street": "string",
        "number": 0
      },
      "value": 0,
      "type": "APARTMENT",
      "size": 0
    }
    - Returns BuildingNotFoundException if ID does not exist

+ /v1/buildings/{id}: 
    - Provides information about building selected by ID.
    - Updates building selected by ID. Example of request body: 
    {
      "owner": "string",
      "address": {
        "city": "string",
        "street": "string",
        "number": 0
      },
      "value": 0,
      "type": "APARTMENT",
      "size": 0
    }
    - Returns BuildingNotFoundException if ID does not exist
    
 + /v1/buildings/owner/{owner}:
    - Provides information about buildings selected by owner
    
+ /v1/buildings/owner/{owner}/tax:
    - Calculates total yearly real estate tax for a particular owner
    
+ /v1/buildings/similar: (This endpoint requires street, city, type and size variables)
    - Returns up to 3 buildings of the same type on the same street that have the size most similar to the input parameter.
    Properties with higher market value have bigger priority.
