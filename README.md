# Let's Talk

Projeto de estudo em Java 21 e Spring Boot para construir um chat em tempo real com salas, mensagens e lista de usuários online. O chat usa WebSocket com STOMP para manter a conexão aberta, JWT para autenticar os usuários e Redis Pub/Sub para distribuir mensagens entre duas instâncias do backend.

**Meta final:** Ana se conecta ao servidor A e Bruno ao servidor B. Depois do login, os dois entram na sala `geral`, aparecem na lista de usuários online e conversam sem atualizar a página. Só participa quem tem um JWT válido, e cada sala recebe apenas as suas mensagens.

## Checklist de desenvolvimento

Marque cada etapa com `[x]` depois de demonstrar a conquista. A primeira meta é chegar à etapa 3 e conversar entre duas abas.

- [x] **1. Colocar o backend no ar**  
  Crie o backend com Java 21, Spring Boot, Maven e Spring Web MVC. Implemente `GET /api/status`.  
  **Conquista:** abra `http://localhost:8080/api/status` no navegador e veja `{"status":"online"}`.

- [x] **2. Receber a primeira mensagem do servidor**  
  Configure WebSocket com STOMP no endpoint `/ws`. Crie uma página HTML com um botão **Conectar** e faça o servidor publicar o horário a cada cinco segundos em um tópico de teste.  
  **Conquista:** clique uma única vez em **Conectar** e veja três horários chegarem sem atualizar a página.

- [ ] **3. Conversar na primeira sala**  
  Crie a sala `geral`. Envie mensagens para `/app/salas/geral/mensagens` e assine `/topic/salas/geral/mensagens` para recebê-las.  
  **Conquista:** abra duas abas, envie “Oi!” em uma e responda pela outra. As duas exibem a conversa.

- [ ] **4. Entrar com JWT**  
  Adicione Spring Security, crie duas contas de teste com senhas armazenadas como hash e implemente `POST /api/auth/login`. Valide o JWT enviado no cabeçalho `Authorization` do quadro STOMP `CONNECT`.  
  **Conquista:** conecte Ana com um token válido. Repita sem token e com um token vencido e confirme que o servidor recusa as duas tentativas.

- [ ] **5. Controlar o acesso por sala**  
  Crie a sala `estudos` e a ação **Entrar**. Antes de aceitar `SEND` ou `SUBSCRIBE`, verifique se o usuário entrou na sala de destino. Identifique o autor da mensagem pelo JWT.  
  **Conquista:** Ana e Bruno conversam em `geral`. Uma terceira conta em `estudos` não recebe a conversa. Se tentar assinar `geral` sem entrar, o servidor recusa.

- [ ] **6. Mostrar quem está online**  
  Registre as sessões por usuário e por sala. Mostre a lista atual a quem entra e avise os participantes quando ela mudar.  
  **Conquista:** abra duas abas como Ana e uma como Bruno. Ana aparece uma única vez na lista de Bruno e só desaparece quando fecha sua última aba.

- [ ] **7. Distribuir mensagens com Redis Pub/Sub**  
  Adicione Spring Data Redis. Publique cada mensagem aceita em um canal do Redis. Ao receber o evento, cada instância entrega a mensagem aos clientes WebSocket locais da sala.  
  **Conquista:** envie uma mensagem, acompanhe o evento no Redis e confira que cada aba recebe uma única cópia, inclusive a de quem enviou.

- [ ] **8. Conversar entre dois servidores**  
  Execute o backend nas portas `8080` e `8081`. Use o mesmo Redis e a mesma configuração de validação de JWT nas duas instâncias. Compartilhe também a presença por sala no Redis.  
  **Conquista:** conecte Ana à porta `8080` e Bruno à `8081`. Na mesma sala, eles se veem online e trocam mensagens nos dois sentidos, sem duplicatas.

- [ ] **9. Demonstrar o projeto completo**  
  Reúna os serviços `chat-a`, `chat-b` e `redis` no Docker Compose. Use sinais periódicos de atividade (*heartbeat*) e expiração da presença para lidar com quedas inesperadas.  
  **Conquista:** execute `docker compose up --build`. Com Ana e Bruno conversando em servidores diferentes, interrompa o servidor de Ana. Bruno continua conectado e vê Ana sair da lista após o prazo de expiração.