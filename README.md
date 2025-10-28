# 🌦️ Weather Forecast (Android)

Aplicativo Android desenvolvido para fins **acadêmicos de aprendizagem**, com o objetivo de demonstrar consumo de API de previsão do tempo, manipulação de dados JSON e exibição de informações meteorológicas em tempo real.

**EN:**  
Android weather-forecast system built for **academic learning purposes**, showcasing how to consume weather APIs, handle JSON data, and display real-time meteorological information.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| Plataforma | Android |
| Linguagem  | Java / Kotlin |
| IDE        | Android Studio |
| API        | OpenWeatherMap (ou outra utilizada) |
| Layout     | XML + Material Design |
| Arquitetura| MVVM / MVC (sugestão) |
| Android mínimo | API 21 (Lollipop) ou superior |

---

## ✨ Funcionalidades Principais

- 🌡️ Exibe temperatura atual, umidade e condições meteorológicas  
- 📍 Busca por cidade ou uso da localização atual  
- ☁️ Ícones dinâmicos de acordo com o clima  
- 🔄 Atualização automática ou manual das informações  
- 🎨 Interface simples e intuitiva, voltada para aprendizagem  

---

## ⚙️ Instalação e Execução

### 1. Clone o repositório
```bash
git clone https://github.com/MarlonMiguel/weather-forecast.git
cd weather-forecast
```

### 2. Abra o projeto no Android Studio
- Vá em **File → Open…**  
- Selecione a pasta do projeto  
- Aguarde o download das dependências

### 3. Configure a chave da API
- Crie ou edite o arquivo `local.properties` ou um arquivo de configuração conforme o projeto:
```properties
WEATHER_API_KEY=your_api_key_here
```
- Substitua `your_api_key_here` pela sua chave real da API

### 4. Compile e execute o aplicativo
- No Android Studio: clique em ▶ Run  
- Ou via terminal:
```bash
./gradlew assembleDebug
```
- Conecte um emulador ou dispositivo Android e execute

---

## 📱 Uso do Aplicativo

- Insira o nome da cidade ou permita que o app use sua localização (se implementado)  
- O app exibirá:
  - Temperatura atual  
  - Umidade  
  - Condições climáticas  
  - Ícones dinâmicos do clima  
- Ideal para aprender sobre:
  - Consumo de APIs REST  
  - Parsing de JSON  
  - Criação de UI responsiva em Android

---

## 🎓 Objetivo Educacional

Este projeto serve para estudos e prática de:
- Consumo de APIs REST no Android  
- Manipulação de dados JSON  
- Interface com Material Design  
- Boas práticas de desenvolvimento mobile  
- Controle de estado, lifecycle e permissões

---

## 🗂 Estrutura do Projeto

```
weather-forecast/
├─ app/
│  ├─ src/main/java/...      # código fonte Java/Kotlin
│  ├─ src/main/res/          # layouts, drawables, icons
│  └─ AndroidManifest.xml
├─ build.gradle
├─ settings.gradle
└─ local.properties          # chave da API (não comitar!)
```

---

## 📝 Licença

Projeto para uso **educacional e não comercial**.  
Distribuição ou reutilização somente mediante crédito ao autor.

---

## 👤 Autor

**Marlon Miguel**  
Projeto desenvolvido para fins acadêmicos no ecossistema Biopark / Curso de Desenvolvimento Mobile.

