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
HTTP/1.1 200 
Content-Type: application/json 

[
  {
    "id": 1,
    "subId": "subId222",
    "loginKey": "randomKey"
  }
]
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
HTTP/1.1 201 
Location: http://localhost:8100/user/user/1
```

### GET `/user/{userId}`

Demo request:

```http request
GET http://localhost:8100/user/1
Content-Type: application/json
```

correspond respond:

```
HTTP/1.1 200 

{
  "id": 1,
  "subId": "subId222",
  "loginKey": "randomKey"
}
```

### PUT `/user/{userId}`

Demo request:

```http request
PUT http://localhost:8100/user/1
Content-Type: application/json

{
  "subId": "subIdUpdated",
  "loginKey": "keyUpdated"
}
```

correspond respond:

```
HTTP/1.1 201 
Location: http://localhost:8100/user/1/user/1
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
