# User Microservice

## GET `/users`

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

## GET `/user/{userId}`

Demo request:

```http request
GET http://localhost:8100/user/1
```

correspond respond:

```

``` 

## POST `/user`

Demo request:

```http request
POST http://localhost:8100/user
Content-Type: application/json

{
  "subId": "subId222",
  "loginKey": "randomKey"
}
```

