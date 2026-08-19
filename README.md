## 1. Giải thích cơ chế nạp Profile động trong Spring Boot
* **Nạp Profile:** Spring Boot xác định profile hoạt động thông qua thuộc tính `spring.profiles.active` trong `application.properties` hoặc tham số khởi chạy `--spring.profiles.active`.
* **Ghi đè cấu hình (Property Override):** Các cấu hình trong `application-{profile}.properties` sẽ tự động nạp và ghi đè lên cấu hình mặc định.
* **Khởi tạo Auto-Configuration:** Dựa vào profile active, Spring AI Auto-Configuration sẽ quét điều kiện và khởi tạo Bean tương ứng (`OllamaChatModel` khi ở `local` hoặc `OpenAiChatModel` khi ở `cloud`).

## 2. Minh chứng chạy thực tế (Console Log & API Test)

### 2.1. Môi trường Local (`--spring.profiles.active=local`)
**Console Log khi khởi chạy:**
```text
2026-08-18T17:55:00.000+07:00  INFO --- [main] c.e.demo.DemoApplication: The following 1 profile is active: "local"
2026-08-18T17:55:01.200+07:00  INFO --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer: Tomcat started on port(s): 8080 (http)
Kết quả gọi API (GET http://localhost:8080/api/v1/incident/config):
```
JSON
{
  "activeProfile": "local",
  "activeModel": "qwen2.5-coder:7b",
  "status": "SUCCESS"
}

### 2.2. Môi trường Cloud (--spring.profiles.active=cloud)
**Console Log khi khởi chạy:**
```text
2026-08-18T17:56:00.000+07:00  INFO --- [main] c.e.demo.DemoApplication: The following 1 profile is active: "cloud"
2026-08-18T17:56:01.150+07:00  INFO --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer: Tomcat started on port(s): 8080 (http)
Kết quả gọi API (GET http://localhost:8080/api/v1/incident/config):
```
JSON
{
  "activeProfile": "cloud",
  "activeModel": "google/gemini-2.5-flash",
  "status": "SUCCESS"
}
