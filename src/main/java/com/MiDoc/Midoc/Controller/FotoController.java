package com.MiDoc.Midoc.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FotoController {

    @PostMapping("/subir-foto")
    public ResponseEntity<?> subirFoto(@RequestParam("foto") MultipartFile foto) {
        // 🛡️ Validación: archivo vacío
        if (foto.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío");
        }

        // 🛡️ Validación: tipo MIME
        String tipo = foto.getContentType();
        if (tipo == null || !tipo.startsWith("image/")) {
            return ResponseEntity.badRequest().body("Solo se permiten imágenes");
        }

        // 🛡️ Validación: tamaño máximo (opcional, aquí 5MB)
        if (foto.getSize() > 5 * 1024 * 1024) {
            return ResponseEntity.badRequest().body("La imagen es demasiado grande (máx. 5MB)");
        }

        try {
            // 📁 Asegurar que la carpeta exista
            File carpeta = new File("uploads/");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            // 🧠 Generar nombre único
            String nombreArchivo = UUID.randomUUID() + "_" + foto.getOriginalFilename();
            String ruta = "uploads/" + nombreArchivo;
            File destino = new File(ruta);

            // 💾 Guardar archivo
            foto.transferTo(destino);

            // 🌐 URL pública
            String urlPublica = "/uploads/" + nombreArchivo;
            return ResponseEntity.ok(Map.of("url", urlPublica));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al guardar la imagen");
        }
    }
}
