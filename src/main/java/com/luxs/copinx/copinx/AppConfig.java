package com.luxs.copinx.copinx;

import com.luxs.copinx.copinx.service.GerenciadorAgua;
import com.luxs.copinx.copinx.service.GerenciadorReview;
import com.luxs.copinx.copinx.service.GerenciadorUsuario;
import com.luxs.copinx.copinx.service.arquivo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    @Primary
    public GerenciadorAgua gerenciadorAguaBean(){
        return arquivo.recuperaGerenciador("GA.s", GerenciadorAgua.class);
    }
    @Bean
    @Scope("singleton")
    @Primary
    public GerenciadorUsuario gerenciadorUsuarioBean(){
        return arquivo.recuperaGerenciador("GU.s", GerenciadorUsuario.class);
    }
    @Bean
    @Scope("singleton")
    @Primary
    public GerenciadorReview gerenciadoReviewBean(){
        return arquivo.recuperaGerenciador("GR.s", GerenciadorReview.class);
    }

}
