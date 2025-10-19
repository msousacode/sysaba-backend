## 🧩 Sobre o Projeto

Esse é um projeto profissional construído em **Java**, desenvolvido antes mesmo da era da IA... 😄  

O objetivo deste projeto foi criar uma **plataforma SaaS para psicólogos clínicos**, onde os usuários poderiam registrar os atendimentos realizados de forma prática e segura.  

O **backend** foi totalmente desenvolvido em **Java**, e durante o processo tive a oportunidade de realizar um **deploy real na Amazon AWS**, utilizando uma arquitetura simples, porém poderosa, com recursos como **EC2**, **API Gateway**, **RDS (PostgreSQL)**, **certificado TLS** e **domínio personalizado**. 

### 🧩 Suporte a Multitenancy

Este projeto oferece suporte a **Multitenancy**, ou seja, possui uma separação lógica que permite que vários usuários compartilhem a mesma base de dados, mantendo seus dados **isolados entre si**.  

Para isso, foram utilizadas **anotações específicas do Spring**, garantindo o gerenciamento adequado do contexto de inquilino (*tenant*) em cada requisição.  

Esse recurso é extremamente útil para quem precisa disponibilizar o mesmo sistema para **milhares de usuários**, mantendo uma **única base de dados**.  

Veja:  
- [TenantAspect.java](https://github.com/msousacode/sysaba-backend/blob/main/src/main/java/br/com/sysaba/core/aspect/TenantAspect.java)  
- [Tenantable.java](https://github.com/msousacode/sysaba-backend/blob/main/src/main/java/br/com/sysaba/core/models/Tenantable.java)


Foi um projeto completo e desafiador, no qual dediquei **cerca de um ano e meio** de muito estudo, esforço e aprendizado.  

Decidi tornar este projeto público — não fiquei rico como imaginei... 😅 — mas certamente adquiri uma enorme **riqueza de conhecimento** ao embarcar nessa jornada. Trabalhei **sozinho** no desenvolvimento de um sistema **complexo e funcional**, que chegou a ir para **produção real**, com **cinco usuários autênticos e pagantes**.  

Eventualmente, decidi encerrar o projeto e seguir por outros caminhos, mas ele continua sendo uma das experiências mais valiosas da minha carreira.  

Deixo o sistema disponível para quem desejar conhecer o meu **estilo de código** e a forma como organizei a arquitetura.  

O **frontend** deste projeto foi desenvolvido com **Vue 3**, utilizando a **Composition API** e o framework **Quasar**.  
O repositório do frontend está disponível aqui:  
🔗 [https://github.com/msousacode/teia](https://github.com/msousacode/teia)
