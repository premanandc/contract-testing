package contracts.webClient

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description("""
Represents a successful scenario to get all customers

```
given:
    
when:
    a request to get all customers
then:
    an array of customers should be returned

```
""")
  request {
    method 'GET'
    url '/customers'
  }
  response {
    status OK()
    headers {
      contentType('application/json')
    }
    body(
        (1..3).collect {
          [
              id       : $(producer(anyNumber())),
              firstName: $(consumer("First$it"), producer(regex('[\\w\\d]+'))),
              lastName : $(consumer("Last$it"), producer(regex('[\\w\\d]+'))),
              email    : $(consumer("first$it@test.com"), producer(regex('^\\S+@\\S+$')))
          ]
        }
    )
  }
}
