package test.ss04ex01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/incident")
public class SystemConfigController {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Value("${spring.ai.ollama.chat.options.model:${spring.ai.openai.chat.options.model:N/A}}")
    private String activeModel;

    @GetMapping("/config")
    public Map<String, Object> getConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("activeProfile", activeProfile);
        config.put("activeModel", activeModel);
        config.put("status", "SUCCESS");
        return config;
    }
}