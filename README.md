# In-One
Api para APP de carteira digital para armazenar, criar e renovar os seus documentos.

## Endpoints
- Documentos
    - Listar todos
    - Selecionar

---

### Buscar Um
`GET` /inOne/api/docs/{param}

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|--
| doc_num | int | não | É o número de registro do documento.
| doc_name | string | sim | É o nome do documento.

**Exemplo de corpo do request**

```js
{
    "doc_num" : 1,
    "doc_name": "RG"
}
```

**Códigos de Exceção**

| código | descrição 
|-|-
| 201 | Cadastrado com sucesso
| 400 | Erro de cadastro

---
### Buscar Todos
`GET` /inOne/api/docs

**Exemplo de corpo da resposta**

```js
{
    "doc_num": 446.283.557,
    "doc_digit": x,
    "doc_name": "RG",
    "doc_body": "assets/image/user/cats/doc/rg.pdf"
}
```

**Códigos de Exceção**

| código | descrição 
|-|-
| 200 | Ok
| 400 | Erro de busca

---
