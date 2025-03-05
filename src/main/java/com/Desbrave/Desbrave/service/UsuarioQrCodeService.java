// package com.Desbrave.Desbrave.service;

// import java.time.LocalDateTime;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.Desbrave.Desbrave.model.Usuario;
// import com.Desbrave.Desbrave.model.UsuarioQrCode;
// import com.Desbrave.Desbrave.repository.UsuarioQrCodeRepository;
// import com.Desbrave.Desbrave.repository.UsuarioRepository;

// @Service
// public class UsuarioQrCodeService {

//     @Autowired
//     private UsuarioRepository usuarioRepository;

//     @Autowired
//     private UsuarioQrCodeRepository usuarioQrCodeRepository;

//     @Autowired
//     private QrcodeRepository qrCodeRepository;

//     public void associarUsuarioQrCode(Long usuarioId, long qr_code_id){
//         Usuario usuario = usuarioRepository.findById(usuarioId)
//         .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

//         Qrcode qrcode = qrCodeRepository.findById(qr_code_id)
//         .orElseThrow(()-> new RuntimeException("Qr Code não encontrado"));
        
//         UsuarioQrCode usuarioQrCode = new UsuarioQrCode();
//         usuarioQrCode.setUsuario(usuario);
//         usuarioQrCode.setQrCode(qrcode);
//         usuarioQrCode.setDataEscaneamento(LocalDateTime.now());

//         usuarioQrCodeRepository.save(usuarioQrCode);
//     }

// }
