// package com.Desbrave.Desbrave.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.Desbrave.Desbrave.DTO.LoginRequest;
// import com.Desbrave.Desbrave.service.AutenticacaoService;

// @RestController
// @RequestMapping("/autenticacao")
// public class AutenticacaoController {

//     @Autowired
//     private AutenticacaoService autenticacaoService;

//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
//         String token = autenticacaoService.login(loginRequest);
//         return ResponseEntity.ok(token);
//     }
// }
