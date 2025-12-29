# SGPS-API
Sistema de Gestão de Processos Seletivos (SGPS)  
API REST desenvolvida para demonstrar arquitetura, boas práticas e escalabilidade.

---

## 📌 Sobre o Projeto

O **SGPS — Sistema de Gestão de Processos Seletivos** é uma plataforma moderna para gerenciamento de processos seletivos, incluindo:

- Cadastro de candidatos
- Cadastro de vagas
- Inscrições com regras de negócio
- Etapas
- Notificações

O objetivo do projeto é mostrar uma evolução arquitetural real:

1. **API Monolítica Tradicional**
2. **API Abordagem Domain Driven Design (DDD)**
3. **Arquitetura Limpa (Clean Architecture / Hexagonal)**
4. **Arquitetura Modular**

Esse repositório representa a **Etapa 2**: API com abordagem Domain Driven Design (DDD).

---

## 🏗️ Arquitetura Inicial (Etapa 2)

A arquitetura inicial segue o padrão tradicional:

Infra-estrutura → Aplicação → Dominio

## 🚀 Tecnologias (Etapa 2)

- **Java 21+**
- **Spring Boot 3.5.8**
- Spring Web