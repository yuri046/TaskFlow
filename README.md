<h1>TaskFlow</h1>

API REST para cadastro de usuário e gerenciamento de tarefas.

Site: https://taskflow-u543.onrender.com/

---

## Passo a passo

### 1. Registro
**Endpoint:** `/register`  
**Método:** `POST`

**Campos (JSON):**
- `email` (string)
- `name` (string)
- `password` (string)

<img width="852" height="504" alt="image" src="https://github.com/user-attachments/assets/1824f800-00df-40da-b2fe-2c62967ea3a6" />


### 2. Login
**Endpoint:** `/login`
**Método:** `POST`

**Campos (JSON):**
- `email` (string)
- `password` (string)

<img width="898" height="566" alt="image" src="https://github.com/user-attachments/assets/c730c923-a99b-4366-b213-bffad4377a49" />


### 3. Token de autorização
**Todas as funcionalidades após o login necessitam do token de autorização**

<img width="840" height="179" alt="image" src="https://github.com/user-attachments/assets/5e75acda-0eca-4305-b04a-4fb789adfc86" />

### 4. Exibir dados do usuário
**Endpoint:** `/users/me`
**Método:** `GET`

<img width="843" height="501" alt="image" src="https://github.com/user-attachments/assets/0648a4f7-6ffb-4968-8edf-e62c5d35743c" />


### 4. Atualizar dados do usuário
**Endpoint:** `/users/me`
**Método:** `PUT`

<img width="850" height="498" alt="image" src="https://github.com/user-attachments/assets/5ae282e8-ea74-47ca-96f1-217ef2974515" />


### 5. Criar tarefa
**Endpoint:** `/tasks`
**Método:** `POST`

<img width="857" height="411" alt="image" src="https://github.com/user-attachments/assets/39359871-f47e-4b87-b388-ba7403528d9c" />

### 6. Atualizar tarefa
**Endpoint:** `/tasks/{taskId}`
**Método:** `PUT`

<img width="854" height="450" alt="image" src="https://github.com/user-attachments/assets/da11652e-061e-40df-b114-612c2d8654ed" />


### 7. Exibir tarefa
**Endpoint:** `/tasks/{taskId}`
**Método:** `GET`

<img width="861" height="443" alt="image" src="https://github.com/user-attachments/assets/c337232f-53c1-4fbe-a61f-0b0b5cf7b5ba" />



### 8. Excluir tarefa
**Endpoint:** `/tasks{taskId}`
**Método:** `DELETE`

<img width="848" height="395" alt="image" src="https://github.com/user-attachments/assets/6d69817d-c0b1-45c9-a001-f055cf6524d5" />



