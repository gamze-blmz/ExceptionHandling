package com.project.mergenTech.api;

import com.project.mergenTech.entity.Filtre;
import com.project.mergenTech.entity.Master;
import com.project.mergenTech.service.Impl.serviceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controller")
public class controllerService {
    private final serviceImpl serviceImpl;

    public controllerService(com.project.mergenTech.service.Impl.serviceImpl serviceImpl) {
        this.serviceImpl = serviceImpl;
    }


//    @Bean
//    public WebMvcConfigurer configure(){
//        return new WebMvcConfigurer(){
//            //            @Override
//            public void addCorsMappings(CorsRegistry registry){
//                registry.addMapping("/*").allowedOrigins("http://localhost:4200");
//            }
//        };
//    }

    @PostMapping("/create")
    public ResponseEntity<Master> createMaster(@RequestBody Master master){

           Master resultMaster=serviceImpl.createMaster(master);
           return ResponseEntity.ok(resultMaster);
    }

    @GetMapping("/getAll")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Master>> getAll(){
        List<Master> resultMaster = serviceImpl.getAll();
        return ResponseEntity.ok(resultMaster);
    }

    @GetMapping("/findAllById/{id}")
    public ResponseEntity<List<Master>> findAllById(@PathVariable("id") Long id){
        List<Master> resultMaster = serviceImpl.findAllById(id);
        return ResponseEntity.ok(resultMaster);
    }


    @GetMapping("/getMaster")
   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Master>> findallbyentity(Object kriter){

        List<Master> resultMaster = serviceImpl.findallbyentity(kriter);
        return ResponseEntity.ok(resultMaster);
    }



    @PostMapping("/getAllF") //hepsini çekme
    @CrossOrigin(origins ="http://localhost:4200")
    public ResponseEntity<List<Master>> getMastersf(@RequestBody Filtre filtre){
        List<Master> resultMasters= serviceImpl.getMastersf(filtre);
        return ResponseEntity.ok(resultMasters);
    }

//    @GetMapping("/exceptionOrnek")
//    @CrossOrigin(origins ="http://localhost:4200")
//    public Master exceptionOrnek( Filtre filtre) {
//        return serviceImpl.exceptionOrnek(filtre);
//    }


}
