Sistema backend robusto desenvolvido para o controle financeiro inteligente de assinaturas, despesas recorrentes e serviços mensais. A aplicação automatiza a gestão do ciclo de vida dos pagamentos, calculando em tempo real o status de cada cobrança (Em dia, Atrasado ou Suspenso) com base na data de vencimento, além de consolidar métricas essenciais como o valor total das faturas e o montante acumulado em atraso para uma tomada de decisão rápida.

Funcionalidades
Gestão Completa de Assinaturas: Cadastro, edição, listagem e exclusão de serviços, planos e custos fixos mensais.

Cálculo Dinâmico de Status: Atualização automática e inteligente do estado da assinatura (Ativa, Vencida/Atrasada ou Suspensa) comparando a data atual com o vencimento.

Dashboard Financeiro Consolidado: Agregação em tempo real do valor total das faturas e do total em atraso, oferecendo visibilidade imediata da saúde financeira.

Tecnologias e Ferramentas
Java 21
Spring Boot 3
Spring Data JPA / Hibernate
H2 SQL
Lombok

Front-end Integrado: Interface web limpa e responsiva utilizando HTML, CSS e JavaScript para consumo da API.

Boas Práticas e Arquitetura
Arquitetura em Camadas: Separação clara de responsabilidades entre Controllers, Services e Repositories seguindo os padrões da indústria.

Uso de DTOs (Records): Transferência de dados otimizada e imutável, garantindo maior segurança e clareza nos contratos da API.

Código Limpo e Escalável: Estrutura preparada para expansão de regras de negócios, como notificações de vencimento e relatórios de gastos.
