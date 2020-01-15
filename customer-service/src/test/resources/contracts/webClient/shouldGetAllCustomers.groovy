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
        body([
                [
                        id       : 1,
                        firstName: 'First1',
                        lastName : 'Last1',
                        email    : 'first1@test.com'
                ],
                [
                        id       : 2,
                        firstName: 'First2',
                        lastName : 'Last2',
                        email    : 'first2@test.com'
                ],
                [
                        id       : 3,
                        firstName: 'First3',
                        lastName : 'Last3',
                        email    : 'first3@test.com'
                ]
        ])
    }
}
