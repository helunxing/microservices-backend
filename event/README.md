# Event Microservice

This microservice return events data and handle change request.

Default port number can find in [here](../README.md#local-urls).

## GET `/events`

Get all events.

Demo request:

```http request
GET http://localhost:8000/events
Content-Type: application/json
```

Correspond respond if no data:

```

```

Correspond respond if no data:

```
HTTP/1.1 404
```



## POST `/event`

Create a new event.

Demo request:
```http request
POST http://localhost:8000/event
Content-Type: application/json

{
"creatorId": 1,
"title": "Event 1",
"date": "2019-01-01",
"timeOptions": "Event 1 timeOptions"
}
```
Correspond respond:
```
HTTP/1.1 201
```

## GET `/event/{eventId}`

Get event by id. 

## PUT `/event/{eventId}`

Update event by id. No need to get user info.

## DELETE `/event/{eventId}`

Delete event by id.
