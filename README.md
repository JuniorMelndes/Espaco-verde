# Espaço Verde

O projeto de backend "Espaço Verde" é uma plataforma desenvolvida para auxiliar pequenos comerciantes no início do desenvolvimento de sua aplicação web. A principal função desse backend é fornecer recursos e funcionalidades essenciais para que os comerciantes possam criar, gerenciar e expandir seus negócios online. Também pode ser útil para estudantes que querem ingressar nesse mercado.

Algumas características e objetivos principais do projeto "Espaço Verde" incluem:

1. Gerenciamento de produtos: A plataforma permite que os comerciantes cadastrem e gerenciem seus produtos, incluindo informações como nome, descrição, preço, estoque e disponibilidade.

2. Processamento de pedidos: Os comerciantes podem receber e processar pedidos feitos pelos clientes. O backend fornece recursos para rastrear o status do pedido, gerar faturas e gerenciar o processo de entrega.

3. Autenticação e segurança: O projeto "Espaço Verde" inclui recursos de autenticação e segurança para que apenas usuários autorizados tenham acesso às informações sensíveis.

4. Feedback dos clientes: Os clientes podem compartilhar suas experiências e opiniões sobre os produtos e serviços adquiridos. Isso fornece aos comerciantes informações valiosas sobre a qualidade e a satisfação do cliente, permitindo-lhes identificar pontos fortes e áreas de melhoria.

5. Credibilidade e confiança: As avaliações dos clientes ajudam a construir credibilidade e confiança para os comerciantes. Potenciais clientes podem ler as avaliações existentes para tomar decisões de compra informadas, aumentando a confiança na marca e nos produtos.

6. Eficiência no cadastro: Comerciantes podem preparar uma planilha com os detalhes dos produtos, como nome, descrição, preço, entre outros, e fazer o upload dessa planilha no sistema. Isso agiliza o processo de cadastro em massa, especialmente quando há uma grande quantidade de produtos a serem adicionados. 

7. Escalabilidade e extensibilidade: O projeto "Espaço Verde" é projetado para ser escalável e extensível, permitindo que os comerciantes adicionem novos recursos e personalizem a plataforma de acordo com suas necessidades específicas à medida que seus negócios crescem.

## Documentação da API

```http
  GET /user/getAllProducts
  GET /user/getProduct/{idProduct}
  GET /user/getProductByName/{productName}
  POST /user/postPurchase
  POST /user/postReview
  GET /user/getReview/{idReview}
  GET /user/getReviewByProduct/{productName}
  POST /admin/postProduct
  PUT /admin/putProduct/{idProduct}
  DELETE /admin/deleteProduct/{idProduct}
  PUT /admin/putPurchase/{idPurchase}
  DELETE /admin/deletePurchase/{idPurchase}
  GET /admin/getPurchase/{idPurchase}
  PUT /admin/validatePurchase/{idPurchase}
  
```


| Parâmetro  | Tipo     | Descrição         |
|:-----------|:---------|:------------------|
| `idProduct` | `number` | Código do produto |
| `productName` | `string` | Nome do produto |
| `idReview` | `number` | Código da avaliação |
| `idPurchase` | `number` | Código da compra |



## Instalação

- Instale as dependências a partir do `pom.xml`
- [Instale](https://projectlombok.org/setup/) o plugin do Lombok na sua IDE

## Modificação

- As configurações da podem ser alteradas no `application.yml`.
- Os scripts SQL de criação/eliminação das tabelas encontram-se na pasta `sql`
- As imagens de teste encontram-se na pasta `images`.
- Um exemplo de planilha de cadastro encontra-se na pasta `planilha`
- O código está na pasta `src/main/java`.
- Os testes estão na pasta `src/test/java`.

## Gerando e executando Artefato

Para gerar e executar artefato deste projeto rode respectivamente

```bash
  mvn clean compile install
  java -jar [path do artefato] [nome da planilha]
```

## Referência

### Lombok

- [Lombok](https://projectlombok.org/)
- [Introduction to Project Lombok](https://www.baeldung.com/intro-to-project-lombok)

### Apache POI


- [Apache POI](https://poi.apache.org/)
- [Introduction to Project Apache POI](https://www.baeldung.com/java-microsoft-excel)


## Pontos Importantes

- As nomeclatura das tabelas corrobora com os scripts fornecidos, porém, se pressupõe que o schema já exista, logo, será necessário colocar o nome do seu schema nas classe que necessitam.
- No arquivo de depêndencias do projeto você pode achar o artefato do Oracle, que foi o banco de dados escolhido, porém você pode fazer a substituição por um da sua preferência, irá funcionar perfeitamente. 



