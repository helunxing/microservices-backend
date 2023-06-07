# Event Microservice

This microservice return events data and handle change request.

Default port number can find in [here](../README.md#local-urls).

Unit test for every http api can find in [here](./req.http).

## `/events`

### GET `/events`

Get all events.

## `/event`

### POST `/event`

Create a new event.

## `/event/{eventId}`

### GET `/event/{eventId}`

Get event by id.

### PUT `/event/{eventId}`

Update event by id.

### DELETE `/event/{eventId}`

Delete event by id.
