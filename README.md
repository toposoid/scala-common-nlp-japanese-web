# scala-common-nlp-japanese-web
This is a WEB API that works as a microservice within the Toposoid project.
Toposoid is a knowledge base construction platform.(see [Toposoid　Root Project](https://github.com/toposoid/toposoid.git))
This Microservice provides an NLP function that handles Japanese and outputs the result in JSON.

[![Unit Test And Build Image Action](https://github.com/toposoid/toposoid-sentence-parser-web/actions/workflows/action.yml/badge.svg?branch=main)](https://github.com/toposoid/toposoid-sentence-parser-web/actions/workflows/action.yml)

<img width="1093" alt="2021-11-22 19 23 01" src="https://user-images.githubusercontent.com/82787843/142844640-e1a8e27d-2b5b-4dbb-8e3d-c2ee20a6c53a.png">

## Requirements
* Docker version 20.10.x, or later
* docker-compose version 1.22.x

### Memory requirements
* Required: at least 6GB of RAM
* Required: 10G or higher　of HDD

## Setup
```bssh
docker-compose up -d
```
It takes more than 20 minutes to pull the Docker image for the first time.

## Usage
```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "synonyms": [
        "フィルム"
    ]
}
' http://localhost:9006/getSynonyms
```

# Note
* This microservice uses 9006 as the default port.
* If you want to run in a remote environment or a virtual environment, change PRIVATE_IP_ADDRESS in docker-compose.yml according to your environment.
* Currently, only the function to get synonyms is open to the public in this API.

## License
toposoid/scala-common-nlp-japanese-web is Open Source software released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).

## Author
* Makoto Kubodera([Linked Ideal LLC.](https://linked-ideal.com/))

Thank you!
