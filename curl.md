# CURL Commands to test the API

## Syntax

### General syntax
For a normal GET request, just type `curl` followed by the address.
```
curl 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticket'
```

### HTTP methods
For other methods use -X or --request:
```
curl --request GET 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticket'
```

### Setting headers
Use the --header option, add the header in a `Header-Name: Value` format:
```
curl --request POST 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticket' \
--header 'Content-Type: application/json'
```

### Adding data
Most easily you can add data directly in the call using `--data-raw`:

```
curl --request POST 'https://8080-michaelpisul-apihacking-owmmak098tz.ws-eu79.gitpod.io/ticket' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "Test",
    "status": "OPEN"
}'
```

## Example request bodies

### Ticket

```
{
    "title": "Test",
    "status": "OPEN"
}
```

### Comment

```
{
    "id": "{{id}}",
    "comment": "This is a new comment",
    "author": "Michael"
}
```