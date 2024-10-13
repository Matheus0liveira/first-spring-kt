# User API Example Usage

## List All Users
```
curl -X GET http://localhost:8080/api/users | json_pp
```

## Get User by ID
```
curl -X GET http://localhost:8080/api/users/1 | json_pp
```

## Create a New User
```
curl -X POST http://localhost:8080/api/users \
     -H "Content-Type: application/json" \
     -d '{
  "name": "John Doe",
  "email": "johndoe@example.com"
}' | json_pp
```

## Update User by ID
```
curl -X PATCH http://localhost:8080/api/users/1 \
     -H "Content-Type: application/json" \
     -d '{
  "name": "John Smith",
  "email": "johnsmith@example.com"
}' | json_pp
```

## Delete User by ID
```
curl -X DELETE http://localhost:8080/api/users/1 | json_pp
```