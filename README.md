# Orbital Mission Control Platform
## Global Solution 2026 — FIAP | Turma 3ECR
### Tema: Space Connect — Orbital Mission Control Platform

---

## Integrantes

| Nome Completo | RM |
|---|---|
| (preencher) | RM |
| (preencher) | RM |
| (preencher) | RM |

---

## Descrição do Projeto

Plataforma inteligente de controle de missão orbital inspirada no projeto Gateway da NASA. O sistema monitora sensores críticos instalados nos módulos da estação orbital, registra leituras operacionais em tempo real e gera alertas quando parâmetros excedem limites seguros.

O backend é uma API REST desenvolvida em Java com Spring Boot, responsável por receber dados do app mobile e disponibilizá-los para consulta.

---

## Entidades

### SensorModulo
Representa um sensor ou módulo computacional instalado na plataforma orbital.

| Campo | Tipo | Descrição |
|---|---|---|
| id | Long | Identificador gerado automaticamente |
| nome | String | Ex: "Sensor de Temperatura TMP-01" |
| tipo | String | Ex: "temperatura", "pressao", "radiacao", "navegacao" |
| localizacao | String | Ex: "Módulo A", "Compartimento Central" |
| ativo | Boolean | Indica se o sensor está ativo |

### LeituraSensor
Leitura operacional registrada por um sensor. Possui relacionamento `@ManyToOne` com `SensorModulo`.

| Campo | Tipo | Descrição |
|---|---|---|
| id | Long | Identificador gerado automaticamente |
| sensor | SensorModulo | Sensor que gerou a leitura (chave estrangeira sensor_id) |
| valor | Double | Valor numérico da leitura |
| unidade | String | Ex: "°C", "kPa", "Sv/h" |
| statusOperacional | String | "normal", "atencao", "critico" |
| timestamp | LocalDateTime | Data e hora da leitura |
| observacoes | String | Campo opcional |

### AlertaCritico
Alerta gerado quando um parâmetro excede limite seguro. Possui relacionamento `@ManyToOne` com `SensorModulo`.

| Campo | Tipo | Descrição |
|---|---|---|
| id | Long | Identificador gerado automaticamente |
| sensor | SensorModulo | Sensor que originou o alerta (chave estrangeira sensor_id) |
| descricao | String | Descrição do alerta |
| tipoAlerta | String | Ex: "TEMPERATURA_ALTA", "PRESSAO_BAIXA", "RADIACAO_CRITICA", "FALHA_NAVEGACAO" |
| nivelCriticidade | String | "baixo", "medio", "alto", "critico" |
| resolvido | Boolean | Indica se o alerta foi resolvido |
| timestamp | LocalDateTime | Data e hora do alerta |

---

## Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | /sensores | Cadastrar sensor |
| GET | /sensores | Listar sensores |
| GET | /sensores/{id} | Buscar sensor por ID |
| PUT | /sensores/{id} | Atualizar sensor |
| DELETE | /sensores/{id} | Remover sensor |
| POST | /leituras | Registrar leitura |
| GET | /leituras | Listar leituras |
| GET | /leituras/{id} | Buscar leitura por ID |
| PUT | /leituras/{id} | Atualizar leitura |
| DELETE | /leituras/{id} | Remover leitura |
| POST | /alertas | Criar alerta |
| GET | /alertas | Listar alertas |
| GET | /alertas/{id} | Buscar alerta por ID |
| PUT | /alertas/{id} | Atualizar alerta |
| DELETE | /alertas/{id} | Remover alerta |

---

## Como Rodar

1. Abra o projeto no **IntelliJ IDEA**
2. Aguarde o Maven baixar todas as dependências automaticamente
3. Localize e execute a classe `OrbitalMissionControlApplication.java`
4. A API estará disponível em `http://localhost:8080`

---

## H2 Console (Banco de Dados em Memória)

Acesse o console do banco H2 em:

```
http://localhost:8080/h2-console
```

Configurações de conexão:
- **JDBC URL:** `jdbc:h2:file:./data/orbital`
- **User Name:** `sa`
- **Password:** (deixar em branco)

---

## Exemplos de JSON para Postman

### POST /sensores — Cadastrar Sensor
```json
{
  "nome": "Sensor de Temperatura TMP-01",
  "tipo": "temperatura",
  "localizacao": "Módulo A",
  "ativo": true
}
```

### POST /sensores — Outros exemplos
```json
{
  "nome": "Sensor de Pressão PRS-03",
  "tipo": "pressao",
  "localizacao": "Compartimento Central",
  "ativo": true
}
```

```json
{
  "nome": "Sensor de Radiação RAD-07",
  "tipo": "radiacao",
  "localizacao": "Painel Externo Norte",
  "ativo": true
}
```

---

### POST /leituras — Registrar Leitura
> O sensor é referenciado pelo ID. Cadastre um sensor antes de registrar leituras.

```json
{
  "sensor": {"id": 1},
  "valor": 87.5,
  "unidade": "°C",
  "statusOperacional": "atencao",
  "timestamp": "2026-06-01T14:30:00",
  "observacoes": "Temperatura acima do normal no módulo A"
}
```

```json
{
  "sensor": {"id": 2},
  "valor": 101.2,
  "unidade": "kPa",
  "statusOperacional": "normal",
  "timestamp": "2026-06-01T14:32:00",
  "observacoes": null
}
```

```json
{
  "sensor": {"id": 3},
  "valor": 0.85,
  "unidade": "Sv/h",
  "statusOperacional": "critico",
  "timestamp": "2026-06-01T14:45:00",
  "observacoes": "Nível de radiação acima do limite seguro"
}
```

---

### PUT /leituras/{id} — Atualizar Leitura
```json
{
  "sensor": {"id": 1},
  "valor": 92.0,
  "unidade": "°C",
  "statusOperacional": "critico",
  "timestamp": "2026-06-01T15:00:00",
  "observacoes": "Temperatura atingiu nível crítico"
}
```

---

### POST /alertas — Criar Alerta
> O sensor é referenciado pelo ID.

```json
{
  "sensor": {"id": 1},
  "descricao": "Temperatura crítica detectada no Módulo A",
  "tipoAlerta": "TEMPERATURA_ALTA",
  "nivelCriticidade": "alto",
  "resolvido": false,
  "timestamp": "2026-06-01T14:35:00"
}
```

```json
{
  "sensor": {"id": 3},
  "descricao": "Nível de radiação ultrapassou limite máximo permitido",
  "tipoAlerta": "RADIACAO_CRITICA",
  "nivelCriticidade": "critico",
  "resolvido": false,
  "timestamp": "2026-06-01T14:46:00"
}
```

```json
{
  "sensor": {"id": 2},
  "descricao": "Queda de pressão detectada no compartimento central",
  "tipoAlerta": "PRESSAO_BAIXA",
  "nivelCriticidade": "medio",
  "resolvido": false,
  "timestamp": "2026-06-01T15:10:00"
}
```

### PUT /alertas/{id} — Marcar Alerta como Resolvido
```json
{
  "sensor": {"id": 1},
  "descricao": "Temperatura crítica detectada no Módulo A",
  "tipoAlerta": "TEMPERATURA_ALTA",
  "nivelCriticidade": "alto",
  "resolvido": true,
  "timestamp": "2026-06-01T14:35:00"
}
```

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Maven
