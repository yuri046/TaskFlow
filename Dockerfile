# =========================
# Etapa 1: Build da aplicação
# =========================
FROM gradle:8.10-jdk21 AS build
WORKDIR /app

# Copia arquivos de configuração e código-fonte
COPY build.gradle settings.gradle ./
COPY src ./src

# Gera o shadowJar com todas as dependências
RUN gradle shadowJar -x test

# =========================
# Etapa 2: Imagem final (leve)
# =========================
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/build/libs/*-all.jar app.jar

# Porta exposta
EXPOSE 7000

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
