
# test-typedapi

This is a simple app to try out [TypedApi library](https://github.com/pheymann/typedapi) which is inspired by 
[Haskell's Servant](https://github.com/haskell-servant/servant)

### Usage instructions

1. Execute the app:
    
    `$ sbt run`

2. Insert some users by running the following curL:

    `curl -d '{"name":"User1"}' -H "Content-Type: application/json" -X PUT http://localhost:3000/user`

3. Query the just inserted users:

    `curl -H 'Content-type: application/json' localhost:3000/user/User1`
