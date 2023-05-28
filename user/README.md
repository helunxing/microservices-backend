# User Microservice

This microservice return users data and handle change request.

Default port number can find in [here](../README.md#local-urls).

## `/users`

### GET `/users`

Demo request:

```http request
GET http://localhost:8100/users
Content-Type: application/json
```

correspond respond:

```
```

## `/user`

### POST `/user`

Demo request:

```http request
POST http://localhost:8100/user
Content-Type: application/json

{
  "subId": "subId222",
  "loginKey": "randomKey"
}
```

correspond respond:

```
```

### GET `/user/{userId}`

Demo request:

```http request
GET http://localhost:8100/user/1
Content-Type: application/json
```

correspond respond:

```
```

### PUT `/user/{userId}`

Demo request:

```http request
GET http://localhost:8100/user/1
Content-Type: application/json
```

correspond respond:

```
```

### DELETE `/user/{userId}`

Demo request:

```http request
DELETE http://localhost:8100/user/1
Content-Type: application/json
```

correspond respond:

```
```
