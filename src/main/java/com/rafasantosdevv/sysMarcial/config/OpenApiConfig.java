package com.rafasantosdevv.sysMarcial.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Academia API")
                        .description(
                                """
                                API para gerenciamento de academia, incluindo:
                                
                                - Cadastro de alunos
                                - Matrículas e planos
                                - Controle financeiro
                                - Relatórios gerenciais
                                
                                Projeto desenvolvido para aprendizado próprio.
                                """
                        )
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Rafael Dos Santos")
                                .email("rafaeldosantos__@outlook.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor Local")))
                .externalDocs(new ExternalDocumentation().description("Documentação do Projeto").url("github"));
    }

}











