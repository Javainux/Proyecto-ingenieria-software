package com.MiDoc.Midoc.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<String> recibirWebhook(@RequestBody String payload) {
        System.out.println("📦 Webhook recibido:");
        System.out.println(payload); // Puedes loguearlo o procesarlo

        // Aquí podrías validar o procesar el JSON si lo deseas

        return ResponseEntity.ok("✅ Webhook recibido correctamente");
    }
}
