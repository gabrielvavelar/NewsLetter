# Notic.ia

## Sobre o Projeto

`Notic.ia` é uma aplicação web em Spring Boot MVC que envia diariamente resumos das principais notícias do G1 para usuários inscritos.
- O sistema faz web scraping do site do G1 para extrair notícias em destaque.
- Em seguida, utiliza a API do Gemini para gerar resumos concisos.
- Os resumos são enviados automaticamente para os inscritos às 7h da manhã.
- Usuários podem se inscrever ou cancelar a inscrição apenas com o e-mail.

## 📌 Detalhes do Projeto
- Apesar do G1 fornecer RSS, optei por usar web scraping para garantir que as notícias coletadas fossem  estavam em destaque
- Arquitetura MVC

## 👨‍💻 Autor
[Gabriel Venancio de Avelar](https://github.com/gabrielvavelar)
