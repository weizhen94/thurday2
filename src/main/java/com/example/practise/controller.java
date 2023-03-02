package com.example.practise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonArray;

@RestController
@RequestMapping ("/contact")
public class controller {

    @GetMapping (path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getContact(@PathVariable String name){
        JsonObject payload = Json.createObjectBuilder()
        .add("name", "John")
        .add("age", 30)
        .add("married", false)
        .build();

        System.out.printf("++++json: %s\n", payload.toString());

        //cannot do return new ResponseEntity<>(contact, HttpStatus.OK); because the object being returned will be serialised into JSON, but it is already in json due to the JsonObject payload = Json.createObjectBuilder()
        //here your ResponseEntity<String> so need payload.toString()
        return ResponseEntity.status(200)
        .header("Message", "The contact is")
        .body(payload.toString());
    }
    
    @GetMapping (path = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getContactArray (){
        JsonObject payload = Json.createObjectBuilder()
        .add("name", "John")
        .add("age", 30)
        .add("married", false)
        .build();

        //to do jsonarray, need to have a json object first, like above
        JsonArray array = Json.createArrayBuilder()
        .add(payload)
 		.build(); 

         System.out.printf("+++=array: %s\n", array.toString());

         return ResponseEntity.status(200)
         .header("Message", "The array is")
         .body(array.toString());
    }
    
}
