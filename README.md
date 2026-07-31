# ☕ Coffee Menu Service

REST API สำหรับจัดการเมนูกาแฟ (Coffee Menu Service)  
พัฒนาด้วย **Spring Boot 3** และ **Java 17**  
โปรเจกต์สำหรับ Lab 05 — รายวิชา CP353002 Principles of Software Design and Development

---

## 🛠 เทคโนโลยีที่ใช้

| เทคโนโลยี | เวอร์ชัน |
|-----------|---------|
| Java | 17 |
| Spring Boot | 3.3.5 |
| Maven | 3.x |

---

## 📁 โครงสร้างโปรเจกต์ (Layered Design)

```
com.example.coffeeshop/
├── model/
│   └── Coffee.java              # เก็บโครงสร้างข้อมูล (Entity)
├── service/
│   └── CoffeeService.java       # เก็บ Business Logic + ข้อมูลใน List
└── controller/
    └── CoffeeController.java    # รับ-ส่ง HTTP Request/Response
```

> **หลักการ:** Controller ไม่เก็บข้อมูลเอง เรียกผ่าน Service เท่านั้น

---

## 🚀 วิธีรันโปรเจกต์

### 1. รันด้วย Maven
```bash
cd coffeeshop
mvn spring-boot:run
```

### 2. รันด้วย IDE (IntelliJ IDEA / VS Code)
เปิดโปรเจกต์ → รันไฟล์ `CoffeeshopApplication.java`

> **หมายเหตุ:** ถ้า port 8080 ถูกใช้งานอยู่ ให้เพิ่ม `server.port=8081` ในไฟล์ `src/main/resources/application.properties`

---

## 📡 API Endpoints

| # | Method | Path | คำอธิบาย | HTTP Status |
|---|--------|------|----------|-------------|
| 1 | `GET` | `/coffees` | ดูเมนูทั้งหมด | `200 OK` |
| 2 | `GET` | `/coffees/{id}` | ดูเมนูตาม ID | `200 OK` / `404 Not Found` |
| 3 | `POST` | `/coffees` | เพิ่มเมนูใหม่ | `201 Created` |
| 4 | `PUT` | `/coffees/{id}` | แก้ไขเมนู | `200 OK` / `404 Not Found` |
| 5 | `DELETE` | `/coffees/{id}` | ลบเมนู | `204 No Content` / `404 Not Found` |
| 6 | `GET` | `/coffees/search?name=...` | ค้นหาตามชื่อ (โบนัส) | `200 OK` |

---

## 🧪 ตัวอย่างการทดสอบด้วย curl

### 1️⃣ GET — ดูเมนูทั้งหมด
```bash
curl http://localhost:8080/coffees
```
**ผลลัพธ์:**
```json
[
  {"id":1,"name":"Espresso","price":45.0},
  {"id":2,"name":"Latte","price":55.0}
]
```

---

### 2️⃣ GET — ดูเมนูตาม ID
```bash
curl http://localhost:8080/coffees/1
```
**ผลลัพธ์:**
```json
{"id":1,"name":"Espresso","price":45.0}
```

---

### 3️⃣ POST — เพิ่มเมนูใหม่
```bash
curl -X POST http://localhost:8080/coffees \
  -H "Content-Type: application/json" \
  -d '{"name":"Cappuccino","price":60.0}'
```
**ผลลัพธ์:**
```json
{"id":3,"name":"Cappuccino","price":60.0}
```

> ⚠️ **อย่าลืมใส่ header `-H "Content-Type: application/json"`** ถ้าไม่ใส่จะได้ error 415

---

### 4️⃣ PUT — แก้ไขเมนู
```bash
curl -X PUT http://localhost:8080/coffees/2 \
  -H "Content-Type: application/json" \
  -d '{"name":"Latte","price":50.0}'
```
**ผลลัพธ์:**
```json
{"id":2,"name":"Latte","price":50.0}
```

---

### 5️⃣ DELETE — ลบเมนู
```bash
curl -X DELETE http://localhost:8080/coffees/3
```
**ผลลัพธ์:** `HTTP/1.1 204 No Content` (ไม่มี body)

---

### 6️⃣ Search — ค้นหาตามชื่อ (โบนัส)
```bash
curl "http://localhost:8080/coffees/search?name=Lat"
```
**ผลลัพธ์:**
```json
[{"id":2,"name":"Latte","price":50.0}]
```

---

### 7️⃣ ทดสอบ 404 — ID ไม่พบ
```bash
curl -i http://localhost:8080/coffees/999
```
**ผลลัพธ์:** `HTTP/1.1 404 Not Found`

---

## ⚠️ ข้อควรระวัง

| ปัญหา | สาเหตุ | วิธีแก้ |
|-------|--------|---------|
| `415 Unsupported Media Type` | ลืมใส่ header `Content-Type: application/json` | เพิ่ม `-H "Content-Type: application/json"` |
| `404 Not Found` | หา ID ที่ไม่มีในระบบ | ตรวจสอบ ID ให้ถูกต้อง |
| Port 8080 ถูกใช้ | มี process อื่นใช้ port 8080 | เปลี่ยน port ใน `application.properties` |

---

## 📋 ข้อมูลเริ่มต้น (Seed Data)

เมื่อรันแอปครั้งแรก จะมีข้อมูลตัวอย่างอยู่ 2 รายการ:

| ID | Name | Price |
|----|------|-------|
| 1 | Espresso | 45.0 |
| 2 | Latte | 55.0 |

---

## 👤 ผู้จัดทำ

-ผู้จัดทำ: นายกรกฏ พรมทอง 673380025-8
	  นายสรวิชญ์ ทะมานันท์ 673380295-9

- **รายวิชา:** CP353002 — Principles of Software Design and Development
