# Spring AI with OpenAI


This project contains a web service that will accept HTTP GET requests at
`http://localhost:8080/ai/`.

There is optional `message` parameter whose default value is "Tell me a joke".

The response to the request is from the OpenAI ChatGPT Service.

## [Content](#Content)

[Tech Stack](#Tech-Stack)  
[Prerequisites](#Prerequisites)  
[Building and running](#Building-and-running)  
[Access the endpoint](#Access-the-endpoint)  
[Running application in Docker](#Running-application-in-Docker)

[Autotests](#Autotests)

## Tech Stack

<p>
    <a href="https://spring.io/">
      <img width="15%" title="Spring" src="src/main/resources/media/Spring.svg" alt="Spring">
    </a>
    <a href="https://maven.apache.org/">
      <img width="15%" title="Maven" src="src/main/resources/media/ApacheMaven.svg" alt="Maven">
    </a>
    <a href="https://www.java.com/">
      <img width="15%" title="Java" src="src/main/resources/media/java-original.svg" alt="java">
    </a>
    <a href="https://www.jetbrains.com/">
      <img width="15%" title="IntelliJ IDEA" src="src/main/resources/media/Idea.svg" alt="IntelliJ IDEA">
    </a>
    <a href="https://junit.org/junit5/">
      <img width="15%" title="JUnit5" src="src/main/resources/media/Junit5.svg" alt="JUnit5">
    </a>
    <a href="https://rest-assured.io/">
      <img width="15%" title="Rest Assured" src="src/main/resources/media/rest-assured.png" alt="Rest Assured">
    </a>
    <a href="https://github.com/">
      <img width="15%" title="GitHub" src="src/main/resources/media/github-mark-white.svg" alt="GitHub">
    </a>
    <a href="https://swagger.io/">
      <img width="15%" title="Swagger" src="src/main/resources/media/Swagger.svg" alt="Swagger">
    </a>
</p>  

## Prerequisites

Before using the AI commands, make sure you have a developer token from OpenAI.

Create an account at [OpenAI Signup](https://platform.openai.com/signup) and generate the token at [API Keys](https://platform.openai.com/account/api-keys).

The Spring AI project defines a configuration property named `spring.ai.openai.api-key` that you should set to the value of the `API Key` obtained from `openai.com`.

Exporting an environment variable is one way to set that configuration property.
```shell
export SPRING_AI_OPENAI_API_KEY=<INSERT KEY HERE>
```
Setting the API key is all you need to run the application.

[Back to Content](#Content)

## Building and running

```
./mvnw spring-boot:run
```

[Back to Content](#Content)

## Access the endpoint
###### Haiku endpoint
To get a response to the default request of "Tell me an AI haiku"
```shell 
curl localhost:8080/ai/get-haiku
```

A sample response is 
```text
AI learns and grows,
Mimicking human thought,
Future now unfolds.
```

###### Poetry
Now using the `genre` and `theme` request parameters
```shell 
curl --location 'http://localhost:8080/ai/get-poetry?genre=joke&theme=friendship'
```

A sample response is
```json
{
  "genre":"friendship",
  "theme":"A friend is like a four-leaf clover, hard to find and lucky to have. But unlike a clover,they won't wilt in a day. They'll stick around even when you're feeling gray. So cherish your pals, they're worth more than gold. Just don't let them borrow your favorite sweater, they might leave it out in the cold!",
  "format":"joke poetry"
}
```

###### City guide
Now using the `city` and `interest` request parameters
```shell
curl -G -d "city=London" -d "interest=Good%20eatery" http://localhost:8080/ai/city-guide
```

A sample response is

```text
London is a fantastic city for food lovers, with a wide range of dining options to suit every taste and budget. Here are some tips for exploring the city
1. Visit Borough Market: This bustling food market is a must-visit for any foodie. Sample delicious artisanal bread, cheese, meats, and more from local p
2. Try traditional British fare: London is known for its classic dishes like fish and chips, pie and mash, and Sunday roast. Head to a traditional pub or
3. Explore the diverse food scene: London is a melting pot of cultures, and you can find cuisine from all over the world in the city. Visit areas like Chres.
4. Check out food markets: In addition to Borough Market, London has a number of other food markets worth exploring, such as Camden Market, Maltby Streets to satisfy any craving.
5. Book a food tour: If you want to get a taste of the city's best eateries in a short amount of time, consider booking a food tour. There are plenty of sing on a particular type of cuisine.
Overall, London is a food lover's paradise with a diverse range of dining options to suit every palate. Enjoy exploring the city's culinary delights!
```

###### Pics
To obtain a graphic response need to use a tool for managing APIs like Postman. 
```text
GET http://localhost:8080/ai/get-image?prompt=funny%20bird
```

A sample response is  
<a>
    <img title="color bird" src="src/main/resources/media/funny_bird.png" alt="color bird">
</a>

[Back to Content](#Content)

## Running application in Docker

1. Build Docker-image using Dockerfile with *name_image*
2. Run docker container using this command line:

```bash
docker run -p 8080:8080 -e spring.ai.openai.api-key=<Real OpenAI API key must be here> <name_image>
```

[Back to Content](#Content)

-----------------------------------------------------------

## Autotests

### Implemented checks

- Successful/failure Haiku creation
- Successful/failure Poem creation
- Getting city place recommendations
- Successful/failure Image creation

[Back to Content](#Content)

### Running tests locally from the terminal

`mvn clean test`

[Back to Content](#Content)
  
