
# Location service

Part of a multi module project. Handles geographical locations and tags.

## Class Diagram

```mermaid
classDiagram
    Account <|-- AccountRole
    Account --|> POI
    Order <|--OrderItem
    Account --|> Provider
    Provider <|-- Item
    Item <|--SubItem
    Provider --|> Order
    Account --|> Order
    POI <|-- Tag
    OrderItem <|--Item
    OrderItem <|-- OrderSubItem
    OrderSubItem <|-- SubItem
    Order --|> SQSMessageSender
    SQSMessageSender --|> SQSMessageListener
    SQSMessageListener --|> SQSMessageHandler
    SQSMessageHandler --|> OrderTransitionLog
    Order --|> OrderTransitionLog
    Account : +UUID ID
    Account : +String status
    Account : +AccountRole role
    Account: +createAccount()
    class SQSMessageListener {
        +SQSMessageHandler Handler
        +receiveMessage()
    }
    class SQSMessageSender {
        +Order order
        +amazonSQSAsync.sendMessage()
    }
    class SQSMessageHandler {
        +StageHandler StageHandler
        +handle()
    }
    class OrderTransitionLog {
        +String ID
        +Order order
    }
    class OrderItem {
        +Long ID
        +Long providerItemID
        +Integer PriceCents
        +List OrderSubItem
    }
    class OrderSubItem{
        +Long ID
        +Long providerSubItemID
        +Integer PriceCents
        +List OrderSubItem
    }
    class Tag {
        +POI poi
        +String tag
    }
    class AccountRole {
        +UUID roleID
        +UUID accountID
        +String status
        +createAccountRole()
        +getAccountRole()
    }
    class Provider{
      +Long ID
      +UUID accountID
      +String name
      +List Item
      +addProvider()
      +patchProvider()
      +getAllProviders()
      +getAllByTitle()
    }
    class Item {
        +Long ID
        +String title
        +Integer PriceCents
        +List SubItem
        +addItem()
        +updateItem()
        +getItemByID()
        +getAllByTitle()
    }
    class SubItem {
        +Long ID
        +String title
        +Integer PriceCents
        +getAllByTitle()
    }
    class POI{
      +Long ID
      +Float latitude
      +Float longitude
      +List Tag
      +getNearestPois()
      +addPoi()
      +getPoi()
      +updatePoi()
    }
    class Order{
      +Long ID
      +String orderNumber
      +Long providerID
      +UUID clientID
      +Integer totalPriceCents
      +createOrder()
      +getOrder()
    }
```

## Features

- Store account data and roles
- CRUD operations for accounts and roles
- Account role verification
- Unit and integration tests for positive and negative scenarios
- Code generation using openAPI
- Postgis geography
## Tech Stack

- Java Spring Boot
- PostgreSQL
- Dockerfile and docker-compose file
- Spotless Gradle
- GitHub actions which ensure all tests pass and Spotless has been applied
- Flyway migration scripts
- API specification using OpenAPI specification

## API Reference

#### Healtcheck

```http
  GET /api/v1/healthcheck
```

#### Create POI [.http file](https://github.com/NikoIskra/location-service/blob/main/requests/create_poi.http)

```http
  POST /api/v1/poi
```

Header: 

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `account-id`      | `uuid` | **Required**. Id of account to create location |

Request body:

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `externalId`      | `string` | **Required**. Poi external id |
| `name`      | `string` | **Required**. Poi name |
| `type`      | `string` | **Required**. Poi type, available values : ["service", "market", "restaurant", "grocery", "nutrition", "tools", "electronics"] |
| `tags`      | `array` | **Optional**. Poi tags, available values : ["food", "repair", "restaurant_food", "china_food", "pizza", "meat", "vegan", "organic", "grocery", "gift"] |
| `description`      | `array` | **Optional**. Poi description |
| `latitude`      | `float` | **Required**. Poi latitude |
| `longitude`      | `float` | **Required**. Poi longitude |


#### Get POI [.http file](https://github.com/NikoIskra/location-service/blob/main/requests/get_poi.http)

```http
  GET /api/v1/poi/{poi-id}
```

Header: 

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `account-id`      | `uuid` | **Required**. Id of account to create poi |
| `poi-id`      | `integer` | **Required**. Id of poi to retrieve |

#### Update POI [.http file](https://github.com/NikoIskra/location-service/blob/main/requests/update_poi.http)

```http
  PUT api/v1/poi/{poi-id}
```

Header: 

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `account-id`      | `uuid` | **Required**. Id of account to create poi |
| `poi-id`      | `integer` | **Required**. Id of poi to retrieve |

Request body:

| Parameter | Type     | 
| `name`      | `string` | **Required**. Poi name |
| `type`      | `string` | **Required**. Poi type, available values : ["service", "market", "restaurant", "grocery", "nutrition", "tools", "electronics"] |
| `tags`      | `array` | **Optional**. Poi tags, available values : ["food", "repair", "restaurant_food", "china_food", "pizza", "meat", "vegan", "organic", "grocery", "gift"] |
| `description`      | `array` | **Optional**. Poi description |
| `status`      | `string` | **Required**. Poi status, available values : ["visible", "hidden"] |
| `latitude`      | `float` | **Required**. Poi latitude |
| `longitude`      | `float` | **Required**. Poi longitude |

#### Get nearest POIs [.http file](https://github.com/NikoIskra/location-service/blob/main/requests/search_nearest_poi.http)

```http
  GET /api/v1/poi/distance/{meters}
```

Header: 

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `account-id`      | `uuid` | **Required**. Id of account to create poi |
| `meters`      | `integer` | **Required**. Determine max range |
| `latitude`      | `float` | **Required**. Poi latitude |
| `longitude`      | `float` | **Required**. Poi longitude |
| `page`      | `integer` | **Required**. Page number |
| `page-size`      | `integer` | **Optional**. Number of records on one page |

## Database model
POI:

- `ID : long`
- `External_id : string`
- `Name : string`
- `Type : string`
- `Description : string`
- `Latitude : real` (float)
- `Longitude : real` (float)
- `Location : geography` -> (not mapped in Spring Hibernate Entity)
- `Status : string`
- `CreatedAt : timestamp`
- `UpdatedAt : timestamp`

Tag:

- `ID : long`
- `Poi_id : long`
- `name : string`

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`DB_HOST`

`POSTGRES_DB`

`POSTGRES_USER`

`POSTGRES_PASSWORD`


## Run Locally

Clone the project

```
  git clone https://github.com/NikoIskra/location-service
```

Go to the project directory

```
  cd location-service
```

Build .jar file

```
  ./gradlew build
```

Start WSL and locate service directory

```
  cd /mnt/c/location-service
```

Run docker compose

```
docker compose up
```