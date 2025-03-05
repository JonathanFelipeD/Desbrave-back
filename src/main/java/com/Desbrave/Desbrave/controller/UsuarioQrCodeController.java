// package com.Desbrave.Desbrave.controller;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowire;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.Desbrave.Desbrave.DTO.UsuarioQrCodeRequest;
// import com.Desbrave.Desbrave.model.UsuarioQrCode;
// import com.Desbrave.Desbrave.repository.UsuarioQrCodeRepository;


// @RestController
// @RequestMapping("/usuario-qrcode")
// public class UsuarioQrCodeController {

//     @Autowired
//     private UsuarioQrCodeService usuarioQrCodeService;

//     @Autowired
//     private UsuarioQrCodeRepository usuarioQrCodeRepository;

//     //associar um usuario a um qrcode
//     @PostMapping
//     public UsuarioQrCode associUsuarioQrCode(@RequestBody UsuarioQrCodeRequest request){
//         return usuarioQrCodeService.associUsuarioQrCode(usuarioQrCode.getUsuarioId(), request.getQrCodeId());
//     }

//     //listar todas as acossiacoes
//     @GetMapping
//     public List<UsuarioQrCode>listarAssociacoes(){
//         return usuarioQrCodeRepository.findAll();
//     }

//     //buscar associacoes por id de usuario
//     @GetMapping("/usuario/{usuarioId}")
//     public List<UsuarioQrCode> buscarAssociacoesPorUsuarioId(@PathVariable Long usuarioId) {
//         return usuarioQrCodeRepository.findByUsuarioId(usuarioId); // Usando o método personalizado do repository
//     }

//     @GetMapping
//     public List<UsuarioQrCode> buscarAssociacoesPorQrCodeId(@PathVariable Long qrCodeId){
//         return usuarioQrCodeRepository.findByQrCodeId(qrCodeId);
//     }
// }
