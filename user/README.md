# User Microservice

## `/users`

### GET `/users`

Demo request:

```http request
GET http://localhost:8100/users
Content-Type: application/json
```

correspond respond:

```
HTTP/1.1 201
Location: http://localhost:8100/user/user/1
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
