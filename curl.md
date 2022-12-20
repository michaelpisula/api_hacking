# CURL Commands to test the API

## GET

```
curl --location --request GET 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticket'
```

## POST

```
curl --location --request POST 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticket' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "Test",
    "status": "OPEN"
}'
```

## Status Change

```
curl --location --request GET 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticketStatusChange?id=7d811b14-d892-4032-a7e0-a0ba1db09518&status=IN_PROGRESS'
```

## PUT

```
curl --location --request PUT 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticketUpdate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "7d811b14-d892-4032-a7e0-a0ba1db09518",
    "comment": "This is a new comment",
    "author": "Michael"
}'
```